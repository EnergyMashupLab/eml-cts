/*
 * 
 * Updated by: Dhruvin Desai
 * 
 */

package org.theenergymashuplab.cts.dao;

import java.time.Instant;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theenergymashuplab.cts.model.PositionManagerModel;
import org.theenergymashuplab.cts.repository.PositionRepository;
import org.theenergymashuplab.cts.model.PositionSummaryModel;

@Service
public class PositionService {
	
	@Autowired
	PositionRepository posRepo;
	
	// New Stuff TC
	public List<PositionSummaryModel> getPositionTotalforDuration(long positionParty, Instant sTime, long durationinSeconds) {
		return posRepo.getPositionTotalforDuration(positionParty, sTime, sTime.plusSeconds(durationinSeconds));
	}
	
	public PositionManagerModel save(@Valid PositionManagerModel pos) {
		return posRepo.save(pos);	// fault
	}
	
	public PositionManagerModel getStatus(long id){
		return posRepo.getStatus(id);
	}
	
	public List<PositionManagerModel> getPositionforDuration(long positionParty, Instant sTime, long durationinSeconds) {
		return posRepo.getPositionforDuration(positionParty, sTime, sTime.plusSeconds(durationinSeconds));
	}
	
//	public List<PositionManagerModel> getPositionforUpdate(long positionParty, Instant sTime, long durationinSeconds) {
//		return posRepo.getPositionforUpdate(positionParty, sTime, sTime.plusSeconds(durationinSeconds));
//	}
	
//	public int updatePositionforDuration(long positionParty, Instant sTime, long durationinSeconds, long quantity) {
//		return posRepo.updatePositionforDuration(positionParty, sTime, sTime.plusSeconds(durationinSeconds), quantity);
//	}
}