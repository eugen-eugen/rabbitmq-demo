package de.moldawski.rabbit.jms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.UUID;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

	@Autowired
	ConnectionFactory connectionFactory;

	@Autowired
	Destination destination;

	@Autowired
	JmsTemplate tpl;

	@Scheduled(fixedRate = 1000)
	public void rpcWithSpringJmsTemplate() throws Exception {
		Connection clientConnection = connectionFactory.createConnection();
		clientConnection.start();

		tpl.setReceiveTimeout(2000);
		tpl.send(destination, session -> {
			BytesMessage message = session.createBytesMessage();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				new ObjectOutputStream(bos).writeObject(new String[] { "a", "b" });
				message.writeBytes(bos.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return message;
		});
	}
}
