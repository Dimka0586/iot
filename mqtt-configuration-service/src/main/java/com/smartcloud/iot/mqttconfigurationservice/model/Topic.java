package com.smartcloud.iot.mqttconfigurationservice.model;

import javax.persistence.*;

@Entity
@Table(name = "T_TOPIC")
public class Topic {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    String name;

    @ManyToOne
    ThingConfig thingConfig;

    public Topic() {
    }

    public Topic(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThingConfig getThingConfig() {
        return thingConfig;
    }

    public void setThingConfig(ThingConfig thingConfig) {
        this.thingConfig = thingConfig;
    }


}
