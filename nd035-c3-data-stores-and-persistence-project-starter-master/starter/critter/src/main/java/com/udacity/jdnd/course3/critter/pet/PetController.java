package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.modelMapper.PetModelMapper;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

	@Autowired
	PetModelMapper petModelMapper;

	@Autowired
	PetService petService;

	@PostMapping
	public PetDTO savePet(@RequestBody PetDTO petDTO) {

		return petModelMapper.fromEntitytoDTO(petService.savePet(petModelMapper.fromDTOtoEntity(petDTO)));

	}

	@GetMapping("/{petId}")
	public PetDTO getPet(@PathVariable long petId) {

		return petModelMapper.fromEntitytoDTO(petService.getPet(petId));
	}

	@GetMapping
	public List<PetDTO> getPets() {

		return petModelMapper.getPetsDTO(petService.getAllPets());
	}

	@GetMapping("/owner/{ownerId}")
	public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

		return petModelMapper.getPetsDTO(petService.getAllPetsByCustomerId(ownerId));
	}
}
