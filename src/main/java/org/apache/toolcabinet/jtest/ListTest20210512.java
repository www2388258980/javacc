package org.apache.toolcabinet.jtest;

import java.util.ArrayList;
import java.util.List;

public class ListTest20210512 {
		public static void main(String[] args) {
				List<String> list = new ArrayList<String>();
				list.add("1");
				list.add("2");
				for (String item : list) {
						if ("2".equals(item)) {
								list.remove(item);
						}
				}
		}
}
