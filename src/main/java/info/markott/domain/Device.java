package info.markott.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mark on 9/7/16.
 */

@Entity
public class Device {


	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;


	@Column(unique=true, nullable = false)
	private String name;

	private Date createdOn;
	private Date updatedOn;

	@PrePersist
	protected void onCreate() {
		createdOn = new Date();
	}

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
}
