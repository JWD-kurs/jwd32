package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;

public class InMemoryActivityService implements ActivityService{
	private Map<Long, Activity> activities = new HashMap<>();

	private long nextId = 1L;
	
	@Override
	public Activity findOne(Long id) {
		return activities.get(id);
	}

	@Override
	public List<Activity> findAll() {
//		List<Activity> retVal = new ArrayList<>();
//		
//		for(Activity a : activities.values()) {
//			retVal.add(a);
//		}
//		
//		return retVal;
		
		// return (List<Activity>) activities.values(); //ovo ne radi :(
		
		return new ArrayList<Activity>(activities.values());
	}

	@Override
	public Activity save(Activity activity) {
		if(activity.getId() == null) {
			activity.setId(nextId);
			nextId += 1L;
		}
		activities.put(activity.getId(), activity);
		
		return activity;
	}

	@Override
	public Activity delete(Long id) {
		if(!activities.containsKey(id)) {
			throw new IllegalArgumentException("Tried to "
					+ "remove non-existant activity.");
		}
		
		return activities.remove(id);
	}

}
