package com.wood.esm.detailservice.profile.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wood.esm.detailservice.profile.DTO.EmployeeActivationDTO;
import com.wood.esm.detailservice.profile.service.EmployeeActivationService;
import com.wood.esm.detailservice.profile.web.response.EmployeeActivationGetResponse;
import com.wood.esm.detailservice.profile.web.response.EmployeeActivationUpdateResponse;

import lombok.extern.slf4j.XSlf4j;

/**
 * @author chinn
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/profile/v1")
@XSlf4j
public class EmployeeActivationController {

	@Autowired
	private EmployeeActivationService employeeActivationService;

	/**
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/profiles")
	public EmployeeActivationGetResponse getEmployeeActivationDetails(@RequestParam(required = true) String userName,
			@RequestParam(required = true) String password) throws Exception {
		log.entry();

		EmployeeActivationGetResponse employeeActivationGetResponse = employeeActivationService
				.getEmployeeActivationDetails(userName, password);

		return employeeActivationGetResponse;
	}

	/**
	 * @param employeeActivationDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/updateProfiles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeActivationUpdateResponse updateEmployeeActivationDetails(
			@RequestBody EmployeeActivationDTO employeeActivationDTO) throws Exception {
		log.entry();

		EmployeeActivationUpdateResponse employeeActivationUpdateResponse = new EmployeeActivationUpdateResponse();

		employeeActivationUpdateResponse = employeeActivationService.updateEmployeeActivation(employeeActivationDTO);
		log.exit();
		return employeeActivationUpdateResponse;

	}
}
