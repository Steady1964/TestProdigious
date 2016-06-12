package com.test.repo;



import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.test.model.Festivities;

public interface FestivitiesContext extends CrudRepository<Festivities, Long>
{
		@Query("{'name' : ?0}")
		public Iterable<Festivities> searchByName(String name);
		
}
