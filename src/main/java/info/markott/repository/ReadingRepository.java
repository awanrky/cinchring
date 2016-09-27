package info.markott.repository;

import info.markott.domain.Reading;
import info.markott.domain.UnitOfMeasure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created by mark on 8/16/16.
 */
public interface ReadingRepository extends CrudRepository<Reading, Long> {

	@Query("SELECT r FROM Reading r WHERE r.uom = :unitOfMeasure AND r.createdOn BETWEEN :startDate AND :endDate")
	public List<Reading> findByUom(
			@Param("unitOfMeasure") UnitOfMeasure unitOfMeasure,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
}
