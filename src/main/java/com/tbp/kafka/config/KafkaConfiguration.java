
package com.tbp.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConfiguration {

	
	  @Value("${spring.kafka.bootstrap-servers}") private String bootstrapServers;
	  
	  @Value("${spring.kafka.consumer.group-id}") private String groupId;
	 
	@Bean
	public Map<String, Object> consumerConfig() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configMap.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, KafkaConsumerInterceptor.class.getName());
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
		configMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		return configMap;
	}

	@Bean
	public <T> ConsumerFactory<String, T> consumerFactory() {
		return new DefaultKafkaConsumerFactory(consumerConfig());
	}	

}
