package info.markott.repository;

import info.markott.domain.Component;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by mark on 9/9/16.
 */
public interface ComponentRepository extends CrudRepository<Component, Integer> {

	public Optional<Component> findByName(String name);
}