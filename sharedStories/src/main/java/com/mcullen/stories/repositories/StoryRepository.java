package com.mcullen.stories.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mcullen.stories.models.Story;

@Repository
public interface StoryRepository extends CrudRepository<Story, Long> {
	//Gets ALL Prompts from the DB
	List<Story> findAll();
	Optional<Story> findById(Story Id);

}
