package info.markott.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mark on 8/16/16.
 *
 * A Reading is a single piece of information (value) from a single source (device and component)
 *
 * It could be the temperature, light level, etc.
 *
 * It contains several pieces of metadata about the reading, its unit of measure, the device
 * and component it came from, the time it was created, and maybe the location of the reading (
 * the location, if present, would override the location of the device)
 *
 * A reading should never be updated (note the lack of updatedOn field)
 *
 * A reading is created by its device (note the lack of a createdBy field)
 *
 */

@Entity
public class Reading {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;

	@Column(name="VALUE")
	private String value;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="UOM_ID")
	private UnitOfMeasure uom;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="DEVICE_ID", nullable = false)
	private Device device;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="COMPONENT_ID", nullable = false)
	private Component component;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@PrePersist
	protected void onCreate() {
		createdOn = new Date();
	}

//	@PreUpdate
//	protected void onUpdate() {
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UnitOfMeasure getUom() {
		return uom;
	}

	public void setUom(UnitOfMeasure uom) {
		this.uom = uom;
	}

	@Override
	public String toString() {
		return getId() + ", " + getValue();
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}
}
