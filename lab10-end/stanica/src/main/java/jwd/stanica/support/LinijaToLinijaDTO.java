package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Linija;
import jwd.stanica.web.dto.LinijaDTO;

@Component
public class LinijaToLinijaDTO 
	implements Converter<Linija, LinijaDTO> {

	@Override
	public LinijaDTO convert(Linija linija) {
		if(linija==null){
			return null;
		}
		
		LinijaDTO dto = new LinijaDTO();
		
		dto.setId(linija.getId());
		dto.setBrojMesta(linija.getBrojMesta());
		dto.setCenaKarte(linija.getCenaKarte());
		dto.setVremePolaska(linija.getVremePolaska());
		dto.setDestinacija(linija.getDestinacija());
		
		dto.setPrevoznikId(linija.getPrevoznik().getId());
		dto.setPrevoznikNaziv(linija.getPrevoznik().getNaziv());
		
		return dto;
	}
	
	public List<LinijaDTO> convert(List<Linija> linije){
		List<LinijaDTO> dtoList = new ArrayList<>();
		
		for(Linija linija: linije){
			dtoList.add(convert(linija));
		}
		
		return dtoList;
	}

}