package info.markott.repository;

import info.markott.domain.Device;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mark on 9/7/16.
 */
public interface DeviceRepository extends CrudRepository<Device, Integer> {

}
