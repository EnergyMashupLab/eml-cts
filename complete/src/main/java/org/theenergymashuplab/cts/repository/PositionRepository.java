package org.theenergymashuplab.cts.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.theenergymashuplab.cts.model.PositionManagerModel;

public interface PositionRepository extends JpaRepository<PositionManagerModel, Long>	{

	//	get all positions for positionParty
	@Query(nativeQuery = true, value = "select * from Position p where p.Position_id=:id")
	public PositionManagerModel getStatus(@Param("id") long id);
	
//	 seems to be what's meant by PositionService getPositionTotalforDuration
	// Summing Quantity in Database - TC
	@Query(nativeQuery = true, value = "select positionParty, sTime, sTime_plus_dur, SUM(quantity) AS `TotalQuantity from Position p where (p.position_Party = :positionParty) AND ((p.start_time <= :sTime AND p.end_time > :sTime) OR (p.start_time < :sTime_plus_dur AND p.start_time > :sTime))")
	public List<PositionManagerModel> getPositionforDuration(@Param("positionParty") long positionParty,
			@Param("sTime") Instant sTime,
			@Param("sTime_plus_dur") Instant sTime_plus_dur);
	
	// Summing Quantity in Database - TC
	@Query(nativeQuery = true, value = "select positionParty, sTime, sTime_plus_dur, SUM(quantity) AS `TotalQuantity from Position p where (p.position_Party = :positionParty) AND ((p.start_time <= :sTime AND p.end_time > :sTime) OR (p.start_time < :sTime_plus_dur AND p.start_time > :sTime))")
	public List<PositionManagerModel> getPositionTotalforDuration(@Param("positionParty") long positionParty,
			@Param("sTime") Instant sTime,
			@Param("sTime_plus_dur") Instant sTime_plus_dur);

	
	// NOT USED
	@Query(nativeQuery = true, value = "select * from Position p where p.position_Party = :positionParty AND start_time = :sTime AND end_time = :sTime_plus_dur")
	public List<PositionManagerModel> getPositionforUpdate(
			@Param("positionParty") long positionParty,
			@Param("sTime") Instant sTime,
			@Param("sTime_plus_dur") Instant sTime_plus_dur);
	
	// NOT USED
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update Position p set p.Quantity = p.Quantity + :newquantity where p.position_Party = :positionParty AND start_time = :sTime AND end_time = :sTime_plus_dur")
	public int updatePositionforDuration(
			@Param("positionParty") long positionParty,
			@Param("sTime") Instant sTime,
			@Param("sTime_plus_dur") Instant sTime_plus_dur,
			@Param("newquantity") long newquantity);
}
