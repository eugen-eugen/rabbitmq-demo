package de.moldawski.rabbit.jms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.jms.BytesMessage;
import javax.jms.JMSException;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@JmsListener(destination = "mytopicex")
	public void receiveMessage(BytesMessage message) throws IOException, ClassNotFoundException, JMSException {
		byte[] payload = new byte[(int) message.getBodyLength()];
		message.readBytes(payload);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(payload));
		Object o = ois.readObject();
		System.out.println("Received <" + message + ">");
	}

}