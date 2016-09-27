package info.markott.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mark on 9/7/16.
 *
 * Defines a Device, which can be a physical device such as an arduino, or
 * the web server, a phone app?, etc.
 *
 * Any createdBy and updatedBy information will contain the device that created
 * (or updated the record)
 *
 * For user information, when it is implemented, there will be additional fields
 * for createdByUser and updatedByUser, which may be null (until/unless I add
 * device-level authentication)
 *
 */

@Entity
public class Device {

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
	@JoinColumn(name="CREATED_BY")
	private Device createdBy;

	@JsonView(Views.Full.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="UPDATED_BY")
	private Device updatedBy;

	@PrePersist
	protected void onCreate() {
		createdOn = new Date();
	}

//	@PostPersist
//	protected void onCreated() {
//	}

	@PreUpdate
	protected void onUpdate() {
		updatedOn = new Date();
	}

	public Device() {

	}

	public Device(String name) {
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

	public Device getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Device updatedBy) {
		this.updatedBy = updatedBy;
	}
}
