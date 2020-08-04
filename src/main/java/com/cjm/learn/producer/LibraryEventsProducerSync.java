package com.cjm.learn.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.cjm.learn.domain.LibraryEvent;
import com.cjm.learn.producer.utils.HandleResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("sync")
public class LibraryEventsProducerSync implements ILibraryEventsProducer {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplate;
	
	@Autowired
	ObjectMapper objMapper;
	
	@Autowired
	HandleResponse hlr;
	
	@Override
	public void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
		// TODO Auto-generated method stub

	}

	@Override
	public SendResult<Integer, String> sendLibraryEventSync(LibraryEvent libraryEvent)
			throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
		
		log.info("sendLibraryEventSync");
		Integer key = libraryEvent.getLibraryEventId();
		String value = objMapper.writeValueAsString(libraryEvent);

		SendResult<Integer, String> sendResult = null;

		try {
			sendResult = kafkaTemplate.sendDefault(key, value).get(1, TimeUnit.SECONDS);
		} catch (ExecutionException | InterruptedException e) {
			log.error("Interrupted -- Error Sending the Message, the exception is : {} ", e.getMessage());
			throw e;
		} catch (TimeoutException e) {
			log.error("Timeout -- Error Sending the Message, the exception is : {} ", e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Exc -- Error Sending the Message, the exception is : {} ", e.getMessage());
			throw e;
		}

		return sendResult;

	}

}
