/*
 * 
 * Updated by: Dhruvin Desai
 * 
 */

package org.theenergymashuplab.cts.controller.payloads;

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
//	@RequestMapping("/position")
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
				posPayload.getInterval().getDtStart(),
				posPayload.getInterval().getDuration().getSeconds());

		List<PositionManagerModel> queryresult = posDao.getPositionforUpdate(
				positionParty,
				posPayload.getInterval().getDtStart(),
				posPayload.getInterval().getDuration().getSeconds());
		
		logger.info(posadd.toString());

		if (queryresult.isEmpty()) {
			// New row to be added.
			// Saving the position.
			PositionManagerModel temp = null;
			temp = posDao.save(posadd);

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

}
