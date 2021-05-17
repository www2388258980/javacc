package org.apache.toolcabinet.jtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

		public static void main(String[] args) throws ParseException {
				long time = new Date().getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(sdf.parse("9999-12-31").getTime() / 1000);
				System.out.println(time / 1000);


				long[] timeArr = new long[]{
								sdf.parse("2021-04-01").getTime() / 1000,
								sdf.parse("2021-04-02").getTime() / 1000,
								sdf.parse("2021-04-03").getTime() / 1000,
								sdf.parse("2021-04-04").getTime() / 1000,
								sdf.parse("2021-04-05").getTime() / 1000,
								sdf.parse("2021-04-06").getTime() / 1000,
								sdf.parse("2021-04-07").getTime() / 1000,
								sdf.parse("2021-04-08").getTime() / 1000,
								sdf.parse("2021-04-09").getTime() / 1000,
								sdf.parse("2021-04-10").getTime() / 1000,
								sdf.parse("2021-04-11").getTime() / 1000,
								sdf.parse("2021-04-12").getTime() / 1000,
								sdf.parse("2021-04-13").getTime() / 1000
				};
				System.out.println("==================================================");

				long t1 = sdf.parse("2021-04-08").getTime() / 1000;
				for (long t2 : timeArr) {
						if (t1 > t2)
								System.out.println(sdf.format(new Date(t2 * 1000)));
				}
		}
}
