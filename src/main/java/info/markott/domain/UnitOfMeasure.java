package info.markott.domain;

import javax.persistence.*;

/**
 * Created by mark on 9/5/16.
 */


@Entity
public class UnitOfMeasure {

	@Id
	@GeneratedValue
	private int id;

	@Column(unique=true)
	private String name;

	private String description;

	public UnitOfMeasure() {

	}

	public UnitOfMeasure(String name) {
		setName(name);
	}

	public UnitOfMeasure(String name, String description) {
		setName(name);
		setDescription(description);
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

}

