package info.markott.controller;

import info.markott.domain.Reading;
import info.markott.domain.UnitOfMeasure;
import info.markott.repository.ReadingRepository;
import info.markott.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.util.Optional;

/**
 * Created by mark on 9/5/16.
 */

@RestController
@RequestMapping("/api/readings")
public class ReadingController {

	@Inject
	private ReadingRepository readingRepository;

	@Inject
	UnitOfMeasureRepository unitOfMeasureRepository;

//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public Iterable<Reading> getAllReadings() {
//		return readingRepository.findAll();
//	}

	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Reading>> getAllReadings() {
		Iterable<Reading> allReadings = readingRepository.findAll();
		return new ResponseEntity<>(allReadings, HttpStatus.OK);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<?> createReading(@RequestBody Reading reading) {

		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByName(reading.getUom().getName());

		if (unitOfMeasure.isPresent()) {
			reading.setUom(unitOfMeasure.get());
		}

		reading = readingRepository.save(reading);

		HttpHeaders responseHeaders = new HttpHeaders();
		URI newReadingUri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(reading.getId())
				.toUri();
		responseHeaders.setLocation(newReadingUri);

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public Reading getReading(@PathVariable String id) {
//		return readingRepository.findReadingById(id);
//	}
}
