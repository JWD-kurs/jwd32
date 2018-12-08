package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Prevoznik;
import jwd.stanica.service.PrevoznikService;
import jwd.stanica.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDTOToPrevoznik implements Converter<PrevoznikDTO, Prevoznik>{

	@Autowired
	private PrevoznikService prevoznikService;

	@Override
	public Prevoznik convert(PrevoznikDTO prevoznikDTO) {
		

			Prevoznik prevoznik = null;
			
			if(prevoznikDTO.getId() != null) {
				prevoznik = prevoznikService.findOne(prevoznikDTO.getId());
			}
			else {
				prevoznik = new Prevoznik();
			}
			
			prevoznik.setNaziv(prevoznikDTO.getNaziv());
			prevoznik.setAdresa(prevoznikDTO.getAdresa());
			prevoznik.setPib(prevoznikDTO.getPib());

			
			return prevoznik;
	}

	public List<Prevoznik> convert(List<PrevoznikDTO> prevoznikDTOs){
		List<Prevoznik> ret = new ArrayList<>();
		
		for(PrevoznikDTO prevoznikDTO : prevoznikDTOs){
			ret.add(convert(prevoznikDTO));
		}
		
		return ret;
	}
}