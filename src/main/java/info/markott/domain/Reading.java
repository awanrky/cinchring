package info.markott.domain;

import sun.security.util.UntrustedCertificates;

import javax.persistence.*;

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
}
