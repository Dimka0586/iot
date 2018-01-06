package com.smartcloud.iot.mqttconfigurationservice.repository;

import com.smartcloud.iot.mqttconfigurationservice.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
}
