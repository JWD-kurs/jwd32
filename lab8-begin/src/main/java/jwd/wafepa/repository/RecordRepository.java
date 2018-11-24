package jwd.wafepa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{

	Page<Record> findByUserId(Long id, Pageable page);
}
