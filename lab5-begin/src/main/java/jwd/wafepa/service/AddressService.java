package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Address;

public interface AddressService {
	/**
	 * Returns an address with specified ID.
	 * @param id ID of the address
	 * @return Address, if address with such ID
	 * exists, {@code null} if address is not found.
	 */
	Address findOne(Long id);
	
	/**
	 *  
	 * @return List of all existing addresses.
	 */
	List<Address> findAll();
	
	/**
	 * Persists an address. If address's id is null,
	 * a new id will be assigned automatically.
	 * @param address
	 * @return Address state after persisting. 
	 */
	Address save(Address address);
	

	/**
	 * Deletes an address having specified ID.
	 * @param id ID of the address to be removed. 
	 */
	void delete(Long id);
	

	
}
