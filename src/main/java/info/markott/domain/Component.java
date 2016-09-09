package info.markott.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mark on 9/9/16.
 */

@Entity
public class Component {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;

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
