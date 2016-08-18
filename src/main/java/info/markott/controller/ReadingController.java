package info.markott.controller;

import info.markott.domain.Reading;
import info.markott.repository.ReadingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

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
}
