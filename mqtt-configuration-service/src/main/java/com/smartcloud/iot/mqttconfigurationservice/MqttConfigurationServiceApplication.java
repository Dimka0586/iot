package com.smartcloud.iot.mqttconfigurationservice;


import com.smartcloud.iot.mqttconfigurationservice.model.ThingConfig;

import com.smartcloud.iot.mqttconfigurationservice.model.Topic;
import com.smartcloud.iot.mqttconfigurationservice.repository.ThingConfigRepository;
import com.smartcloud.iot.mqttconfigurationservice.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

@SpringBootApplication
public class MqttConfigurationServiceApplication {

	private @Autowired ThingConfigRepository thingConfigRepository;
	private @Autowired TopicRepository topicRepository;

	public static void main(String[] args) {
		SpringApplication.run(MqttConfigurationServiceApplication.class, args);
	}

	//@Transactional
	public @PostConstruct void init() {
		/*String baseTopic = "rpi";
		ThingConfig rpi = new ThingConfig("rpi", "rpiClient");
		Map<String, Topic> topics = new HashMap<>();
		Topic temperature1Topic = new Topic(baseTopic.concat("/temperature1"));
		temperature1Topic.setThingConfig(rpi);
		topics.put(temperature1Topic.getName(), temperature1Topic);
		Topic temperature2Topic = new Topic(baseTopic.concat("/temperature2"));
		temperature2Topic.setThingConfig(rpi);
		topics.put(temperature2Topic.getName(), temperature2Topic);
		rpi.setTopics(topics);
		thingConfigRepository.saveAndFlush(rpi);
		ThingConfig thingConfigFromDB = thingConfigRepository.findOne(1);
		System.out.println(thingConfigFromDB.getClientID());*/


	}



}
