package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;

import com.udacity.jdnd.course3.critter.user.SkillsEntity;

@Entity
@Table(name = "schedule")
@DynamicUpdate
public class ScheduleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToMany
	private List<EmployeeEntity> employeeEntities;

	@ManyToMany
	private List<PetEntity> petEntities;

	@OneToMany(mappedBy = "scheduleEntity")
	private List<SkillsEntity> skillsEntities;

	@Column(name = "schedule_date")
	private LocalDate date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<EmployeeEntity> getEmployeeEntities() {
		return employeeEntities;
	}

	public void setEmployeeEntities(List<EmployeeEntity> employeeEntities) {
		this.employeeEntities = employeeEntities;
	}

	public List<PetEntity> getPetEntities() {
		return petEntities;
	}

	public void setPetEntities(List<PetEntity> petEntities) {
		this.petEntities = petEntities;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<SkillsEntity> getSkillsEntities() {
		return skillsEntities;
	}

	public void setSkillsEntities(List<SkillsEntity> skillsEntities) {
		this.skillsEntities = skillsEntities;
	}

}
