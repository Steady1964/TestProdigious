package main.java.com.test.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.com.test.model.Festivities;

@Service("festivitiesService")
@Transactional
public class FestivitiesServiceImpl implements FestivitiesService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Festivities> festivities;
	
	static{
		festivities= populateDummyUsers();
	}

	public List<Festivities> findAllUsers() {
		return festivities;
	}
	
	public Festivities findById(long id) {
		for(Festivities festivitie : festivities){
			if(festivitie.getId() == id){
				return festivitie;
			}
		}
		return null;
	}
	
	public Festivities findByName(String name) {
		for(Festivities festivitie : festivities){
			if(festivitie.getName().equalsIgnoreCase(name)){
				return festivitie;
			}
		}
		return null;
	}
	
	public void saveUser(Festivities festivitie) {
		festivitie.setId(counter.incrementAndGet());
		festivities.add(festivitie);
	}

	public void updateUser(Festivities festivitie) {
		int index = festivities.indexOf(festivitie);
		festivities.set(index, festivitie);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<Festivities> iterator = festivities.iterator(); iterator.hasNext(); ) {
			Festivities festivitie = iterator.next();
		    if (festivitie.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(Festivities festivitie) {
		return findByName(festivitie.getName())!=null;
	}
	
	public void deleteAllUsers(){
		festivities.clear();
	}

	private static List<Festivities> populateDummyUsers(){
		List<Festivities> festivitie = new ArrayList<Festivities>();
		/*
		festivitie.add(new Festivities(counter.incrementAndGet(),"Sam",30, 70000, ));
		festivitie.add(new Festivities(counter.incrementAndGet(),"Tom",40, 50000));
		festivitie.add(new Festivities(counter.incrementAndGet(),"Jerome",45, 30000));
		festivitie.add(new Festivities(counter.incrementAndGet(),"Silvia",50, 40000));
		*/
		return festivitie;
	}

}
