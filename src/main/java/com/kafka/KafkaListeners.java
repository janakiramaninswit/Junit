package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.bookstore.entity.Book;

@Component
public class KafkaListeners {


@KafkaListener(
	topics = "jana",
	groupId = "groupId"
)
void listener(ConsumerRecord data) {
	System.out.println("DATA:" + data.toString());
	System.out.println("TOPIC:" + data.topic());
	System.out.println("VALUE:" + data.value());
}

}
