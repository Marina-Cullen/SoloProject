package com.mcullen.stories.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mcullen.stories.models.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
//Gets all the Users from the DB
	List<User> findAll();
	
//Will allow us to get User by email IF POSSIBLE
	Optional<User> findByEmail(String email);
}
