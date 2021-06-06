package com.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


import java.util.Arrays;

import java.util.Properties;

/**
 * kafka简单消费者
 */
public class SimpleConsumer {
		public static void main(String[] args) {
				String topic = "hello-kafka";
				Properties prop = new Properties();
				prop.put("bootstrap.servers", "slave2:9092");
				prop.put("group.id", "test");
				prop.put("enable.auto.commit", true);
				prop.put("auto.commit.interval.ms", "1000");
				prop.put("session.timeout.ms", "30000");
				prop.put("key.deserializer",
								"org.apache.kafka.common.serialization.StringDeserializer");
				prop.put("value.deserializer",
								"org.apache.kafka.common.serialization.StringDeserializer");
				KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);

				//Kafka Consumer subscribes list of topics here.
				consumer.subscribe(Arrays.asList(topic));


				while (true) {
						ConsumerRecords<String, String> records = consumer.poll(100);
						records.forEach(record -> {
								// print the offset,key and value for the consumer records.
								System.out.printf("offset = %d, key = %s, value = %s\n",
												record.offset(), record.key(), record.value());
						});


				}
		}
}
