package com.smartcloud.iot.innersensorsimulator;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
//@EnableDiscoveryClient
public class InnerSensorSimulatorApplication {

	@Value("${mqtt-client.serverURI}")
	String mqttServerURI;

	@Value("${rabbit-hostname}")
	String rabbitHostname;

	public static void main(String[] args) {
		SpringApplication.run(InnerSensorSimulatorApplication.class, args);
	}

	public @PostConstruct
	void publish() throws InterruptedException {
		String sensorName = "sensor4";
		RabbitAdmin admin = new RabbitAdmin(connectionFactory());
		Queue queue = new Queue("sensors_" + sensorName);
		TopicExchange topicExchange = new TopicExchange("amq.topic");
		Binding binding = BindingBuilder.bind(queue).to(topicExchange).with("sensors." + sensorName);
		//Binding binding = BindingBuilder.bind(queue).to(topicExchange).with("amq.topic");
		admin.declareQueue(queue);
		admin.declareExchange(topicExchange);
		admin.declareBinding(binding);


		try {
			MqttClient client = new MqttClient(mqttServerURI, sensorName);
			client.connect();
			MqttMessage message = new MqttMessage();

			for (int i=0;i<100000000;i++) {
				String jsonMessage = sensorName + ":::{\"instanceId\":1,\"thingPar\":{\"id\":1}," +
						"\"thing\": {\"id\":2},\"value\":\"" +
						// new Double(Math.random()*100.0).toString() + "\"}";
						new Integer(i).toString() + "\"}";
				message.setPayload(jsonMessage.getBytes());
				client.publish("sensors." + sensorName, message);
				Thread.sleep(5000);
			}
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	AmqpTemplate template;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory =
				new CachingConnectionFactory(rabbitHostname);
		//connectionFactory.setUsername("guest");
		//connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}
}
