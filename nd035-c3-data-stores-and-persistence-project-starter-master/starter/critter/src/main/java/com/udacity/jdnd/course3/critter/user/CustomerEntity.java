package com.udacity.jdnd.course3.critter.user;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.udacity.jdnd.course3.critter.pet.PetEntity;

@Entity
@Table(name = "customers")
@DynamicUpdate
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "notes")
	private String notes;

	@OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PetEntity> petEntities;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<PetEntity> getPetEntities() {
		return petEntities;
	}

	public void setPetEntities(List<PetEntity> petEntities) {
		this.petEntities = petEntities;
	}

}
