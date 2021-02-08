package com.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZKCreate {
		// create static instance for zookeeper class.
		private static ZooKeeper zk;

		// create static instance for ZooKeeperConnection class.
		private static ZooKeeperConnection conn;

		// Method to create znode in zookeeper ensemble
		/*
		 	path - Znode路径。例如，/myapp1，/myapp2，/myapp1/mydata1，myapp2/mydata1/myanothersubdata

			data - 要存储在指定znode路径中的数据

			acl - 要创建的节点的访问控制列表。ZooKeeper API提供了一个静态接口 ZooDefs.Ids 来获取一些基本的acl列表。
			例如，ZooDefs.Ids.OPEN_ACL_UNSAFE返回打开znode的acl列表。

			createMode - 节点的类型，即临时，顺序或两者。这是一个枚举。
		 */
		public static void create(String path, byte[] data) throws
						KeeperException, InterruptedException {
				zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
								CreateMode.PERSISTENT);
		}

		public static void main(String[] args) {

				// znode path
				String path = "/MyFirstZnode"; // Assign path to znode

				// data in byte array
				byte[] data = "My first zookeeper app".getBytes(); // Declare data

				try {
						conn = new ZooKeeperConnection();
						zk = conn.connect("slave2");
						create(path, data); // Create the data to the specified path
						conn.close();
				} catch (Exception e) {
						System.out.println(e.getMessage()); //Catch error message
				}
		}
}
