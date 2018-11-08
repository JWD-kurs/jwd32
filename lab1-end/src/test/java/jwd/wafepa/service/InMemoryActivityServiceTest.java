package jwd.wafepa.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

public class InMemoryActivityServiceTest {
	private ActivityService activityService;
	
	@Before
	public void setup() {
		activityService = new InMemoryActivityService();
		
		activityService.save(new Activity("Swimming"));
		activityService.save(new Activity("Running"));
	}
	
	@Test
	public void testFindOne() {
		Activity a = activityService.findOne(2L);
		
		Assert.assertEquals("Running", a.getName());
	}
	
	@Test
	public void testFindAll() {
		List<Activity> a = activityService.findAll();
		
		Assert.assertEquals(2, a.size());
	}
}
