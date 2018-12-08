package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Rezervacija;
import jwd.stanica.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDTO 
	implements Converter<Rezervacija, RezervacijaDTO> {

	@Override
	public RezervacijaDTO convert(Rezervacija rezervacija) {
		if(rezervacija==null){
			return null;
		}
		
		RezervacijaDTO dto = new RezervacijaDTO();
		
		dto.setId(rezervacija.getId());
		
		return dto;
	}
	
	public List<RezervacijaDTO> convert(List<Rezervacija> rezervacije){
		List<RezervacijaDTO> dtoList = new ArrayList<>();
		
		for(Rezervacija rezervacija: rezervacije){
			dtoList.add(convert(rezervacija));
		}
		
		return dtoList;
	}

}