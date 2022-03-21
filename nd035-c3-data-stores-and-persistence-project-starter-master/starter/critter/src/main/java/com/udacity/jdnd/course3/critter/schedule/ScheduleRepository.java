package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.SkillsEntity;

@Repository
public interface ScheduleRepository extends CrudRepository<ScheduleEntity, Long> {

	List<ScheduleEntity> findAllByEmployeeEntities(EmployeeEntity employeeEntity);

	List<ScheduleEntity> findAllByPetEntities(PetEntity petEntity);

	List<ScheduleEntity> findAllBySkillsEntities(SkillsEntity skillsEntity);

}
