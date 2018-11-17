package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Address;
import jwd.wafepa.repository.AddressRepository;
import jwd.wafepa.service.AddressService;

@Service
public class JpaAddressService 
	implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address findOne(Long id) {
		
		return addressRepository.findOne(id);
	}

	@Override
	public List<Address> findAll() {
		
		return addressRepository.findAll();
	}

	@Override
	public Address save(Address address) {
		
		return addressRepository.save(address);
	}



	@Override
	public void delete(Long id) {
		addressRepository.delete(id);
	}


}
