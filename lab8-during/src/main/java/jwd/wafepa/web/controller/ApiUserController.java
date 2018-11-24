package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jwd.wafepa.model.Record;
import jwd.wafepa.model.User;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;
import jwd.wafepa.support.RecordToRecordDTO;
import jwd.wafepa.support.UserDTOToUser;
import jwd.wafepa.support.UserToUserDTO;
import jwd.wafepa.web.dto.RecordDTO;
import jwd.wafepa.web.dto.UserDTO;
import jwd.wafepa.web.dto.UserRegistrationDTO;

@Controller
@RequestMapping(value="/api/users")
public class ApiUserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private UserDTOToUser toUser;
	
	@Autowired
	private UserToUserDTO toDto;
	
	@Autowired
	private RecordToRecordDTO toRecordDTO;
	

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<UserDTO>> getUser(
			@RequestParam(defaultValue="0") int page){
		
		List<User> users;
		Page<User> usersPage = userService.findAll(page);
		users = usersPage.getContent();
		
		if(users == null || users.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(users), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<UserDTO> getUser(@PathVariable Long id){
		User user = userService.findOne(id);
		if(user==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				toDto.convert(user),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<User> delete(@PathVariable Long id){
		userService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<UserDTO> add(
			@Validated @RequestBody UserRegistrationDTO newUser){
		if(newUser.getPassword()==null 
				|| newUser.getPassword().isEmpty()
				|| !newUser.getPassword().equals(newUser.getPasswordConfirm())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setEmail(newUser.getEmail());
		user.setPassword(newUser.getPassword());
		user.setFirstName(newUser.getFirstname());
		user.setLastName(newUser.getLastname());
		
		User savedUser = userService.save(user);
		
		return new ResponseEntity<>(
				toDto.convert(savedUser), 
				HttpStatus.CREATED);
	}
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<UserDTO> edit(
			@RequestBody UserDTO user,
			@PathVariable Long id){
		
		if(id!=user.getId()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User persisted = userService.save(toUser.convert(user));
		
		return new ResponseEntity<>(
				toDto.convert(persisted),
				HttpStatus.OK);
	}
	
	@RequestMapping(value= "{id}/records", method=RequestMethod.GET)
	ResponseEntity<List<RecordDTO>> getUsersRecords(
			@PathVariable Long id, @RequestParam(defaultValue="0") int pageNum){
		
		Page<Record> recordsPage = recordService.findByUserId(id, pageNum);
		
		if(recordsPage == null || recordsPage.getContent().isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(recordsPage.getTotalPages()) );
		
		return new ResponseEntity<>(
				toRecordDTO.convert(recordsPage.getContent()),
				headers,
				HttpStatus.OK);
	}
	
}
