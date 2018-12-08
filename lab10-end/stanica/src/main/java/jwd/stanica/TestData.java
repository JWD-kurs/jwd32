package jwd.stanica;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Prevoznik;
import jwd.stanica.service.PrevoznikService;

@Component
public class TestData {
	
	@Autowired
	private PrevoznikService ps;
	
	@PostConstruct
	public void init() {
		Prevoznik p = new Prevoznik();
		p.setNaziv("Lasta");
		p.setPib("12332111");
		p.setAdresa("Beogradski put 11, Beograd");
		
		ps.save(p);
		
		Prevoznik p2 = new Prevoznik();
		p2.setNaziv("Ekspres");
		p2.setPib("12332112");
		p2.setAdresa("Bajski put 1, Subotica");
		
		ps.save(p2);
		
		
	}
	
}
