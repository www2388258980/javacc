package com.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * kafka简单生产者
 */
public class SimpleProducer {
		public static void main(String[] args) {
				String topicName = "hello-kafka";
				Properties properties = new Properties();
				properties.put("bootstrap.servers", "slave2:9092");
				// Set acknowledgements for producer requests.
				properties.put("acks", "all");
				// 失败重试次数
				properties.put("retries", 0);
				// 缓冲区大小
				properties.put("batch.size", 16384);
				// Reduce the no of requests less than 0
				properties.put("linger.ms", 1);
				// The buffer.memory controls the total amount of memory available to the producer for buffering.
				properties.put("buffer.memory", 33554432);
				properties.put("key.serializer",
								"org.apache.kafka.common.serialization.StringSerializer");

				properties.put("value.serializer",
								"org.apache.kafka.common.serialization.StringSerializer");
				Producer<String, String> producer = new KafkaProducer<>(properties);

				for (int i = 0; i < 10; i++)
						producer.send(new ProducerRecord<>(topicName, Integer.toString(i), Integer.toString(i)),
										(metadata, e) -> {
												if (e == null) {
														// 代表没有发生异常
														System.out.println("Message sent successfully");
														System.out.println("topic: " + metadata.topic());
														System.out.println("partion: " + metadata.partition());
														System.out.println("offset: " + metadata.offset());
												} else {
														// 发生异常
														System.out.println("Message send failly: " + e.getMessage());
														e.printStackTrace();
												}
										}
						);

				producer.close();
		}
}
