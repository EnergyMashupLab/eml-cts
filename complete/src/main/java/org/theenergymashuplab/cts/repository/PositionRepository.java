package org.theenergymashuplab.cts.repository;

import org.theenergymashuplab.cts.model.PositionSummaryModel;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// DEBUG
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;
import org.theenergymashuplab.cts.model.PositionManagerModel;

//@Transactional(readOnly = true)
public interface PositionRepository extends JpaRepository<PositionManagerModel, Long>	{

	//	get all positions for positionParty
	@Query(nativeQuery = true, value = "select * from Position p where p.Position_id=:id")
	public PositionManagerModel getStatus(@Param("id") long id);
	
	@Query(nativeQuery = true,
	value = "select position_party, start_time, sum(quantity) AS TotalQuantity from position p where p.position_party = :positionParty) AND ((p.start_time <= :sTime AND p.end_time > :sTime) OR (p.start_time < :sTime_plus_dur AND p.start_time > :sTime)) group by position_party, start_time order by position_party, start_time")
	public List<PositionManagerModel> getPositionforDuration(@Param("positionParty") long positionParty,
			@Param("sTime") Instant sTime,
			@Param("sTime_plus_dur") Instant sTime_plus_dur);
	
//	// Summing Quantity in Database - TC OLDER VERSION - FIXED QUERY SYNTAX wtc
//	@Query(nativeQuery = true,
//	value = "select positionParty, sTime, sTime_plus_dur, SUM(quantity) AS `TotalQuantity from Position p where (p.position_Party = :positionParty) AND ((p.start_time <= :sTime AND p.end_time > :sTime) OR (p.start_time < :sTime_plus_dur AND p.start_time > :sTime)) group by position_party, start_time, duration_minutes order by position_party, start_time, duration_minutes")
//	public List<PositionManagerModel> getPositionTotalforDuration(@Param("positionParty") long positionParty,
//			@Param("sTime") Instant sTime,
//			@Param("sTime_plus_dur") Instant sTime_plus_dur);

//	// Summing Quantity in Database - TC CORRECTED QUERY - changed comparison PREVIOUS ITERATION
//	@Query(nativeQuery = true, value = "select position_party, start_time, duration_minutes, SUM(quantity) AS TotalQuantity from position p where (p.position_party = :positionParty) AND ((p.start_time <= :sTime AND p.end_time > :sTime) OR (p.start_time < :sTime_plus_dur AND p.start_time > :sTime) group by position_party, start_time, duration_minutes order by position_party, start_time, duration_minutes")
//	public List<PositionManagerModel> getPositionTotalforDuration(@Param("positionParty") long positionParty,
//			@Param("sTime") Instant sTime,
//			@Param("eTime") Instant eTime);
	
	
	// Summing Quantity in Database  8pm
//	@Query(nativeQuery = true, value = "select position_party, start_time, duration_minutes, SUM(quantity) AS TotalQuantity from position p where ((p.position_party = :positionParty) AND (p.start_time >= :sTime AND p.end_time <= :eTime)) order by position_party, start_time, duration_minutes group by position_party, start_time, duration_minutes")
// 940am
//	@Query(nativeQuery = true, value = "select position_party, start_time, duration_minutes, SUM(quantity) AS TotalQuantity from position p where ((p.position_party = :positionParty) AND (p.start_time >= :sTime AND p.end_time <= :eTime)) order by position_party, start_time, duration_minutes ")

	// eliminate group by
//	@Query(nativeQuery = true, value = "select position_party, start_time, duration_minutes, SUM(quantity) AS TotalQuantity from position p where ((p.position_party = :positionParty) AND (p.start_time >= :sTime AND p.end_time <= :eTime)) order by position_party, start_time, duration_minutes ")

	//	getPositionTotalforDuration(positionParty, startTime, endTime) returns list of PositionSummaryModel
//	@Modifying
//	@Transactional(readOnly = true)
	@Query(nativeQuery = true, value = "select row_number() over() as id, " + "position_party, start_time, duration_minutes, SUM(quantity) " +
			"AS TotalQuantity from position p " +
			"where ((p.position_party = :positionParty) AND (p.start_time >= :sTime AND p.end_time <= :eTime)) " +
			"group by position_party, start_time, duration_minutes")

	public List<PositionSummaryModel> getPositionTotalforDuration
			(@Param("positionParty") long positionParty,
			@Param("sTime") Instant sTime,
			@Param("eTime") Instant eTime);
	
	// NOT USED
//	@Query(nativeQuery = true,
//	value = "select * from Position p where p.position_Party = :positionParty AND start_time = :sTime AND end_time = :sTime_plus_dur")
//	public List<PositionManagerModel> getPositionforUpdate(
//			@Param("positionParty") long positionParty,
//			@Param("sTime") Instant sTime,
//			@Param("sTime_plus_dur") Instant sTime_plus_dur);
	

//	@Modifying
//	@Transactional(readOnly = true)
//	@Query(nativeQuery = true, value = "update Position p set p.Quantity = p.Quantity + :newquantity where p.position_Party = :positionParty AND start_time = :sTime AND end_time = :sTime_plus_dur")
//	public int updatePositionforDuration(
//			@Param("positionParty") long positionParty,
//			@Param("sTime") Instant sTime,
//			@Param("sTime_plus_dur") Instant sTime_plus_dur,
//			@Param("newquantity") long newquantity);
}
