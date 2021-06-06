package org.apache.toolcabinet.jtest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;


import java.util.ArrayList;
import java.util.List;

public class CsvTest {
		public static void main(String[] args) {
				String fileName = "G:\\上海至数\\address_info.csv";
				WriteSheet sheet1 = EasyExcel.writerSheet("sheet1").build();
				ExcelWriter excelWriter = EasyExcel
								.write(fileName)
								.head(head())
								.build();

				excelWriter.write(dataList(), sheet1);
				excelWriter.finish();
		}

		private static List<List<String>> head() {
				List<List<String>> list = new ArrayList<>();
				List<String> head0 = new ArrayList<>();
				head0.add("机构id");
				List<String> head1 = new ArrayList<>();
				head1.add("name_zh");
				List<String> head2 = new ArrayList<>();
				head2.add("city");
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

		private static List<List<Object>> dataList() {
				List<Object> list = new ArrayList<>();
				list.add("1");
				list.add("2");
				list.add("3");
				list.add("4");
				list.add("5");
				List<List<Object>> list2 = new ArrayList<>();
				list2.add(list);
				return list2;
		}
}
