package com.cjm.learn.producer.utils;

import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;

public class ProducerRecordByTopic {
	
	public ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic) {
		// TODO Auto-generated method stub
		return new ProducerRecord<Integer, String>(topic, null, key, value, null);
	}
	
	
	public ProducerRecord<Integer, String> buildProducerRecordWitHeader(Integer key, String value, String topic) {
		
		
		List<Header> recordHeaders = List.of(new RecordHeader("event-source", "scanner".getBytes()));
		
		return new ProducerRecord<Integer, String>(topic, null, key, value, recordHeaders);
	}

}
