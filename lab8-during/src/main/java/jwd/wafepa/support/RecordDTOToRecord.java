package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Record;
import jwd.wafepa.model.User;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.RecordDTO;

@Component
public class RecordDTOToRecord implements Converter<RecordDTO, Record>{

	@Autowired
	private UserService userService;
	
	@Autowired
	private ActivityService activityService;
	
	@Override
	public Record convert(RecordDTO recordDTO) {
		
		User user = userService.findOne(recordDTO.getUserId());
		Activity activity = activityService.findOne(recordDTO.getActivityId());
		
		Record record = new Record();
		
		record.setId(recordDTO.getId());
		record.setTime(recordDTO.getTime());
		record.setDuration(recordDTO.getDuration());
		record.setIntensity(recordDTO.getIntensity());
		record.setDescription(recordDTO.getDescription());
		
		record.setUser(user);
		record.setActivity(activity);
		
		return record;
	}

	public List<Record> convert(List<RecordDTO> recordDTOs){
		List<Record> ret = new ArrayList<>();
		
		for(RecordDTO recordDTO : recordDTOs){
			ret.add(convert(recordDTO));
		}
		
		return ret;
	}
}
