package com.test.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
@XmlRootElement
@Document(collection = "festivities")
public class Festivities {

	/** The id. */
	@Id
	private long id;

	/** The name. */
	private String name;

	/** The place. */
	private String place;

	/** The dateStart. */
	private String dateStart;

	/** The date end. */
	private String dateEnd;

	/**
	 * Instantiates a new user.
	 */
	public Festivities() {
		id = 0;
	}

	@PersistenceConstructor
	public Festivities(long id, String name, String place, String dateStart, String dateEnd) {
		this.id = id;
		this.name = name;
		this.place = place;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getDateStart() {
		return dateStart;
	}


	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}


	public String getDateEnd() {
		return dateEnd;
	}


	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Festivities other = (Festivities) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Festivitie {id:" + id + ", name:" + name + ", place:" + place + ", start:" + dateStart + ", end:" + dateEnd + "}";
	}

}
