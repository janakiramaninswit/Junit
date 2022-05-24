package com.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.bookstore.entity.Book;

@Configuration
public class KafkaProducerConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstarpServers;
	
	public Map<String, Object> config() {
		Map<String, Object> configMap = new HashMap<>();
		
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstarpServers);
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return configMap;
	}
	
	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		 return new DefaultKafkaProducerFactory<>(config());
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory){
		
		return new KafkaTemplate(producerFactory);
	}

}
