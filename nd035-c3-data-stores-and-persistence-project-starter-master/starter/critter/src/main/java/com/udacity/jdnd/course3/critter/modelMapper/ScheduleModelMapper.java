package com.udacity.jdnd.course3.critter.modelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.SkillsEntity;
import com.udacity.jdnd.course3.critter.user.SkillsRepository;

@Transactional
@Service
public class ScheduleModelMapper {
	
	@Autowired
	SkillsRepository skillsRepository;

	public ScheduleEntity fromDTOtoEntity(ScheduleDTO scheduleDTO) {

		ScheduleEntity scheduleEntity = new ScheduleEntity();
		BeanUtils.copyProperties(scheduleDTO, scheduleEntity);
		return scheduleEntity;
	
	}
	
	public ScheduleDTO fromEntitytoDTO(ScheduleEntity scheduleEntity) {
		
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		BeanUtils.copyProperties(scheduleEntity, scheduleDTO);
		
		if(scheduleEntity.getEmployeeEntities()!=null) {
			List<Long> employeeIds=new ArrayList<>();
			for (EmployeeEntity employeeEntity:scheduleEntity.getEmployeeEntities()) {
				employeeIds.add(employeeEntity.getId());
			}
			scheduleDTO.setEmployeeIds(employeeIds);
		}
		
		if(scheduleEntity.getPetEntities()!=null) {
			List<Long> petIds=new ArrayList<>();
			for (PetEntity petEntity:scheduleEntity.getPetEntities()) {
				petIds.add(petEntity.getId());
			}
			scheduleDTO.setPetIds(petIds);
		}
		if(scheduleEntity.getSkillsEntities()==null) {
			try {
				scheduleEntity.setSkillsEntities(skillsRepository.findByScheduleEntity(scheduleEntity));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		if(scheduleEntity.getSkillsEntities()!=null) {
			Set<EmployeeSkill> skill=new HashSet<>();
			for (SkillsEntity skillsEntity:scheduleEntity.getSkillsEntities()) {
				skill.add(Enum.valueOf(EmployeeSkill.class, skillsEntity.getSkill()));
			}
			scheduleDTO.setActivities(skill);
		}
		
		return scheduleDTO;
	}

	 public List<ScheduleDTO> getScheduleDTOs(List<ScheduleEntity> scheduleEntity){
		 List<ScheduleDTO> scheduleDTOs=new ArrayList<>();
		 for(ScheduleEntity scheduleEntity2:scheduleEntity) {
			 scheduleDTOs.add(fromEntitytoDTO(scheduleEntity2)); 
		 }
		 
		 
		 return scheduleDTOs;
	 }
}
