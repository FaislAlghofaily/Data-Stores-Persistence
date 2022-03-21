package com.udacity.jdnd.course3.critter.modelMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.user.CustomerService;

@Service
public class PetModelMapper {

	@Autowired
	CustomerService customerService;

	public PetEntity fromDTOtoEntity(PetDTO petDTO) {

		PetEntity petEntity = new PetEntity();
		BeanUtils.copyProperties(petDTO, petEntity);

		petEntity.setCustomerEntity(customerService.getCustomer(petDTO.getOwnerId()));
		petEntity.setType(petDTO.getType().toString());

		return petEntity;

	}

	public PetDTO fromEntitytoDTO(PetEntity petEntity) {

		PetDTO petDTO = new PetDTO();
		BeanUtils.copyProperties(petEntity, petDTO);
		petDTO.setOwnerId(petEntity.getCustomerEntity().getId());

		petDTO.setType(Enum.valueOf(PetType.class, petEntity.getType()));
		return petDTO;

	}

	public List<PetDTO> getPetsDTO(List<PetEntity> petEntities) {
		List<PetDTO> petDTO = new ArrayList<>();

		for (PetEntity petEntity2 : petEntities) {
			PetDTO pet = new PetDTO();
			BeanUtils.copyProperties(petEntity2, pet);
			pet.setOwnerId(petEntity2.getCustomerEntity().getId());

			pet.setType(Enum.valueOf(PetType.class, petEntity2.getType()));
			petDTO.add(pet);
		}

		return petDTO;

	}

}
