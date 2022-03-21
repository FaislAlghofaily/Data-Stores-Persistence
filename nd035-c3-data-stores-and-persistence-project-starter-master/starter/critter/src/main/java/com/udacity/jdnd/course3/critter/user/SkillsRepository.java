package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.schedule.ScheduleEntity;

@Repository
public interface SkillsRepository extends CrudRepository<SkillsEntity, Long> {
	List<SkillsEntity> findAllBySkill(String skill);

	List<SkillsEntity> findByScheduleEntity(ScheduleEntity scheduleEntity);

}
