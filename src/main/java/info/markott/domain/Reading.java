package info.markott.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mark on 8/16/16.
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@PrePersist
	protected void onCreate() {
		createdOn = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedOn = new Date();
	}

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

	public Date getUpdatedOn() {
		return updatedOn;
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
