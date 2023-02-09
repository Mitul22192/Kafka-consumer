package com.tbp.kafka.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tbp.kafka.utility.KafkaUtils;

import lombok.Data;
@Data
public class ConsumerDto<K, V> {
	String Topic;
	K Key;
	V Value;
	String Time;	
	List<HashMap<String, String>> headers;

	public ConsumerDto() {
		super();
	}
	

	public ConsumerDto(String topic, K key, V value, Long time, List<HashMap<String, String>> headers) {
		super();
		Topic = topic;
		Key = key;
		Value = value;
		Date date = new Date(time);
		Time = KafkaUtils.formatDateToString(date, "MMM dd yyyy hh:mm:ss a", "UTC");
		this.headers= headers;
	}


	@JsonIgnore
	public String getTopic() {
		return Topic;
	}

	public void setTopic(String topic) {
		Topic = topic;
	}

	
	@JsonIgnore
	public K getKey() {
		return Key;
	}


	public void setKey(K key) {
		Key = key;
	}

	@JsonIgnore
	public V getValue() {
		return Value;
	}


	public void setValue(V value) {
		Value = value;
	}

	
	@JsonIgnore
	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public List<HashMap<String, String>> getHeaders() {
		return headers;
	}


	public void setHeaders(List<HashMap<String, String>> headers) {
		this.headers = headers;
	}
	
}
