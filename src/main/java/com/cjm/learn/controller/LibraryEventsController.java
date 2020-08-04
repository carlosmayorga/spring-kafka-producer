package com.cjm.learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cjm.learn.domain.LibraryEvent;
import com.cjm.learn.domain.LibraryEventType;
import com.cjm.learn.producer.ILibraryEventsProducer;


@RestController
public class LibraryEventsController {
	
	Logger log = LoggerFactory.getLogger(LibraryEventsController.class);
	
	@Autowired
	@Qualifier("select-topic")
	ILibraryEventsProducer libEventProducer;
	
	@PostMapping("/v1/libraryevent")
	public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws Exception {
		
		// Invoke Kafka producer
		log.info("### Before invoke Kafka");
		
		// Async
		// libEventProducer.sendLibraryEvent(libraryEvent);
		
		// Sync
		// SendResult<Integer, String> sendResult = libEventProducer.sendLibraryEventSync(libraryEvent);
		// log.info(sendResult.toString());
		
		// Async and Specific Topic
		libraryEvent.setLibraryEventType(LibraryEventType.NEW);
		libEventProducer.sendLibraryEvent(libraryEvent);
		
		log.info("### After invoke Kafka");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
	}
}
