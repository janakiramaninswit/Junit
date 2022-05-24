package com.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.bookstore.entity.Book;

@Configuration
public class KafkaConsumerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstarpServers;
	
	public Map<String, Object> config(){
		Map<String, Object> configMap = new HashMap<>();
		
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstarpServers);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		
		return configMap;
	}
	
	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		 return new DefaultKafkaConsumerFactory<>(config());
	}

	
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String,Object> factory(
//			ConsumerFactory<String, Object> consumerFactory
//		){
//		
//		ConcurrentKafkaListenerContainerFactory<String, Object> factory = 
//				new ConcurrentKafkaListenerContainerFactory<String, Object>();
//		factory.setConsumerFactory(consumerFactory);
//		
//		return factory;
//	}
//	
//	public ConsumerFactory<String, Book> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstarpServers);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "groupId");
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Book.class));
//    }
//	
//	 @Bean
//	    public ConcurrentKafkaListenerContainerFactory<String, Book> userKafkaListenerContainerFactory() {
//	        ConcurrentKafkaListenerContainerFactory<String, Book> factory = new ConcurrentKafkaListenerContainerFactory<>();
//	        factory.setConsumerFactory(consumerFactory());
//	        factory.setMessageConverter(new StringJsonMessageConverter());
//	        return factory;
//	    }
    

   
}
