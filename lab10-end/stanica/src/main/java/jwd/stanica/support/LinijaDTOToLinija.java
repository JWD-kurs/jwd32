package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Linija;
import jwd.stanica.model.Prevoznik;
import jwd.stanica.service.LinijaService;
import jwd.stanica.service.PrevoznikService;
import jwd.stanica.web.dto.LinijaDTO;

@Component
public class LinijaDTOToLinija implements Converter<LinijaDTO, Linija>{

	@Autowired
	private LinijaService linijaService;

	@Autowired
	private PrevoznikService prevoznikService;
	
	@Override
	public Linija convert(LinijaDTO linijaDTO) {
		
		Prevoznik prevoznik = prevoznikService.findOne(linijaDTO.getPrevoznikId());

		if(prevoznik != null ){
			Linija linija = null;
			
			if(linijaDTO.getId() != null) {
				linija = linijaService.findOne(linijaDTO.getId());
			}
			else {
				linija = new Linija();
			}
			
			linija.setBrojMesta(linijaDTO.getBrojMesta());
			linija.setCenaKarte(linijaDTO.getCenaKarte());
			linija.setVremePolaska(linijaDTO.getVremePolaska());
			linija.setDestinacija(linijaDTO.getDestinacija());

			linija.setPrevoznik(prevoznik);
			
			return linija;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Linija> convert(List<LinijaDTO> linijaDTOs){
		List<Linija> ret = new ArrayList<>();
		
		for(LinijaDTO linijaDTO : linijaDTOs){
			ret.add(convert(linijaDTO));
		}
		
		return ret;
	}
}