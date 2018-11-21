package jwd.wafepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Address;
import jwd.wafepa.model.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	List<Address> findByUserId(Long userId);
	
	List<Address> findByUser(User user);

}
