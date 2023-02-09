
package com.tbp.kafka.consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tbp.kafka.model.ConsumerDto;

@Component
public class KafkaEventListener<T> {
	private static final Logger log = LogManager.getLogger(KafkaEventListener.class);

	@KafkaListener(topics = "${kafka.topic}")

	void consume(ConsumerRecord<String, T> record) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		List<HashMap<String, String>> headerListMap= new ArrayList<HashMap<String, String>>();
	    for (Header header : record.headers()) {
	    	HashMap<String, String> headersMap = new HashMap<String, String>();
	    	headersMap.put("Key",header.key());
	    	headersMap.put("Value",new String(header.value()));
	    	headerListMap.add(headersMap);
	        
	    }
	   
		ConsumerDto<String, T> data = new ConsumerDto<String, T>(record.topic(), record.key(), record.value(), record.timestamp(),headerListMap);
		try {
			log.info(mapper.writeValueAsString(data));
			System.out.println(mapper.writeValueAsString(data));

		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
			log.error("error {} in kakfka Listener topic {} ", e.getMessage(),record.topic());
		}

	}

}