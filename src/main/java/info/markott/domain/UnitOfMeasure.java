package info.markott.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mark on 9/5/16.
 *
 * UnitOfMeasure should be self-explanatory.  Is the reading that uses this uom measured
 * in feet?  degrees celsius?  revolutions per minute?
 *
 */


@Entity
public class UnitOfMeasure {

	@JsonView(Views.Full.class)
	@Id
	@GeneratedValue
	private int id;

	@JsonView(Views.Compact.class)
	@Column(unique=true, nullable = false)
	private String name;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonView(Views.Compact.class)
	private String description;

	@JsonView(Views.Full.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	@JsonView(Views.Full.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@JsonView(Views.Full.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="CREATED_BY", nullable = false)
	private Device createdBy;

	public UnitOfMeasure() {

	}

	public UnitOfMeasure(String name) {
		this();
		setName(name);
	}

	public UnitOfMeasure(String name, String description) {
		this(name);
		setDescription(description);
	}

	@PrePersist
	protected void onCreate() {
		createdOn = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedOn = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Device getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Device device) {
		this.createdBy = device;
	}

	/**
	 * setCreatedByIfMissing
	 *
	 * will set the createdBy value if it is null.  This should only happen if the record hasn't
	 * yet been persisted to the database
	 *
	 * @param device
	 */
	public void setCreatedByIfMissing(Device device) {
		if (getCreatedBy() == null) {
			setCreatedBy(device);
		}
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}
}

