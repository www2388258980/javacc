package org.apache.toolcabinet.map.gaode;

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

/***
 * 基于高德地图的地理编码 https://lbs.amap.com/api/webservice/guide/api/georegeo
 * 操作Excel使用easyExcel, https://www.yuque.com/easyexcel/doc/read
 * @Date 2021/4/23
 */
public class GeocodeGaodeApp {


		public static void main(String[] args) throws InterruptedException {
				long startTime = System.currentTimeMillis();
				BlockingQueue<GeocodeCSV> queue = new ArrayBlockingQueue<>(3000);

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

				private BlockingQueue<GeocodeCSV> queue;
				private AtomicBoolean flag;

				public T1(BlockingQueue<GeocodeCSV> queue, AtomicBoolean flag) {
						this.queue = queue;
						this.flag = flag;
				}

				@Override
				public void run() {
						long startTime = System.currentTimeMillis();
						Properties props = new Properties();
						try {
								Connection conn = DBConnection.getConnection("mysql", "hadoop02", "3306",
												"mysql_test", "dolphinscheduler", "Dolphinscheduler_123", props);
								System.out.println("mysql 连接成功!!!");
								String sql = "select organization_id,name from t_adress3";
								PreparedStatement ps = conn.prepareStatement(sql);
								ResultSet rs = ps.executeQuery();
								String url = "https://restapi.amap.com/v3/geocode/geo?adcode=460000&address=name&output=JSON&" +
												"key=3898fc5985a4208f1c509b702da3410c";
								while (rs.next()) {
										String name = rs.getString(2);
//										// 给name加上海南省,并且截取到村
//										name = "海南省" + name;
//										int i = name.indexOf("村");
//										if (i != -1) {
//												name = name.substring(0, i + 1);
//										}
										String realUrl = url.replace("name", URLEncoder.encode(name, "utf-8"));

										// 请求高德地图api
										String response = HttpClientUtil.doGet(realUrl, "utf-8");
										// 把json字符串转成对象
										GeocodeResponse gr = JSON.parseObject(response, GeocodeResponse.class);
										/*
										 * 如果请求失败，什么也不做，继续下一个请求
										 * 如果请求成功，但是结果的个数为0
										 * 如果请求成功，且结果的个数大于0
										 */
										String status = gr.getStatus();
										String cnt = gr.getCount();
										GeocodeCSV csv = new GeocodeCSV();
										if ("0".equals(status)) {

										} else if ("1".equals(status) && "0".equals(cnt)) {
												csv.setOrganizationId(rs.getString(1));
												csv.setName(rs.getString(2));
												csv.setData(null);
												queue.add(csv);
										} else if ("1".equals(status) && !"0".equals(cnt)) {
												csv.setOrganizationId(rs.getString(1));
												csv.setName(rs.getString(2));
												if ("海南省".equals(gr.getGeocodes().get(0).getProvince())) {
														csv.setData(gr);
												} else {
														csv.setData(null);
												}
												queue.add(csv);

										}
								}
								// 执行完成,将标志位置为true
								System.out.println("线程1执行完成!!!");
								flag.set(true);
								long endTime = System.currentTimeMillis();
								System.out.println("线程1运行时间为" + (endTime - startTime) / 1000 + "秒!");
						} catch (Exception e) {
								System.out.println("mysql 连接失败!");
								e.printStackTrace();
						}

				}
		}


		private static class T2 implements Runnable {
				private BlockingQueue<GeocodeCSV> queue;
				private AtomicBoolean flag;

				public T2(BlockingQueue<GeocodeCSV> queue, AtomicBoolean flag) {
						this.queue = queue;
						this.flag = flag;
				}

				@Override
				public void run() {
						long startTime = System.currentTimeMillis();
						ExcelWriter excelWriter = null;
						try {
								String fileName = "G:\\上海至数\\address_info_gaode.csv";
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
										GeocodeCSV csv = queue.poll(5, TimeUnit.MILLISECONDS);
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
						head2.add("行政区");
						List<String> head3 = new ArrayList<>();
						head3.add("formatted_address");
						List<String> head4 = new ArrayList<>();
						head4.add("location");
						list.add(head0);
						list.add(head1);
						list.add(head2);
						list.add(head3);
						list.add(head4);
						return list;
				}

				private List<List<Object>> dataList(GeocodeCSV csv) {
						List<Object> list = new ArrayList<>();
						list.add(csv.getOrganizationId());
						list.add(csv.getName());
						String city = null;
						String formatted_address = null;
						String location = null;
						if (csv.getData() != null) {
								List<Geocode> geocodes = csv.getData().getGeocodes();
								city = geocodes.get(0).getDistrict();
								formatted_address = geocodes.get(0).getFormatted_address();
								location = geocodes.get(0).getLocation();
						}
						list.add("[]".equals(city) ? "" : city);
						list.add(formatted_address);
						list.add(location);
						List<List<Object>> list2 = new ArrayList<>();
						list2.add(list);
						return list2;
				}
		}

}
