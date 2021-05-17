package org.apache.toolcabinet.jtest;

import org.apache.toolcabinet.jdbc.DBConnection;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

public class JDBCTest {
		private static final Logger logger = LoggerFactory.getLogger(JDBCTest.class);

		@Test
		public void mysql() {
				try {
						Properties props = new Properties();
						Connection connn = DBConnection.getConnection("mysql", "hadoop02", "3306",
										"mysql_test", "dolphinscheduler", "Dolphinscheduler_123", props);
						logger.info("mysql 连接成功!!!");

				} catch (Exception e) {
						e.printStackTrace();
				}
		}
}
