package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Address;
import jwd.wafepa.service.AddressService;
import jwd.wafepa.web.dto.AddressDTO;
@Component
public class AddressDTOToAddress 
	implements Converter<AddressDTO, Address> {
	
	@Autowired
	AddressService addressService;

	@Override
	public Address convert(AddressDTO dto) {
		Address address = new Address();
		
		if(dto.getId()!=null){
			address = addressService.findOne(dto.getId());
			
			if(address == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant address");
			}
		}
		
		address.setId(dto.getId());
		address.setStreat(dto.getStreat());
		address.setNumber(dto.getNumber());
		
		return address;
	}
	
	public List<Address> convert (List<AddressDTO> dtoAddresses){
		List<Address> addresses = new ArrayList<>();
		
		for(AddressDTO dto : dtoAddresses){
			addresses.add(convert(dto));
		}
		
		return addresses;
	}

}
