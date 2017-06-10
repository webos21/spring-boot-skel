package com.gmail.webos21.spring.db.domain;

import java.io.Serializable;

/**
 * Test User
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class TestUser implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** ID. */
	private String id;

	/** Name. */
	private String name;

	public TestUser() {
	}

	/**
	 * set the ID
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get the ID
	 * 
	 * @return ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set the Name
	 * 
	 * @param name
	 *            Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get the Name
	 * 
	 * @return Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TestUser other = (TestUser) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}