package com.cjm.learn.producer.utils;

import org.slf4j.Logger;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;


@Component
public class HandleResponse {
	
	public void handleSucces(Integer key, String value, SendResult<Integer, String> result, Logger log) {
		// TODO Auto-generated method stub
		log.info("Message Sent Successfully for Key: {} and Value : {}, Partition {} ", 
				key, value, result.getRecordMetadata().partition());
	}
	
	public void handleFailure(Integer key, String value, Throwable ex, Logger log) {
		// TODO Auto-generated method stub
		log.error("Error Sending the Message, the exception is : {} ", ex.getMessage());
		
		try {
			throw ex;
		} catch (Throwable thoThrowable) {
			log.error("Error OnFailure {}", ex.getMessage());
		}
		
	}

}
