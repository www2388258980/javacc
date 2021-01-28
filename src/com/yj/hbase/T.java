package com.yj.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;


import java.io.IOException;

public class T {

		static Configuration config;
		static Connection connection;
		static Admin admin;

		public static void main(String[] args) throws IOException {
				config = HBaseConfiguration.create();

				//Add any necessary configuration files (hbase-site.xml, core-site.xml)
				config.addResource(T.class.getResource("/hbase-site.xml").getPath());
				config.addResource(T.class.getResource("/core-site.xml").getPath());

				connection = ConnectionFactory.createConnection(config);
				admin = connection.getAdmin();

//				createTable();
//				insert();
				getData();
		}


		//创建数据表
		public static void createTable() throws IOException {
				//表名
				TableName tableName = TableName.valueOf("studentinfo");
				if (admin.tableExists(tableName)) {
						System.out.println("表名已经存在！");
				} else {
						//创建表的描述信息
						HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
						//列族的描述类
						HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("stu".getBytes());
						//绑定
						hTableDescriptor.addFamily(hColumnDescriptor);
						//根据表的描述对象创建表
						admin.createTable(hTableDescriptor);
				}
		}

		//新增数据
		public static void insert() throws IOException {
				//获取表对象
				Table table = connection.getTable(TableName.valueOf("studentinfo"));
				Put put01 = new Put("1111".getBytes());
				put01.addColumn("stu".getBytes(), "name".getBytes(), "zhangsan".getBytes());
				put01.addColumn("stu".getBytes(), "age".getBytes(), "12".getBytes());
				put01.addColumn("stu".getBytes(), "sex".getBytes(), "man".getBytes());
				Put put02 = new Put("2222".getBytes());
				put02.addColumn("stu".getBytes(), "name".getBytes(), "lisi".getBytes());
				put02.addColumn("stu".getBytes(), "age".getBytes(), "15".getBytes());
				put02.addColumn("stu".getBytes(), "sex".getBytes(), "nv".getBytes());
				table.put(put01);
				table.put(put02);
				table.close();
		}

		//查询表中所有数据
		public static void getData() throws IOException {
				//获取表对象
				Table table = connection.getTable(TableName.valueOf("studentinfo"));
				//创建ResultScanner对象
				ResultScanner scanner = table.getScanner(new Scan());
//				scanner.forEach(data -> data );
				for (Result result : scanner) {
						for (Cell cell : result.rawCells()) {
								System.out.println("rowkey：" + Bytes.toString(result.getRow()));
								System.out.print("family:" + Bytes.toString(CellUtil.cloneFamily(cell)) + "\t");
								System.out.print("column:" + Bytes.toString(CellUtil.cloneQualifier(cell)) + "\t");
								System.out.print("value:" + Bytes.toString(CellUtil.cloneValue(cell)));
								System.out.println();
								System.out.println();
						}
				}
				table.close();
		}

		//查询指定Row Key中的数据
		public static void getDataByRowKey() throws IOException {
				//获取表对象
				Table table = connection.getTable(TableName.valueOf("studentinfo"));
				Get get = new Get(Bytes.toBytes("1111"));
				//创建result
				Result result = table.get(get);
				for (Cell cell : result.rawCells()) {
						System.out.println("rowkey：" + Bytes.toString(result.getRow()));
						System.out.print("family==>" + Bytes.toString(CellUtil.cloneFamily(cell)) + "\t");
						System.out.print("column==>" + Bytes.toString(CellUtil.cloneQualifier(cell)) + "\t");
						System.out.print("value==>" + Bytes.toString(CellUtil.cloneValue(cell)));
						System.out.println();
						System.out.println();
				}
				table.close();
		}

		//删除指定Row Key中的数据
		public static void deleteDataRowKey() throws IOException {
				//获取表对象
				Table table = connection.getTable(TableName.valueOf("studentinfo"));
				//设置要删除的rowkey
				Delete delete = new Delete(Bytes.toBytes("2222"));
				table.delete(delete);
				table.close();
		}

		//删除指定列族的数据
		public static void deleteDataColumnFamily() throws IOException {
				//获取表对象
				Table table = connection.getTable(TableName.valueOf("studentinfo"));
				//设置要删除的rowkey
				Delete delete = new Delete(Bytes.toBytes("1111"));
				//设置指定列族
				delete.addFamily(Bytes.toBytes("stu"));
				table.delete(delete);
				table.getClass();
		}

		//删除表
		public static void deleteTable() throws IOException {
				TableName tableName = TableName.valueOf("studentinfo");
				//判断表是否存在
				if (admin.tableExists(tableName)) {
						admin.disableTable(tableName);
						admin.deleteTable(tableName);
				}
		}

}
