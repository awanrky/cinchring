package info.markott.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mark on 9/9/16.
 *
 * A component is part of a device.  For instance an arduino (device) could have many
 * different sensors (components) on it.  One for reading temperature, one for reading
 * light level, etc.
 *
 */

@Entity
public class Component {

	@JsonView(Views.Full.class)
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;

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

	@PrePersist
	protected void onCreate() {
		createdOn = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedOn = new Date();
	}

	public Component() {

	}

	public Component(String name) {
		setName(name);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
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

	public void setCreatedBy(Device createdBy) {
		this.createdBy = createdBy;
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

}
