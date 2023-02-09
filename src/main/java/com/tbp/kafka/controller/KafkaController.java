package com.tbp.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tbp.kafka.model.Book;
import com.tbp.kafka.producer.KafkaMessageProducer;

@RestController
public class KafkaController {
	@Autowired
	private KafkaMessageProducer<Book>  kafkaMessageProducer;
	
	@PostMapping("/testMessage")
	public String tesMessage(@RequestBody String message) {
//		kafkaMessageProducer.sendMessage(message);
		return "Application is running on port 9090 : Message ==>"+message;
	}
	/*
	 * @PostMapping("/pushMessage") public String postMessage(@RequestBody Book
	 * book) { return kafkaMessageProducer.sendMessage(book); }
	 */
	
	@PostMapping("/pushMessage")
	public String postMessage(@RequestBody Book details) {
		return kafkaMessageProducer.sendBook(details);
	}

}
