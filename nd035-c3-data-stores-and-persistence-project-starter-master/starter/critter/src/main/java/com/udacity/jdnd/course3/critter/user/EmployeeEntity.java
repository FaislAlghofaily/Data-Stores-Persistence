package com.udacity.jdnd.course3.critter.user;

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

import com.udacity.jdnd.course3.critter.schedule.ScheduleEntity;

@Entity
@Table(name = "employees")
@DynamicUpdate
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "employeeEntity")
	private List<SkillsEntity> skillsEntities;

	@OneToMany(mappedBy = "employeeEntity")
	private List<DaysAvailableEntity> daysAvailableEntities;

	@ManyToMany
	List<ScheduleEntity> scheduleEntities;

	public List<SkillsEntity> getSkillsEntities() {
		return skillsEntities;
	}

	public void setSkillsEntities(List<SkillsEntity> skillsEntities) {
		this.skillsEntities = skillsEntities;
	}

	public List<DaysAvailableEntity> getDaysAvailableEntities() {
		return daysAvailableEntities;
	}

	public void setDaysAvailableEntities(List<DaysAvailableEntity> daysAvailableEntities) {
		this.daysAvailableEntities = daysAvailableEntities;
	}

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

	public List<ScheduleEntity> getScheduleEntities() {
		return scheduleEntities;
	}

	public void setScheduleEntities(List<ScheduleEntity> scheduleEntities) {
		this.scheduleEntities = scheduleEntities;
	}

}
