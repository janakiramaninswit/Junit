package com.kafka;

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
void listener(Object data) {
	System.out.println("DATA:" + data.toString());
}

}
