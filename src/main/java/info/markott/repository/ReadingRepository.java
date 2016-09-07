package info.markott.repository;

import info.markott.domain.Reading;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mark on 8/16/16.
 */
public interface ReadingRepository extends CrudRepository<Reading, Long> {

}
