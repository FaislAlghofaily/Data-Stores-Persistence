package com.udacity.jdnd.course3.critter.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udacity.jdnd.course3.critter.pet.PetService;
@Transactional
@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	PetService petService;

	public CustomerEntity getCustomer(long cutomerId) {

		Optional<CustomerEntity> optional = customerRepository.findById(cutomerId);
		CustomerEntity customerEntity = optional.orElseThrow(RuntimeException::new);
		return customerEntity;
	}

	public CustomerEntity saveCustomer(CustomerEntity customerEntity) {

		return customerRepository.save(customerEntity);

	}

	public List<CustomerEntity> getAllCustomers() {
		List<CustomerEntity> customers = (List<CustomerEntity>) customerRepository.findAll();
		for (CustomerEntity customerEntity : customers) {
			if (customerEntity.getPetEntities() == null) {
				try {

					customerEntity.setPetEntities(petService.getAllPetsByOwnerId(customerEntity));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		return (List<CustomerEntity>) customerRepository.findAll();
	}

	public CustomerEntity getCustomerByPet(Long petId) {
		CustomerEntity customerEntity = customerRepository.findByPetEntities(petService.getPet(petId));
		if (customerEntity.getPetEntities() == null) {
			try {
				customerEntity.setPetEntities(petService.getAllPetsByCustomerId(petId));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return customerEntity;

	}

}
