package com.cjm.learn.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.springframework.kafka.support.SendResult;

import com.cjm.learn.domain.LibraryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ILibraryEventsProducer {

	// Async
	void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException;

	// Sync
	SendResult<Integer, String> sendLibraryEventSync(LibraryEvent libraryEvent)
			throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException;

}