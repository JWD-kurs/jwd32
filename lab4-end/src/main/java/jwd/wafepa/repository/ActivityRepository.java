package jwd.wafepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Activity;

@Repository
public interface ActivityRepository 
	extends JpaRepository<Activity, Long>{


	List<Activity> findByName(String name);
	
	//@Query("SELECT a FROM Activity a WHERE a.name=:name1")
	//List<Activity> findByName(@Param("name1") String name);
}
