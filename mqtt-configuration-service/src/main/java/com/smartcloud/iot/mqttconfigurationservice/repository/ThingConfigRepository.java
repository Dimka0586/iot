package com.smartcloud.iot.mqttconfigurationservice.repository;

import com.smartcloud.iot.mqttconfigurationservice.model.ThingConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingConfigRepository extends JpaRepository<ThingConfig, Integer> {

}
