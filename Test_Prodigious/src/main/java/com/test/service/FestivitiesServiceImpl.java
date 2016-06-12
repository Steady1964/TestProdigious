package com.test.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.Festivities;

@Service("festivitiesService")
@Transactional
public class FestivitiesServiceImpl implements FestivitiesService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Festivities> festivities;

	static {
		festivities = populateDummyFestivities();
	}

	public List<Festivities> findAllFestivities() {
		return festivities;
	}

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
		festivitie.setId(counter.incrementAndGet());
		festivities.add(festivitie);
	}

	public void updateFestivitie(Festivities festivitie) {
		int index = festivities.indexOf(festivitie);
		festivities.set(index, festivitie);
	}

	public void deleteByFestivitieId(long id) {

		for (Iterator<Festivities> iterator = festivities.iterator(); iterator
				.hasNext();) {
			Festivities festivitie = iterator.next();
			if (festivitie.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isFestivitieExist(Festivities festivitie) {
		return findByName(festivitie.getName()) != null;
	}

	public void deleteAllFestivities() {
		festivities.clear();
	}

	private static List<Festivities> populateDummyFestivities() {
		List<Festivities> festivitie = new ArrayList<Festivities>();

		festivitie.add(new Festivities(counter.incrementAndGet(), "Sam",
				"bogota", "11/08/2016", "11/08/2016"));
		festivitie.add(new Festivities(counter.incrementAndGet(), "Tom",
				"bogota", "11/08/2016", "11/08/2016"));
		festivitie.add(new Festivities(counter.incrementAndGet(), "Jerome",
				"bogota", "11/08/2016", "11/08/2016"));
		festivitie.add(new Festivities(counter.incrementAndGet(), "Silvia",
				"bogota", "11/08/2016", "11/08/2016"));

		return festivitie;
	}

}
