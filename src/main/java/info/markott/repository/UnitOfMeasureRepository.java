package info.markott.repository;

import info.markott.domain.UnitOfMeasure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by mark on 9/5/16.
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Integer> {


	public Optional<UnitOfMeasure> findByName(String name);
}
