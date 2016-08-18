package info.markott.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

	@Override
	public String toString() {
		return getId() + ", " + getValue();
	}
}
