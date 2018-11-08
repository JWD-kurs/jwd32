package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Activity;

public interface ActivityService {
	Activity findOne(Long id);
	List<Activity> findAll();
	Activity save(Activity activity);
	Activity delete(Long id);
}
