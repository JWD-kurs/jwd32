package jwd.wafepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByFirstNameContainingAndLastNameContaining(
			String firstname, String lastname);
	
	// ovakav upit ne bi radio, jer se polje u klasi User
	// ne zove firstname, već firstName
	
	//List<User> findByFirstnameContainingAndLastnameContaining(
	//		String firstname, String lastname);
}
