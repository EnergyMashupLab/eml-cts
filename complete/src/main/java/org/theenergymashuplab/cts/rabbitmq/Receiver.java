package org.theenergymashuplab.cts.rabbitmq;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;
import org.theenergymashuplab.cts.EiTender;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">");
    /*Boolean addQsuccess = false;
    
	addQsuccess = queueFromLme.add(tempCreate);
	logger.debug("queueFomLme addQsuccess " + addQsuccess +
			" TenderId " + tempTender.getTenderId());
	
	// put EiCreateTenderPayload in map to build EiCreateTransactionPayload
	// from MarketCreateTransaction
	mapPutReturnValue = ctsTenderIdToCreateTenderMap.put(tempCreate.getTender().getTenderId().value(),
			tempCreate);	
	
	if (mapPutReturnValue == null) {
		logger.debug("mapPutReturnValue is null - new entry");
	}	else	{
		logger.debug("mapPutReturnValue non-null - previous entry " + mapPutReturnValue.toString());
	}*/
	
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }
}
