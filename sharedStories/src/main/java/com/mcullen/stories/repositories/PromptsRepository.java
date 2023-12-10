package com.mcullen.stories.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mcullen.stories.models.Prompts;
@Repository
public interface PromptsRepository extends CrudRepository<Prompts, Long> {
//Gets ALL Prompts from the DB
	List<Prompts> findAll();

	Optional<Prompts> findById(Prompts id);
}
