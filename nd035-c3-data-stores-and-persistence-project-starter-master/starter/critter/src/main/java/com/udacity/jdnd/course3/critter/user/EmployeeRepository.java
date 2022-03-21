package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

	@Transactional
	@Query(value = "select distinct u FROM EmployeeEntity u join u.daysAvailableEntities i join u.skillsEntities j where i.day=:day and j.skill in :skills"
			+ " GROUP BY u HAVING COUNT(j) = :skillListSize")
	List<EmployeeEntity> listOfAvailable(@Param("skills") List<String> skills, @Param("day") String day,
			@Param("skillListSize") long skillListSize);

}
