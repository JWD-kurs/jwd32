package jwd.stanica.service;

import org.springframework.data.domain.Page;

import jwd.stanica.model.Linija;
import jwd.stanica.model.Rezervacija;

public interface LinijaService {
	Linija findOne(Long id);
	Page<Linija> findAll(int pageNum);
	Page<Linija> search(String destinacija, Long prevoznikId, Double maksCena, int pageNum);
	Linija save(Linija linija);
	Linija delete(Long id);
	Rezervacija reserve(Long id);
}