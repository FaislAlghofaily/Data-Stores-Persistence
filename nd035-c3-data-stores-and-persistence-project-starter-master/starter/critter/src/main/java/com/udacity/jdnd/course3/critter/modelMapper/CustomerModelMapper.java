package com.udacity.jdnd.course3.critter.modelMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.CustomerEntity;

@Service
public class CustomerModelMapper {

	@Autowired
	PetService petService;

	public CustomerEntity fromDTOtoEntity(CustomerDTO customerDTO) {

		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customerDTO, customerEntity);
		if (customerDTO.getPetIds() != null) {
			List<PetEntity> petEntities = new ArrayList<>();
			for (Long i : customerDTO.getPetIds()) {
				petEntities.add(petService.getPet(i));

			}
			customerEntity.setPetEntities(petEntities);

		}

		return customerEntity;

	}

	public CustomerDTO fromEntitytoDTO(CustomerEntity customerEntity) {

		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customerEntity, customerDTO);
		if (customerEntity.getPetEntities() != null) {
			List<Long> petIDs = new ArrayList<>();
			for (PetEntity petEntity : customerEntity.getPetEntities()) {
				petIDs.add(petEntity.getId());
			}
			customerDTO.setPetIds(petIDs);
		}

		return customerDTO;

	}

	public List<CustomerDTO> getCustomersDTO(List<CustomerEntity> customerEntity) {
		List<CustomerDTO> customerDTO = new ArrayList<>();

		for (CustomerEntity customerEntity2 : customerEntity) {

			customerDTO.add(fromEntitytoDTO(customerEntity2));
		}

		return customerDTO;

	}

}
