package jwd.stanica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.stanica.model.Prevoznik;
import jwd.stanica.repository.PrevoznikRepository;
import jwd.stanica.service.PrevoznikService;

@Service
public class JpaPrevoznikService implements PrevoznikService {

	@Autowired
	private PrevoznikRepository prevoznikRepository;
	
	@Override
	public Prevoznik findOne(Long id) {
		return prevoznikRepository.findOne(id);
	}

	@Override
	public List<Prevoznik> findAll() {
		return prevoznikRepository.findAll();
	}
	
	@Override
	public Prevoznik save(Prevoznik prevoznik) {
		return prevoznikRepository.save(prevoznik);
	}

	@Override
	public Prevoznik delete(Long id) {
		Prevoznik prevoznik = prevoznikRepository.findOne(id);
		if(prevoznik != null){
			prevoznikRepository.delete(prevoznik);
		}
		
		return prevoznik;
	}	
}