package info.markott.controller;

import info.markott.domain.Component;
import info.markott.domain.Device;
import info.markott.domain.Reading;
import info.markott.domain.UnitOfMeasure;
import info.markott.repository.ComponentRepository;
import info.markott.repository.DeviceRepository;
import info.markott.repository.ReadingRepository;
import info.markott.repository.UnitOfMeasureRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * Created by mark on 9/5/16.
 */


@RestController
@RequestMapping("/api/readings")
public class ReadingController {

	private final String DEGREES_CELSIUS = "degrees-celsius";

	@Inject
	private ReadingRepository readingRepository;

	@Inject
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Inject
	DeviceRepository deviceRepository;

	@Inject
	ComponentRepository componentRepository;

	/**
	 * returns a JSON response containing all the readings in the database.
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Reading>> getAllReadings() {
		Iterable<Reading> allReadings = readingRepository.findAll();
		return new ResponseEntity<>(allReadings, HttpStatus.OK);
	}

	@RequestMapping(value="/degrees-celsius/{startDate}/{endDate}", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Reading>> getDegreesCelsiusReadings(@PathVariable String startDate, @PathVariable String endDate) {
		DateTimeFormatter f = DateTimeFormatter.ISO_INSTANT;
		Instant startInstant = Instant.from(f.parse(startDate));
		Instant endInstant = Instant.from(f.parse(endDate));
		UnitOfMeasure unitOfMeasure = getUnitOfMeasure(DEGREES_CELSIUS);

		Iterable<Reading> readings = readingRepository.findByUom(unitOfMeasure, Date.from(startInstant), Date.from(endInstant));
		return new ResponseEntity<>(readings, HttpStatus.OK);
	}

	/**
	 * create a new reading
	 *
	 * the body of the post should be valid JSON with the following information:
	 *
	 * {
	 *     "value": "(String) the reading's value goes here",
	 *     "device": "(String or Object) the name of the device.  Can be an object with name and description properties, but only name is used unless a device with this name does not yet exist",
	 *     "component": "(String of Object) the name of the component.  Can be object for a component that does not yet exist",
	 *     "uom": "(String or Object) the name of the measurement used for obtaining the value.  Can be an object for a uom that does not yet exist"
	 * }
	 *
	 * @param reading
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<?> createReading(@RequestBody Reading reading) {

		Optional<UnitOfMeasure> unitOfMeasure = getUnitOfMeasureForReading(reading);

		Optional<Device> device = getDeviceForReading(reading);

		if (device.isPresent()) {
			reading.setDevice(device.get());
		} else {
			deviceRepository.save(reading.getDevice());
			device = Optional.of(reading.getDevice());
		}

		if (unitOfMeasure.isPresent()) {
			reading.setUom(unitOfMeasure.get());
		} else {
			reading.getUom().setCreatedByIfMissing(device.get());
		}

		Optional<Component> component = getComponentForReading(reading);

		if (component.isPresent()) {
			reading.setComponent(component.get());
		} else {
			reading.getComponent().setCreatedByIfMissing(device.get());
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

	private Optional<UnitOfMeasure> getUnitOfMeasureForReading(Reading reading) {
		UnitOfMeasure uom = reading.getUom();

		if (uom == null) {
			return Optional.empty();
		}

		return unitOfMeasureRepository.findByName(uom.getName());
	}

	private UnitOfMeasure getUnitOfMeasure(String name) {
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByName(name);

		if (!unitOfMeasureOptional.isPresent()) {
			return null;
		}
		return unitOfMeasureOptional.get();
	}

	private Optional<Device> getDeviceForReading(Reading reading) {
		Device device = reading.getDevice();

		if (device == null) {
			return Optional.empty();
		}

		return deviceRepository.findByName(device.getName());
	}

	private Optional<Component> getComponentForReading(Reading reading) {
		Component component = reading.getComponent();

		if (component == null) {
			return Optional.empty();
		}

		return componentRepository.findByName(component.getName());
	}
}
