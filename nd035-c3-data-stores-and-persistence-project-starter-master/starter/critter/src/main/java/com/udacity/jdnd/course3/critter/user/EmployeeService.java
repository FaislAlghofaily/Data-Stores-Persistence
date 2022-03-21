package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Transactional
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DaysAvailableRepository daysAvailableRepository;
	@Autowired
	SkillsRepository skillsRepository;

	public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
		EmployeeEntity employee = new EmployeeEntity();
		employee = employeeRepository.save(employeeEntity);
		if (employeeEntity.getSkillsEntities() != null) {
			for (SkillsEntity skillsEntity : employeeEntity.getSkillsEntities()) {
				skillsEntity.setEmployeeEntity(employeeEntity);
				skillsRepository.save(skillsEntity);
			}
		}

		if (employeeEntity.getDaysAvailableEntities() != null) {
			for (DaysAvailableEntity daysAvailableEntity : employeeEntity.getDaysAvailableEntities()) {
				daysAvailableEntity.setEmployeeEntity(employeeEntity);
				daysAvailableRepository.save(daysAvailableEntity);
			}
		}

		return employee;
	}

	public EmployeeEntity getEmployee(long employeeId) {
		Optional<EmployeeEntity> optional = employeeRepository.findById(employeeId);
		EmployeeEntity employeeEntity = optional.orElseThrow(RuntimeException::new);

		try {
			employeeEntity.setDaysAvailableEntities(daysAvailableRepository.findByEmployeeEntity(employeeEntity));
		} catch (Exception e) {

		}

		return employeeEntity;
	}

	public void setAvilability(Set<DayOfWeek> daysAvailable, long employeeId) {
		EmployeeEntity employeeEntity = getEmployee(employeeId);

		for (DayOfWeek day : daysAvailable) {
			DaysAvailableEntity daysAvailableEntity = new DaysAvailableEntity();
			daysAvailableEntity.setDay(day.toString());
			daysAvailableEntity.setEmployeeEntity(employeeEntity);
			daysAvailableRepository.save(daysAvailableEntity);
		}
		employeeRepository.save(employeeEntity);

	}

	public List<EmployeeEntity> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {
		LocalDate localDate = employeeRequestDTO.getDate();
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		List<String> skillsList = new ArrayList<String>();
		Set<EmployeeSkill> skillSet = employeeRequestDTO.getSkills();
		for (EmployeeSkill employeeSkill : skillSet) {
			skillsList.add(employeeSkill.toString());

		}

		return employeeRepository.listOfAvailable(skillsList, dayOfWeek.toString(), skillsList.size());
	}

}
