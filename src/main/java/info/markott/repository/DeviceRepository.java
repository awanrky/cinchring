package info.markott.repository;

import info.markott.domain.Device;
import info.markott.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by mark on 9/7/16.
 */
public interface DeviceRepository extends CrudRepository<Device, Integer> {

	public Optional<Device> findByName(String name);
}
