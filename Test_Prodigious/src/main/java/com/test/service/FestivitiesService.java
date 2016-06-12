package main.java.com.test.service;

import java.util.List;

import main.java.com.test.model.Festivities;



public interface FestivitiesService {
	
	Festivities findById(long id);
	
	Festivities findByName(String name);
	
	void saveUser(Festivities user);
	
	void updateUser(Festivities user);
	
	void deleteUserById(long id);

	List<Festivities> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(Festivities user);
	
}
