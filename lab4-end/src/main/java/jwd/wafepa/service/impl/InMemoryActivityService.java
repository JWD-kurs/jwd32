package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;

import org.springframework.stereotype.Service;

//@Service
public class InMemoryActivityService implements ActivityService {

	private Map<Long, Activity> activities = new HashMap<>();
	private long nextId = 1L;

	@Override
	public Activity findOne(Long id) {
		return activities.get(id);
	}

	@Override
	public List<Activity> findAll() {
		return new ArrayList<Activity>(activities.values());
	}

	@Override
	public Activity save(Activity activity) {
		if (activity.getId() == null) {
			activity.setId(nextId++);
		}
		activities.put(activity.getId(), activity);
		return activity;
	}

	@Override
	public Activity delete(Long id) {
		Activity activity = activities.get(id);
		if(activity!=null){
			activities.remove(id);
		}else{
			return null;
		}
		return activity;
	}

	@Override
	public List<Activity> findByName(String name) {
		List<Activity> ret = new ArrayList<>();
		
		for(Activity a : activities.values()){
			if(name.equalsIgnoreCase(a.getName())){
				ret.add(a);
			}
		}
						
		
		return ret;
	}

	@Override
	public List<Activity> save(List<Activity> activities) {
		List<Activity> ret = new ArrayList<>();
		
		for(Activity a: activities){
			//za svaku prosleđenu aktivnost pozivamo save
			//metodu koju smo već napravili i testirali - 
			//ona će sepobrinuti i za dodelu ID-eva 
			//ako je to potrebno
			Activity saved = save(a);
			
			//uspešno snimljene dodajemo u listu za vraćanje
			if(saved!=null){
				ret.add(saved);
			}
		}
		
		return ret;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id: ids){
			//pozivamo postojeću metodu za svaki
			delete(id);
		}
	}


}
