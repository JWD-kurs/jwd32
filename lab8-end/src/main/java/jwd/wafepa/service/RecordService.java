package jwd.wafepa.service;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.Record;

public interface RecordService {
	/**
	 * Returns a record with specified ID.
	 * @param id ID of the record
	 * @return Record, if record with such ID
	 * exists, {@code null} if record is not found.
	 */
	Record findOne(Long id);
	
	/**
	 *  
	 * @return Page of all existing records.
	 */
	Page<Record> findAll(int pageNum);
	
	/**
	 * Persists a record. If record's id is null,
	 * a new id will be assigned automatically.
	 * @param record
	 * @return Record state after persisting. 
	 */
	Record save(Record record);
	
	/**
	 * Deletes a record having specified ID.
	 * @param id ID of the record to be removed. 
	 * @return Removed record if removal is successful. 
	 * If the record was not found, an {@link IllegalArgumentException}
	 * is thrown.
	 */
	Record delete(Long id);
	
	/**
	 * Returns records for specified user ID.
	 * @param id ID of the user
	 * @return Page of records, if record for such user ID
	 * exist, {@code null} if no records are not found.
	 */
	Page<Record> findByUserId(Long id, int pageNum);
}
