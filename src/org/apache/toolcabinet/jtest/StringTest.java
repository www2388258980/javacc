package org.apache.toolcabinet.jtest;

import org.junit.Test;

public class StringTest {

		@Test
		public void zh() {
				String str = "白沙黎族自治县南开乡南开村委会卫生室";
				int i = str.indexOf("5");
				System.out.println(i);
				System.out.println(str.substring(0, i + 1));
		}
}
