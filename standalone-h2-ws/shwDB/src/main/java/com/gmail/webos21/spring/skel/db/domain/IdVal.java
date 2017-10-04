package com.gmail.webos21.spring.skel.db.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "id_val")
public class IdVal {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = true)
	private Long id;

	@Column(name = "val", nullable = false)
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

}
