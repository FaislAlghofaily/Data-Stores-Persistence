package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaysAvailableRepository extends CrudRepository<DaysAvailableEntity, Long> {
	List<DaysAvailableEntity> findByEmployeeEntity(EmployeeEntity employeeEntity);

}
