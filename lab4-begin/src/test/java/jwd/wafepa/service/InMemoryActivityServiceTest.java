package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryActivityServiceTest {
	private ActivityService activityService;
	
	@Before
	public void setUp(){
		activityService = new InMemoryActivityService();
		
		activityService.save(new Activity("Swimming"));
		activityService.save(new Activity("Running"));
	}
	
	
	@Test
	public void testFindOne(){
		Activity activity = activityService.findOne(1L);
		Assert.assertNotNull(activity);
		Assert.assertEquals("Swimming", activity.getName());
	}
	
	@Test
	public void testFindAll(){
		List<Activity> activities = activityService.findAll();
		Assert.assertNotNull(activities);
		Assert.assertEquals(2, activities.size());
	}
	
}
