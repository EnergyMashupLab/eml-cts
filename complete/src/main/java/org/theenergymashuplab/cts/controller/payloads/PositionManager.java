/*
 * written by: Dhruvin Desai 202005
 */
package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.Interval;
import org.theenergymashuplab.cts.model.PositionSummaryModel;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.theenergymashuplab.cts.dao.PositionService;
import org.theenergymashuplab.cts.model.PositionManagerModel;

@RestController
public class PositionManager {
	private static final Logger logger = LogManager.getLogger(PositionManager.class);

	@Autowired
	PositionService posDao;

	// 	add to a position - new version adds a row for each position change and
	//	stores counterparty and unit price
	@PostMapping("/position/{positionParty}/add")
	public String createPosition(@PathVariable(value = "positionParty") long positionParty,
			@RequestBody PositionAddPayload posPayload,
			HttpServletResponse response) {

		// Creating temporary position manager model instance.
		PositionManagerModel posadd = new PositionManagerModel(
				positionParty,
				0,
				posPayload.getQuantity(),
				posPayload.getInterval().getDtStart(),
				posPayload.getInterval().getDuration().getSeconds(),
				0,
				0);

//		List<PositionManagerModel> queryresult = posDao.getPositionforUpdate(
//				positionParty,
//				posPayload.getInterval().getDtStart(),
//				posPayload.getInterval().getDuration().getSeconds());
		
		logger.info("/position/add (signed) " + posadd.toString());

//		if (queryresult.isEmpty()) {
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
//		} else {
//			// Updating existing row.
//			// Saving the position.
//			int temp = 0;
//			temp = posDao.updatePositionforDuration(
//					positionParty,
//					posPayload.getInterval().getDtStart(),
//					posPayload.getInterval().getDuration().getSeconds(),
//					posPayload.getQuantity());
//
//			// Return output decider.
//			if (temp == 1) {
//				response.setStatus(HttpServletResponse.SC_OK);
//				return "OK";
//			} 
//			else if(temp > 1) {
//				response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
//				return "Multiple Rows affected.";
//			}
//			else {
//				response.setStatus(HttpServletResponse.SC_ACCEPTED);
//				return "ERROR: Not able to store the data.";
//			}
//		}
	}

	//	return position - adds up rows in table for positionParty and interval
	//	Returns a single value as first (and only) item of list.
	//	TODO consider extension for multiple intervals
	@GetMapping("/position/{positionParty}/getPosition")
	public ArrayList<PositionGetPayload> getPosition(@PathVariable(value = "positionParty") long positionParty,
			@RequestBody Interval interval) {
		
		ArrayList<PositionGetPayload> dataList = new ArrayList<PositionGetPayload>();

		// Querying for data.
		
		// TODO parameters for getPositionTotalforDuration are wrong?
		Instant startTime, endTime;	// for the interval
		startTime = interval.getDtStart();
		endTime = startTime.plus(interval.getDuration());	// TODO verify
		List<PositionSummaryModel> queryresult = posDao.getPositionTotalforDuration(positionParty, startTime,
				interval.getDuration().getSeconds());
		
		logger.info("/position/" + positionParty +"/getPosition: " + interval.toString() + queryresult.size());

		// Generating response list.
		PositionGetPayload tpayload = null;
		Interval tinterval = null;

		for (PositionSummaryModel tposmod : queryresult) {
			tinterval = new Interval(tposmod.getDurationInMinutes(), tposmod.getStartTime());
			tpayload = new PositionGetPayload(tinterval, tposmod.getTotalQuantity());

			// Adding to the dataList.
			dataList.add(tpayload);
		}
		return dataList;
	}

}
