package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.user.CustomerEntity;

import com.udacity.jdnd.course3.critter.user.CustomerService;
@Transactional
@Service
public class PetService {

	@Autowired
	PetRepository petRepository;

	@Autowired
	CustomerService customerService;

	public PetEntity savePet(PetEntity petEntity) {

		return petRepository.save(petEntity);
	}

	public PetEntity getPet(long petId) {
		Optional<PetEntity> optional = petRepository.findById(petId);
		PetEntity petEntity = optional.orElseThrow(RuntimeException::new);
		return petEntity;
	}

	public List<PetEntity> getAllPets() {

		return (List<PetEntity>) petRepository.findAll();
	}

	public List<PetEntity> getAllPetsByOwnerId(CustomerEntity customerEntity) {
		return petRepository.findByCustomerEntity(customerEntity);
	}

	public List<PetEntity> getAllPetsByCustomerId(Long customerId) {

		return petRepository.findByCustomerEntity(customerService.getCustomer(customerId));
	}

}
