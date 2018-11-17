package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Address;
import jwd.wafepa.web.dto.AddressDTO;

@Component
public class AddressToAddressDTO implements Converter<Address, AddressDTO> {

	@Override
	public AddressDTO convert(Address address) {
		AddressDTO dto = new AddressDTO();
		
		dto.setId(address.getId());
		dto.setNumber(address.getNumber());
		dto.setStreat(address.getStreat());
		
		return dto;
	}
	
	public List<AddressDTO> convert(List<Address> addresss){
		List<AddressDTO> ret = new ArrayList<>();
		
		for(Address address : addresss){
			ret.add(convert(address));
		}
		
		return ret;
	}
}
