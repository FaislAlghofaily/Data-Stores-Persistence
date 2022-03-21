package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.SkillsEntity;
import com.udacity.jdnd.course3.critter.user.SkillsRepository;
@Transactional
@Service
public class ScheduleService {
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	SkillsRepository skillsRepository;
	@Autowired
	PetRepository petRepository;

	@Autowired
	EmployeeService employeeService;
	@Autowired
	PetService petService;
	@Autowired
	CustomerService customerService;

	public ScheduleEntity createSchedule(List<Long> employeeIds, Set<EmployeeSkill> skills, List<Long> pets,
			LocalDate date) {
		ScheduleEntity scheduleEntity = new ScheduleEntity();
		scheduleEntity.setDate(date);
		ScheduleEntity schedule = scheduleRepository.save(scheduleEntity);
		List<EmployeeEntity> employeeEntities = new ArrayList<>();
		for (Long employee : employeeIds) {
			employeeEntities.add(employeeService.getEmployee(employee));

		}

		List<PetEntity> petEntities = new ArrayList<>();
		for (Long pet : pets) {

			petEntities.add(petService.getPet(pet));

		}

		List<SkillsEntity> skillsEntities = new ArrayList<>();
		for (EmployeeSkill skill : skills) {
			SkillsEntity skillsEntity = new SkillsEntity();
			skillsEntity.setScheduleEntity(schedule);
			skillsEntity.setSkill(skill.toString());
			skillsEntities.add(skillsRepository.save(skillsEntity));

		}

		schedule.setEmployeeEntities(employeeEntities);
		schedule.setPetEntities(petEntities);
		schedule.setSkillsEntities(skillsEntities);
		scheduleEntity = scheduleRepository.save(schedule);
		scheduleEntity.setSkillsEntities(skillsRepository.findByScheduleEntity(scheduleEntity));
		return scheduleEntity;

	}

	public List<ScheduleEntity> getScheduleList() {
		return (List<ScheduleEntity>) scheduleRepository.findAll();

	}

	public List<ScheduleEntity> getScheduleByEmployee(Long employeeId) {
		EmployeeEntity employeeEntity = employeeService.getEmployee(employeeId);
		return scheduleRepository.findAllByEmployeeEntities(employeeEntity);

	}

	public List<ScheduleEntity> getScheduleByPet(Long petId) {
		PetEntity petEntity = petService.getPet(petId);
		return scheduleRepository.findAllByPetEntities(petEntity);

	}

	public List<ScheduleEntity> getScheduleByCustomer(Long customerId) {
		List<ScheduleEntity> scheduleEntities = new ArrayList<>();
		List<PetEntity> PetEntities = petService.getAllPetsByCustomerId(customerId);

		for (PetEntity petEntity : PetEntities) {

			scheduleEntities.addAll(scheduleRepository.findAllByPetEntities(petEntity));
		}
		return scheduleEntities;

	}

}
