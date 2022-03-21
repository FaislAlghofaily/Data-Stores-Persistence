package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.user.CustomerEntity;

@Repository
public interface PetRepository extends CrudRepository<PetEntity, Long> {
	List<PetEntity> findByCustomerEntity(CustomerEntity customerEntity);

}
