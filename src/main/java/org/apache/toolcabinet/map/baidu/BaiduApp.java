package org.apache.toolcabinet.map.baidu;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import org.apache.toolcabinet.http.HttpClientUtil;
import org.apache.toolcabinet.jdbc.DBConnection;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BaiduApp {
		public static void main(String[] args) throws InterruptedException {
				long startTime = System.currentTimeMillis();
				BlockingQueue<SearchResponse> queue = new ArrayBlockingQueue<>(3000);

				// 原子类,线程2退出的标志
				AtomicBoolean flag = new AtomicBoolean();

				// 线程1 从mysql读取数据，请求接口，将数据写入队列
				Thread thread1 = new Thread(new T1(queue, flag));
				thread1.start();
				// 线程2 从队列读取数据，写入csv
				Thread thread2 = new Thread(new T2(queue, flag));
				thread2.start();
				thread1.join();
				thread2.join();
				long endTime = System.currentTimeMillis();
				System.out.println("主线程运行时间为" + (endTime - startTime) / 1000 + "秒!");
		}


		private static class T1 implements Runnable {

				private BlockingQueue<SearchResponse> queue;
				private AtomicBoolean flag;

				public T1(BlockingQueue<SearchResponse> queue, AtomicBoolean flag) {
						this.queue = queue;
						this.flag = flag;
				}

				@Override
				public void run() {
						int j = 0;
						long startTime = System.currentTimeMillis();
						Properties props = new Properties();
						try {
								Connection conn = DBConnection.getConnection("mysql", "hadoop02", "3306",
												"mysql_test", "dolphinscheduler", "Dolphinscheduler_123", props);
								System.out.println("mysql 连接成功!!!");
								String sql = "select organization_id,name_zh,formatted_adress from hainan_address";
								PreparedStatement ps = conn.prepareStatement(sql);
								ResultSet rs = ps.executeQuery();
								String url = "http://api.map.baidu.com/place/v2/search?query=$1&tag=%E8%A1%8C%E6%94%BF%E5%9C%B0%E6%A0%87&" +
												"region=%E6%B5%B7%E5%8D%97&output=json&ak=2Ka2wCEkOwmkN0BxkwWXazk2CPkRRC58";
								while (rs.next()) {
										String name = rs.getString(2);
										String formatted_adress = rs.getString(3);
										String nameP = formatted_adress == null ? name : formatted_adress;

										String realUrl = url.replace("$1", URLEncoder.encode(nameP, "utf-8"));

										// 请求Baidu地图api
										String response = HttpClientUtil.doGet(realUrl, "utf-8");
										// 把json字符串转成对象
										SearchResponse sr = JSON.parseObject(response, SearchResponse.class);
										sr.setNameZh(rs.getString(2));
										sr.setOrganizationId(rs.getString(1));


										if ("0".equals(sr.getStatus())) {
												queue.add(sr);
												j++;
										}
								}
								// 执行完成,将标志位置为true
								System.out.println("线程1执行完成!!!");
								flag.set(true);
								long endTime = System.currentTimeMillis();
								System.out.println("线程1运行时间为" + (endTime - startTime) / 1000 + "秒!");
								System.out.println("队列中共" + j + "条");
						} catch (Exception e) {
								System.out.println("mysql 连接失败!");
								e.printStackTrace();
						}

				}
		}


		private static class T2 implements Runnable {
				private BlockingQueue<SearchResponse> queue;
				private AtomicBoolean flag;

				public T2(BlockingQueue<SearchResponse> queue, AtomicBoolean flag) {
						this.queue = queue;
						this.flag = flag;
				}

				@Override
				public void run() {

						long startTime = System.currentTimeMillis();
						ExcelWriter excelWriter = null;
						try {
								String fileName = "G:\\上海至数\\address_info_baidu3.csv";
								WriteSheet sheet1 = EasyExcel.writerSheet("sheet1").build();
								excelWriter = EasyExcel
												.write(fileName)
												.head(head())
												.build();
								for (; ; ) {
										// 退出条件,flag 为true且队列为空
										if (flag.get() && queue.isEmpty()) {
												break;
										}
										SearchResponse csv = queue.poll(5, TimeUnit.MILLISECONDS);
										if (csv == null) {
												Thread.sleep(5000);
												continue;
										}
										excelWriter.write(dataList(csv), sheet1);
								}
								System.out.println("线程2执行完成!!!");
						} catch (InterruptedException e) {
								e.printStackTrace();
						} finally {
								if (excelWriter != null) {
										excelWriter.finish();
								}
						}
						long endTime = System.currentTimeMillis();
						System.out.println("线程2运行时间为" + (endTime - startTime) / 1000 + "秒!");
				}

				private List<List<String>> head() {
						List<List<String>> list = new ArrayList<>();
						List<String> head0 = new ArrayList<>();
						head0.add("机构id");
						List<String> head1 = new ArrayList<>();
						head1.add("name_zh");
						List<String> head2 = new ArrayList<>();
						head2.add("城市");
						List<String> head6 = new ArrayList<>();
						head6.add("区");
						List<String> head3 = new ArrayList<>();
						head3.add("formatted_address");
						List<String> head4 = new ArrayList<>();
						head4.add("经度");
						List<String> head5 = new ArrayList<>();
						head5.add("纬度");

						list.add(head0);
						list.add(head1);
						list.add(head2);
						list.add(head6);
						list.add(head3);
						list.add(head4);
						list.add(head5);
						return list;
				}

				private List<List<Object>> dataList(SearchResponse csv) {
						List<Object> list = new ArrayList<>();
						list.add(csv.getOrganizationId());
						list.add(csv.getNameZh());
						String city = null;
						String district = null;
						String formatted_address = null;
						String lat = null;
						String lng = null;

						// 如果区信息为空，则拿上一级城市信息
						if (csv.getResults() != null && csv.getResults().size() > 0) {
								// 拿第一个
								Result result = csv.getResults().get(0);
								city = result.getCity();
								district = result.getArea() == null || "".equals(result.getArea()) ? city : result.getArea();
								formatted_address = result.getProvince() + result.getCity() + result.getName();
								if (result.getLocation() != null) {
										lat = result.getLocation().getLat();
										lng = result.getLocation().getLng();
								}

						}

						list.add(city);
						list.add(district);
						list.add(formatted_address);
						list.add(lng);
						list.add(lat);

						List<List<Object>> list2 = new ArrayList<>();
						list2.add(list);
						return list2;
				}
		}
}
