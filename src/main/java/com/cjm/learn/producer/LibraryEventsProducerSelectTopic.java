package com.cjm.learn.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.cjm.learn.domain.LibraryEvent;
import com.cjm.learn.producer.utils.HandleResponse;
import com.cjm.learn.producer.utils.ProducerRecordByTopic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service("select-topic")
public class LibraryEventsProducerSelectTopic implements ILibraryEventsProducer {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String topic = "library-events";
	
	ProducerRecordByTopic prt = new ProducerRecordByTopic();

	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplate;

	@Autowired
	ObjectMapper objMapper;

	@Autowired
	HandleResponse hlr;

	@Override
	public void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {

		log.info("sendLibraryEvent No Default Topic");

		Integer key = libraryEvent.getLibraryEventId();
		String value = objMapper.writeValueAsString(libraryEvent);
		
		ProducerRecord<Integer, String> producerRecord = prt.buildProducerRecordWitHeader(key, value, topic);

		ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(producerRecord);

		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

			@Override
			public void onSuccess(SendResult<Integer, String> result) {
				// TODO Auto-generated method stub
				hlr.handleSucces(key, value, result, log);
			}

			@Override
			public void onFailure(Throwable ex) {
				// TODO Auto-generated method stub
				hlr.handleFailure(key, value, ex, log);
			}

		});

	}

	@Override
	public SendResult<Integer, String> sendLibraryEventSync(LibraryEvent libraryEvent)
			throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

}
