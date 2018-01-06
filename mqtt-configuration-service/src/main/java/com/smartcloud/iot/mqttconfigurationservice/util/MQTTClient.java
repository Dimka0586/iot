package com.smartcloud.iot.mqttconfigurationservice.util;

import com.smartcloud.iot.mqttconfigurationservice.model.ThingConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

@Component
public class MQTTClient {

    public void publish(ThingConfig thingConfig, String topic, MqttMessage message) {
        //String topic = "Data to Cloud";
        //String content = "Message to Cloud " + clientIdIn;
        //int qos = 2;
        // String broker = "tcp://localhost:1883";
        //String clientId = "mqtt-spy1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(thingConfig.getBroker(), thingConfig.getClientID(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(thingConfig.isCleanSession());
            sampleClient.connect(connOpts);
            //message = new MqttMessage(content.getBytes());
            //message.setQos(qos);
            sampleClient.publish(topic, message);
            sampleClient.disconnect();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    public void subscribe() {

    }

}


class Subscriber implements Runnable {

    @Override
    public void run() {
        String topic = "Data from Cloud";
        int qos = 2;
        String broker = "tcp://localhost:1883";
        //String clientId = "mqtt-spy-subscribe";
        String clientId = "mqtt-spy2";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttAsyncClient sampleClient = new MqttAsyncClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.setCallback(new SimpleCallback());
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            Thread.sleep(1000);
            IMqttToken token = sampleClient.subscribe(topic, qos);
            System.out.println("token: " + token.toString());
            System.out.println("Subscribed");
        } catch (Exception me) {
            if (me instanceof MqttException) {
                System.out.println("reason " + ((MqttException) me).getReasonCode());
            }
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }

    }

}



class SimpleCallback implements MqttCallback {

    //Called when the client lost the connection to the broker
    public void connectionLost(Throwable arg0) {
        System.out.println("Connection lost to the broker tcp://192.168.1.2:1883");
    }

    //Called when a new message has arrived
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("| Topic:" + topic);
        System.out.println("| Message: " + new String(message.getPayload()));
        System.out.println("-------------------------------------------------");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Delivery is Complete");

    }
}
