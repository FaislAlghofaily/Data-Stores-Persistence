package com.udacity.jdnd.course3.critter.schedule;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.modelMapper.ScheduleModelMapper;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import com.google.common.collect.Sets;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;
	@Autowired
	ScheduleModelMapper scheduleModelMapper;
	@Autowired
	PetService petService;
	@Autowired
	EmployeeService employeeService;

	@PostMapping
	public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

		ScheduleEntity scheduleEntity = scheduleService.createSchedule(scheduleDTO.getEmployeeIds(),
				scheduleDTO.getActivities(), scheduleDTO.getPetIds(), scheduleDTO.getDate());
		return scheduleModelMapper.fromEntitytoDTO(scheduleEntity);

	}

	@PostMapping(value = "/test")
	public ScheduleDTO createSchedue() {
		LocalDate date = LocalDate.of(2019, 12, 25);
		List<Long> petList = Lists.newArrayList(petService.getPet(2).getId());
		List<Long> employeeList = Lists.newArrayList(employeeService.getEmployee(2).getId());
		Set<EmployeeSkill> skillSet = Sets.newHashSet(EmployeeSkill.PETTING);

		ScheduleEntity scheduleEntity = scheduleService.createSchedule(employeeList, skillSet, petList, date);
		return scheduleModelMapper.fromEntitytoDTO(scheduleEntity);
	}

	@GetMapping
	public List<ScheduleDTO> getAllSchedules() {
		return scheduleModelMapper.getScheduleDTOs(scheduleService.getScheduleList());
	}

	@GetMapping("/pet/{petId}")
	public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

		return scheduleModelMapper.getScheduleDTOs(scheduleService.getScheduleByPet(petId));
	}

	@GetMapping("/employee/{employeeId}")
	public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
		return scheduleModelMapper.getScheduleDTOs(scheduleService.getScheduleByEmployee(employeeId));
	}

	@GetMapping("/customer/{customerId}")
	public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
		return scheduleModelMapper.getScheduleDTOs(scheduleService.getScheduleByCustomer(customerId));
	}
}
