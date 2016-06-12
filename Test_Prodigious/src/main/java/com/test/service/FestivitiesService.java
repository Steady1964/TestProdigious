package com.test.service;

import java.util.List;

import com.test.model.Festivities;



public interface FestivitiesService {
	
	Festivities findById(long id);
	
	Festivities findByName(String name);
	
	void saveFestivitie(Festivities user);
	
	void updateFestivitie(Festivities festivitie);
	
	void deleteByFestivitieId(long id);

	List<Festivities> findAllFestivities(); 
	
	void deleteAllFestivities();
	
	public boolean isFestivitieExist(Festivities user);
	
}
