package info.markott.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mark on 9/5/16.
 *
 */


@Entity
public class UnitOfMeasure {

	@Id
	@GeneratedValue
	private int id;

	@Column(unique=true, nullable = false)
	private String name;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

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

