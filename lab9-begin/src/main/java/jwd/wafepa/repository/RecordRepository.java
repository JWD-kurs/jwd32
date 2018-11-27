package jwd.wafepa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{

	Page<Record> findByUserId(Long id, Pageable page);
	
	@Query("SELECT r FROM Record r WHERE "
			+ "(:activityName IS NULL or r.activity.name like :activityName ) AND "
			+ "(:minDuration IS NULL OR r.duration >= :minDuration) AND "
			+ "(:intensity IS NULL or r.intensity like :intensity ) "
			)
	Page<Record> search(
			@Param("activityName") String activityName, 
			@Param("minDuration") Integer minDuration, 
			@Param("intensity") String intensity,
			Pageable pageRequest);
}
