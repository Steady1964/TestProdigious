package com.test.controller;

import java.util.List;

import org.json.JSONObject;
import org.json.XML;
// import org.json.JSONObject;
// import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.test.model.Festivities;
import com.test.service.FestivitiesService;

/**
 * @author johan vargas
 */
@RestController
public class AppRestController {

	// Service which will do all data retrieval manipulation work
	@Autowired
	FestivitiesService festivitiesService;

	/**
	 * Retrieve all festivities
	 * @return ResponseEntity<List<Festivities>> with all result of festivities
	 */
	@RequestMapping(value = "/festivitie/", method = RequestMethod.GET)
	public ResponseEntity<List<Festivities>> listAllFestivities() {
		List<Festivities> festivities = festivitiesService.findAllFestivities();
		if (festivities.isEmpty()) {
			return new ResponseEntity<List<Festivities>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Festivities>>(festivities,
				HttpStatus.OK);
	}

	/**
	 * Retrieve single Festivities for id
	 * @param id Id of the festivitie of DB
	 * @param format request param format indicates if is XML or JSON
	 * @return ResponseEntity<Object> Response Object with the response media
	 * type
	 */
	@RequestMapping(value = "/festivitie/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Object> getFestivitie(@PathVariable("id") long id,
			@RequestParam String format) {
		System.out.println("Fetching Festitivitie with id " + id);
		HttpHeaders httpHeaders = new HttpHeaders();
		Festivities festivitie = festivitiesService.findById(id);
		if (format.equals("json")) {
			System.out.println("Ok JSON");
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		} else {
			return etlResponseForXmlCharge(festivitie, httpHeaders);
		}
		if (festivitie == null) {
			System.out.println("Festivitie with id " + id + " not found");
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(festivitie, httpHeaders,
				HttpStatus.OK);
	}

	/**
	 * Create a Festivitie
	 * @param festivitie
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(value = "/festivitie/", method = RequestMethod.POST)
	public ResponseEntity<Void> createFestivitie(
			@RequestBody Festivities festivitie,
			UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Festivitie " + festivitie.getName());

		if (festivitiesService.isFestivitieExist(festivitie)) {
			System.out.println("A Festivitie with name " + festivitie.getName()
					+ " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		festivitiesService.saveFestivitie(festivitie);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/festivitie/{id}")
				.buildAndExpand(festivitie.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update a Festivitie by Id
	 * @param id
	 * @param festivitie
	 * @return
	 */
	@RequestMapping(value = "/festivitie/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Festivities> updateFestivitie(
			@PathVariable("id") long id, @RequestBody Festivities festivitie) {
		System.out.println("Updating festivitie " + id);

		Festivities currentFestivitie = festivitiesService.findById(id);

		if (currentFestivitie == null) {
			System.out.println("Festivitie with id " + id + " not found");
			return new ResponseEntity<Festivities>(HttpStatus.NOT_FOUND);
		}

		currentFestivitie.setName(festivitie.getName());
		currentFestivitie.setDateEnd(festivitie.getDateEnd());
		currentFestivitie.setDateStart(festivitie.getDateStart());

		festivitiesService.updateFestivitie(currentFestivitie);
		return new ResponseEntity<Festivities>(currentFestivitie,
				HttpStatus.OK);
	}

	/**
	 * Delete Festivitie
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/festivitie/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Festivities> deleteFestivitie(
			@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Festivitie with id " + id);

		Festivities festivitie = festivitiesService.findById(id);
		if (festivitie == null) {
			System.out.println("Unable to delete. Festivitie with id " + id
					+ " not found");
			return new ResponseEntity<Festivities>(HttpStatus.NOT_FOUND);
		}

		festivitiesService.deleteByFestivitieId(id);
		return new ResponseEntity<Festivities>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete all festivities
	 * @return
	 */
	@RequestMapping(value = "/festivitie/", method = RequestMethod.DELETE)
	public ResponseEntity<Festivities> deleteAllFestivities() {
		System.out.println("Deleting All Festivities");

		festivitiesService.deleteAllFestivities();
		return new ResponseEntity<Festivities>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Return Response in Xml
	 * @return ResponseEntity<Object> Response With Object XML
	 */
	public ResponseEntity<Object> etlResponseForXmlCharge(
			Festivities festivitie, HttpHeaders httpHeaders) {

		String aux[] = festivitie.toString().split("Festivitie");
		System.out.println("Ok Xml: " + aux[aux.length - 1]);
		JSONObject json = new JSONObject(aux[aux.length - 1]);
		String xml = "<festivitie>" + XML.toString(json) + "</festivitie>";
		System.out.println("xml data: \n" + xml);
		httpHeaders.setContentType(MediaType.APPLICATION_XML);
		return new ResponseEntity<Object>(xml, httpHeaders, HttpStatus.OK);
	}

}