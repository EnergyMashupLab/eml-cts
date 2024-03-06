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
 * 
 * Updated by: Dhruvin Desai
 * 
 */

package org.theenergymashuplab.cts.dao;

import java.time.Instant;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theenergymashuplab.cts.model.PositionManagerModel;
import org.theenergymashuplab.cts.repository.PositionRepository;

@Service
public class PositionService {
	
	@Autowired
	PositionRepository posRepo;
	
	public PositionManagerModel save(@Valid PositionManagerModel pos) {
		return posRepo.save(pos);	// fault
	}
	
	public PositionManagerModel getStatus(long id){
		return posRepo.getStatus(id);
	}
	
	public List<PositionManagerModel> getPositionforDuration(long positionParty, Instant sTime, long durationinSeconds) {
		return posRepo.getPositionforDuration(positionParty, sTime, sTime.plusSeconds(durationinSeconds));
	}
	
	public List<PositionManagerModel> getPositionforUpdate(long positionParty, Instant sTime, long durationinSeconds) {
		return posRepo.getPositionforUpdate(positionParty, sTime, sTime.plusSeconds(durationinSeconds));
	}
	
	public int updatePositionforDuration(long positionParty, Instant sTime, long durationinSeconds, long quantity) {
		return posRepo.updatePositionforDuration(positionParty, sTime, sTime.plusSeconds(durationinSeconds), quantity);
	}
}
