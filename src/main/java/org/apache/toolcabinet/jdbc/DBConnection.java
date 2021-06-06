package org.apache.toolcabinet.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 封装数据库连接
 * 用来进行一些简单的请求以及测试
 *
 * @author 杨吉
 * @Date 2021/4/22
 */
public class DBConnection {

		/**
		 * @param classfication 数据库类别,mysql,oracle,postgresql,etc
		 * @param ip
		 * @param port
		 * @param dbName
		 * @param props         用户名密码通过props设置
		 * @return
		 * @throws Exception
		 */
		public static Connection getConnection(String classfication, String ip, String port, String dbName,
																					 String user,String passwd,Properties props) throws Exception {
				if (ip == null || ip == "") {
						throw new Exception("ip不可以为空!!!");
				}
				if (port == null || port == "") {
						port = "3306";
				}
				if (dbName == null || dbName == "") {
						throw new Exception("dbName不可以为空!!!");
				}
				if (classfication == null || classfication == "") {
						throw new Exception("dbName类别不可以为空!!!");
				}
				if(props == null){
						props = new Properties();
				}
				props.setProperty("user",user );
				props.setProperty("password", passwd);
				Connection conn = null;
				switch (classfication) {
						case "mysql":
								Class.forName("com.mysql.jdbc.Driver");
								conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + '/' + dbName +
												"?useUnicode=true&amp;characterEncoding=UTF-8", props);
								break;
						case "oracle":
								Class.forName("oracle.jdbc.driver.OracleDriver");
								conn = DriverManager.getConnection("jdbc:oracle:thin:@" + ip + ":" + port + "/" + dbName, props);
								break;
						case "postgresql":
								Class.forName("org.postgresql.Driver");
								conn = DriverManager.getConnection("jdbc:postgresql:@" + ip + ":" + port + "/" + dbName, props);
								break;
						default:
								throw new Exception("无对应的数据库连接,classfication: " + classfication);

				}
				return conn;
		}


}
