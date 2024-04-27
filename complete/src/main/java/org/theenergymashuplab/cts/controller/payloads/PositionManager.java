/*
 * Copyright 2019-2020 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * written by: Dhruvin Desai 202005
 */
package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.CtsStreamIntervalType;
import org.theenergymashuplab.cts.CtsStreamType;
import org.theenergymashuplab.cts.EiResponse;
import org.theenergymashuplab.cts.Interval;
import org.theenergymashuplab.cts.ResourceDesignator;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.theenergymashuplab.cts.dao.PositionService;
import org.theenergymashuplab.cts.model.PositionManagerModel;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PositionManager {
	private static final Logger logger = LogManager.getLogger(PositionManager.class);

	@Autowired
	PositionService posDao;

	@PostMapping("/position/{positionParty}/add")
	// add to a position
	public String createPosition(@PathVariable(value = "positionParty") long positionParty,
			@RequestBody PositionAddPayload posPayload,
			HttpServletResponse response) {

		// Creating temporary position manager model instance.
		PositionManagerModel posadd = new PositionManagerModel(
				positionParty,
				0,
				posPayload.getQuantity(),
				1, //TODO: In future versions, marketId will not default to 1, this will instead have to match the marketId of the payload
				posPayload.getInterval().getDtStart(),
				posPayload.getInterval().getDuration().getSeconds(),
				ResourceDesignator.ENERGY);
		//TODO: In future versions, change this to have the resource designator match the position payload.
		//As of this commit, energy is the only intended resource designator

		List<PositionManagerModel> queryresult = posDao.getPositionforUpdate(
				positionParty,
				posPayload.getInterval().getDtStart(),
				posPayload.getInterval().getDuration().getSeconds());
		
		logger.debug("/position/add (signed) " + posadd.toString());

		if (queryresult.isEmpty()) {
			// New row to be added.
			// Saving the position.
			PositionManagerModel temp = null;
			temp = posDao.save(posadd);	// invoke native

			// Return output decider.
			if (temp != null) {
				response.setStatus(HttpServletResponse.SC_OK);
				return "OK";
			} else {
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
				return "ERROR: Not able to store the data.";
			}
		} else {
			// Updating existing row.
			// Saving the position.
			int temp = 0;
			temp = posDao.updatePositionforDuration(
					positionParty,
					posPayload.getInterval().getDtStart(),
					posPayload.getInterval().getDuration().getSeconds(),
					posPayload.getQuantity());

			// Return output decider.
			if (temp == 1) {
				response.setStatus(HttpServletResponse.SC_OK);
				return "OK";
			} 
			else if(temp > 1) {
				response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
				return "Multiple Rows affected.";
			}
			else {
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
				return "ERROR: Not able to store the data.";
			}
		}

	}

	@GetMapping("/position/{positionParty}/getPosition")
	public ArrayList<PositionGetPayload> getPosition(@PathVariable(value = "positionParty") long positionParty,
			@RequestBody Interval interval) {
		ArrayList<PositionGetPayload> dataList = new ArrayList<PositionGetPayload>();

		// Querying for data.
		List<PositionManagerModel> queryresult = posDao.getPositionforDuration(positionParty, interval.getDtStart(),
				interval.getDuration().getSeconds());
		
		logger.info("/position/" + positionParty +"/getPosition: " +
				"Interval " + interval.toString());

		// Generating response list.
		PositionGetPayload tpayload = null;
		Interval tinterval = null;

		for (PositionManagerModel tposmod : queryresult) {
			tinterval = new Interval(Duration.between(tposmod.getStartTime(), tposmod.getEndTime()).toMinutes(),
					tposmod.getStartTime());
			tpayload = new PositionGetPayload(tinterval, tposmod.getQuantity());

			// Adding to the dataList.
			dataList.add(tpayload);
		}
		return dataList;
	}

	@GetMapping("/position/requestPosition")
	public EiReplyPositionPayload requestPosition(@RequestBody EiRequestPositionPayload requestPositionPayload) {
		Interval interval = requestPositionPayload.getBoundingInterval();
		Long positionParty = requestPositionPayload.getPositionParty().value();
		Long requestor = requestPositionPayload.getRequestor().value();
		Long marketId = requestPositionPayload.getMarketId().value();
		Long requestId = requestPositionPayload.getRequestId().value();
		String resourceDesignator = requestPositionPayload.getResourceDesignator().name();

		List<PositionManagerModel> queryresult = posDao.getPositionforDuration(positionParty, interval.getDtStart(),
				interval.getDuration().getSeconds());
		
		logger.info("/position/requestPosition " +
				"Interval " + interval.toString());

		// Generating response list.
		PositionGetPayload tpayload = null;
		Interval tinterval = null;
		ArrayList<CtsStreamIntervalType> streamIntervals = new ArrayList<>();
		CtsStreamType ctsStreamType = new CtsStreamType(interval, streamIntervals, interval.getDtStart());
		int streamUid = 0;
		for (PositionManagerModel tposmod : queryresult) {
			tinterval = new Interval(Duration.between(tposmod.getStartTime(), tposmod.getEndTime()).toMinutes(),
					tposmod.getStartTime());
			tpayload = new PositionGetPayload(tinterval, tposmod.getQuantity());

			// Adding to the dataList.
			CtsStreamIntervalType tempStreamInterval = new CtsStreamIntervalType(0, tpayload.getQuantity(), streamUid);
			streamUid++;
			streamIntervals.add(tempStreamInterval);
		}

		//TODO: Update EiResponse if there are any errors
		EiReplyPositionPayload replyPositionPayload = new EiReplyPositionPayload(interval, requestPositionPayload.getPositionParty(), ctsStreamType, requestPositionPayload.getRequestor(), new EiResponse(200, "OK"));

		return replyPositionPayload;
	}
	
	

}
