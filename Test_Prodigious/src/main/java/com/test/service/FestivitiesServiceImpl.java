package com.test.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.Festivities;
import com.test.repo.FestivitiesContext;

@Service("festivitiesService")
@Transactional
public class FestivitiesServiceImpl implements FestivitiesService {

	private static final AtomicLong counter = new AtomicLong();
	private static FestivitiesContext festivitieContext;

	private static List<Festivities> festivities;

	static {
		festivitieContext = getBeanContext();
		festivities = (List<Festivities>) festivitieContext.findAll();
	}

	public List<Festivities> findAllFestivities() {
		return festivities;
	}

	/*
	 * Method that retrieve a Festivitie by id
	 * @param id Id of the record
	 * @return return the Festivitie Object
	 */
	public Festivities findById(long id) {
		for (Festivities festivitie : festivities) {
			if (festivitie.getId() == id) {
				return festivitie;
			}
		}
		return null;
	}

	public Festivities findByName(String name) {
		for (Festivities festivitie : festivities) {
			if (festivitie.getName().equalsIgnoreCase(name)) {
				return festivitie;
			}
		}
		return null;
	}

	public void saveFestivitie(Festivities festivitie) {
		counter.set(festivities.size());
		festivitie.setId(counter.incrementAndGet());
		festivitieContext.save(festivitie);
		reloadFestivitiesList();
	}

	public void updateFestivitie(Festivities festivitie) {
		Festivities index = findById(festivitie.getId());
		festivitieContext.save(index);
	}

	public void deleteByFestivitieId(long id) {
		festivitieContext.delete(id);		
	}

	public boolean isFestivitieExist(Festivities festivitie) {
		return findByName(festivitie.getName()) != null;
	}

	public void deleteAllFestivities() {
		festivities.clear();
		festivitieContext.deleteAll();
	}

	public static ClassPathXmlApplicationContext getContextCRUD() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new ClassPathResource("spring-config.xml").getPath());
		return context;
	}

	public static FestivitiesContext getBeanContext() {
		ClassPathXmlApplicationContext context = getContextCRUD();
		FestivitiesContext festivitieContext = context
				.getBean(FestivitiesContext.class);
		return festivitieContext;
	}

	public void reloadFestivitiesList() {
		festivities.clear();
		festivities = (List<Festivities>) festivitieContext.findAll();
	}
}
