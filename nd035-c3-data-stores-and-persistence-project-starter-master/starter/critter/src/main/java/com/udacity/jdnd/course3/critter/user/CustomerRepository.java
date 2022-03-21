package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.pet.PetEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

	CustomerEntity findByPetEntities(PetEntity petEntity);

}
