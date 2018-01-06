package com.smartcloud.iot.mqttconfigurationservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "T_THING_CONFIG")
public class ThingConfig implements Serializable{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CLIENT_ID")
    private String clientID;

    @Column(name = "BROKER")
    private String broker;

    @Column(name = "QoS")
    private Integer QoS;

    @Column(name = "CLEAN_SESSION")
    private boolean cleanSession;



    //@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "thingConfig")
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "thingConfig")
    //@MapKeyColumn(name="TOPIC_NAME")
    private Map<String, Topic> topics;

    public ThingConfig() {
    }

    public ThingConfig(String name, String clientID) {
        this.name = name;
        this.clientID = clientID;
    }

    public ThingConfig(String name, String clientID, String broker) {
        this.name = name;
        this.clientID = clientID;
        this.broker = broker;
    }

    public ThingConfig(String name, String clientID, String broker, Integer qoS) {
        this.name = name;
        this.clientID = clientID;
        this.broker = broker;
        QoS = qoS;
    }

    public ThingConfig(String name, String clientID, String broker, Integer qoS, boolean cleanSession) {
        this.name = name;
        this.clientID = clientID;
        this.broker = broker;
        QoS = qoS;
        this.cleanSession = cleanSession;
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

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public Map<String, Topic> getTopics() {
        return topics;
    }

    public void setTopics(Map<String, Topic> topics) {
        this.topics = topics;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public Integer getQoS() {
        return QoS;
    }

    public void setQoS(Integer qoS) {
        QoS = qoS;
    }

    public boolean isCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    @Override
    public String toString() {
        return "ThingConfig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clientID='" + clientID + '\'' +
                ", topics=" + topics +
                '}';
    }
}

