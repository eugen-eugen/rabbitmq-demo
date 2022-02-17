package de.moldawski.rabbit.nativee;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(Object message) {
    System.out.println("Received <" + message + ">");
//    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}