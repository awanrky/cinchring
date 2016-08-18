package info.markott.controller;

import info.markott.domain.Reading;
import info.markott.repository.ReadingRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;

/**
 * Created by mark on 8/16/16.
 */

@RestController
public class ReadingController {

	@Inject
	private ReadingRepository readingRepository;

	@RequestMapping(value="/readings", method= RequestMethod.GET)
	public ResponseEntity<Iterable<Reading>> getAllReadings() {
		Iterable<Reading> allReadings = readingRepository.findAll();
		return new ResponseEntity<>(allReadings, HttpStatus.OK);
	}

	@RequestMapping(value="/readings/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getReading(@PathVariable Long id) {
		Reading reading = readingRepository.findOne(id);
		return new ResponseEntity<> (reading, HttpStatus.OK);
	}

	@RequestMapping(value="/readings/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateReading(@RequestBody Reading reading, @PathVariable Long id) {
		Reading r = readingRepository.save(reading);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/readings/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteReading(@PathVariable Long id) {
		readingRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/readings", method=RequestMethod.POST)
	public ResponseEntity<?> createReading(@RequestBody Reading reading) {
		reading = readingRepository.save(reading);

		HttpHeaders responseHeaders = new HttpHeaders();
		URI newReadingUri = ServletUriComponentsBuilder
								.fromCurrentRequest()
								.path("/{id}")
								.buildAndExpand(reading.getId())
								.toUri();
		responseHeaders.setLocation(newReadingUri);

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
}