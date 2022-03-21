package com.udacity.jdnd.course3.critter.modelMapper;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import com.udacity.jdnd.course3.critter.user.DaysAvailableEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.SkillsEntity;

public class EmployeeModelMapper {

	public EmployeeEntity fromDTOtoEntity(EmployeeDTO employeeDTO) {

		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employeeDTO, employeeEntity);
		if (employeeDTO.getSkills() != null) {
			List<SkillsEntity> skillsEntity = new ArrayList<>();
			for (EmployeeSkill eompSkill : employeeDTO.getSkills()) {
				SkillsEntity skills = new SkillsEntity();
				skills.setEmployeeEntity(employeeEntity);
				skills.setSkill(eompSkill.toString());
				skillsEntity.add(skills);
			}
			employeeEntity.setSkillsEntities(skillsEntity);
		}
		if (employeeDTO.getDaysAvailable() != null) {
			List<DaysAvailableEntity> daysAvailableEntities = new ArrayList<>();
			for (DayOfWeek day : employeeDTO.getDaysAvailable()) {
				DaysAvailableEntity days = new DaysAvailableEntity();
				days.setEmployeeEntity(employeeEntity);
				days.setDay(day.toString());
				daysAvailableEntities.add(days);
			}

			employeeEntity.setDaysAvailableEntities(daysAvailableEntities);
		}
		return employeeEntity;

	}

	public EmployeeDTO fromEntitytoDTO(EmployeeEntity employeeEntity) {

		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employeeEntity, employeeDTO);
		if (employeeEntity.getSkillsEntities() != null) {

			Set<EmployeeSkill> skills = new HashSet<>();
			for (SkillsEntity i : employeeEntity.getSkillsEntities()) {
				skills.add(Enum.valueOf(EmployeeSkill.class, i.getSkill()));
			}
			employeeDTO.setSkills(skills);
		}
		if (employeeEntity.getDaysAvailableEntities() != null) {
			Set<DayOfWeek> days = new HashSet<>();
			for (DaysAvailableEntity i : employeeEntity.getDaysAvailableEntities()) {

				days.add(Enum.valueOf(DayOfWeek.class, i.getDay()));

			}

			employeeDTO.setDaysAvailable(days);

		}

		return employeeDTO;

	}

	public List<EmployeeDTO> getEmployeeDTO(List<EmployeeEntity> employeeEntity) {
		List<EmployeeDTO> employeeDTO = new ArrayList<>();

		for (EmployeeEntity employeeEntity2 : employeeEntity) {
			EmployeeDTO employee = new EmployeeDTO();
			Set<EmployeeSkill> skills = new HashSet<>();
			Set<DayOfWeek> days = new HashSet<>();
			BeanUtils.copyProperties(employeeEntity2, employee);

			for (SkillsEntity i : employeeEntity2.getSkillsEntities()) {

				skills.add(Enum.valueOf(EmployeeSkill.class, i.getSkill()));

			}

			for (DaysAvailableEntity i : employeeEntity2.getDaysAvailableEntities()) {

				days.add(Enum.valueOf(DayOfWeek.class, i.getDay()));

			}

			employee.setDaysAvailable(days);
			employee.setSkills(skills);
			employeeDTO.add(employee);
		}

		return employeeDTO;

	}

}
