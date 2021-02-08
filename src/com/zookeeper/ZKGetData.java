package com.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class ZKGetData {
		private static ZooKeeper zk;
		private static ZooKeeperConnection conn;

		public static Stat znode_exists(String path) throws
						KeeperException, InterruptedException {
				return zk.exists(path, true);
		}

		public static void main(String[] args) throws InterruptedException, KeeperException {
				String path = "/MyFirstZnode";
				final CountDownLatch connectedSignal = new CountDownLatch(1);

				try {
						conn = new ZooKeeperConnection();
						zk = conn.connect("slave2");
						Stat stat = znode_exists(path);
						if (stat != null) {
								byte[] b = zk.getData(path, we -> {
										if (we.getType() == Watcher.Event.EventType.None) {
												switch (we.getState()) {
														case Expired:
																connectedSignal.countDown();
																break;
												}
										} else {
												try {
														byte[] bn = zk.getData(path,
																		false, null);
														String data = new String(bn,
																		"UTF-8");
														System.out.println(data);
														connectedSignal.countDown();
												} catch (Exception ex) {
														System.out.println(ex.getMessage());
												}
										}
								}, null);
								String data = new String(b, "UTF-8");
								System.out.println(data);
								connectedSignal.await();
						} else {
								System.out.println("Node does not exists");
						}
				} catch (Exception e) {
						System.out.println(e.getMessage());
				}
		}
}
