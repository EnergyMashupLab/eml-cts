package org.theenergymashuplab.cts.controller;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.BridgeInterval;
import org.theenergymashuplab.cts.EiTender;
import org.theenergymashuplab.cts.EiTenderRabbit;
import org.theenergymashuplab.cts.Interval;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayloadRabbit;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import java.time.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Receiver {

  public void receiveMessage(String message) {
	  
	  EiCreateTenderPayloadRabbit tempTender = null;	 
	  Boolean addQSuccess = false;
	  
	  ObjectMapper mapper = new ObjectMapper();
	  try {
		  tempTender =  mapper.readValue(message, EiCreateTenderPayloadRabbit.class);
	  } catch (Exception e) {
		  e.printStackTrace();
		  System.exit(1);
	  }
	  
	  BridgeInterval receivedInterval = tempTender.getTender().getInterval();
	  Interval newInterval = new Interval(receivedInterval.getDurationInMinutes(), receivedInterval.getDtStart().asInstant());
	  
	  EiTender convertedTender = new EiTender(newInterval, tempTender.getTender().getQuantity(), tempTender.getTender().getPrice(), tempTender.getTender().getExpirationTime().asInstant(), tempTender.getTender().getSide());
	  EiCreateTenderPayload convertedPayload = new EiCreateTenderPayload(convertedTender, tempTender.getPartyId(), tempTender.getCounterPartyId());
	  
	  addQSuccess = LmeRestController.queueFromLme.add(convertedPayload);
  }
    
}
