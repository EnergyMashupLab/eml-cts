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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@PostMapping("/position/requestPosition")
	public EiReplyPositionPayload requestPosition(@RequestBody EiRequestPositionPayload requestPositionPayload) {
		Interval interval = requestPositionPayload.getBoundingInterval();
		long positionParty = requestPositionPayload.getPositionParty().value();

		List<PositionManagerModel> queryResult = posDao.getPositionforDuration(positionParty, interval.getDtStart(),
				interval.getDuration().getSeconds());
		
		logger.info("/position/requestPosition " +
				"Interval " + interval.toString());

		CtsStreamType ctsStreamType = convertPositionsToStream(queryResult, interval);

		//TODO: Update EiResponse if there are any errors
		EiReplyPositionPayload replyPositionPayload = new EiReplyPositionPayload(interval, requestPositionPayload.getPositionParty(), ctsStreamType, requestPositionPayload.getRequestor(), new EiResponse(200, "OK"));
		return replyPositionPayload;
	}
	
	/*
	 * Returns a list of Instants between `startTime` and `endTime` with `duration` amount of time
	 * between each consecutive interval
	 */
	private List<Instant> divideInterval(Instant startTime, Instant endTime, Duration duration) {
		List<Instant> instants = new ArrayList<>();
		Instant curTime = startTime;
		while (curTime.isBefore(endTime)) {
			instants.add(curTime);
			curTime = curTime.plus(duration);
		}

		return instants;
	}

	/* Returns the smallest duration present in the list of models */
	private Duration findMinDuration(List<PositionManagerModel> models) {
		return models.stream()
			.map(m -> Duration.between(m.getStartTime(), m.getEndTime()))
			.min((d1, d2) -> d1.compareTo(d2))
			.orElse(Duration.ZERO);
	}

	/* Combines all the quantities for each given time slot present in the models */
	private Map<Instant, Long> mergePositions(List<PositionManagerModel> models) {
		Map<Instant, Long> mergedPositions = new HashMap<>();
		Duration minDuration = findMinDuration(models);
		for (PositionManagerModel model: models) {
			List<Instant> instants = divideInterval(model.getStartTime(), model.getEndTime(), minDuration);
			instants.stream()
				.forEach(i -> mergedPositions.merge(i, model.getQuantity(), Math::addExact));
		}

		return mergedPositions;
	}

	private CtsStreamType convertPositionsToStream(List<PositionManagerModel> models, Interval boundingInterval) {
		if (models.isEmpty()) {
			return new CtsStreamType(
				new Interval(0, boundingInterval.getDtStart()),
				List.of(),
				boundingInterval.dtStart
			);
		}

		List<CtsStreamIntervalType> streamIntervals = new ArrayList<>();
		Instant startTime = boundingInterval.getDtStart();
		Instant endTime = boundingInterval.getDtStart().plus(boundingInterval.getDuration());
		Duration minDuration = findMinDuration(models);
		Map<Instant, Long> mergedPositions = mergePositions(models);

		Instant curTime = startTime;
		int uid = 0;
		while (curTime.isBefore(endTime)) {
			long quantity = mergedPositions.getOrDefault(curTime, 0L);
			CtsStreamIntervalType interval = new CtsStreamIntervalType(0, quantity, uid++);
			streamIntervals.add(interval);
			curTime = curTime.plus(minDuration);
		}

		CtsStreamType stream = new CtsStreamType(new Interval(minDuration.toMinutes(), startTime), streamIntervals, startTime);
		return stream;
	}
}
