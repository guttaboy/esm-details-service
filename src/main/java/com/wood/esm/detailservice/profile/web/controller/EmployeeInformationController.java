package com.wood.esm.detailservice.profile.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wood.esm.detailservice.profile.DTO.EmployeeInformationDTO;
import com.wood.esm.detailservice.profile.service.EmployeeInformationService;
import com.wood.esm.detailservice.profile.web.response.EmployeeInformationGetResponse;
import com.wood.esm.detailservice.profile.web.response.EmployeeInformationUpdateResponse;

import lombok.extern.slf4j.XSlf4j;

/**
 * @author chinn
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/profile/v1")
@XSlf4j
public class EmployeeInformationController {

	@Autowired
	private EmployeeInformationService employeeInformationService;

	/**
	 * @param employeeActivationId
	 * @param employeeCode
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/employeeDetails")
	public EmployeeInformationGetResponse getEmployeeInformationDetails(
			@RequestParam(required = true) Integer employeeActivationId,
			@RequestParam(required = true) String employeeCode) throws Exception {
		log.entry();

		EmployeeInformationGetResponse employeeInformationGetResponse = employeeInformationService
				.getEmployeeInformationDetails(employeeActivationId, employeeCode);
		
		log.exit();

		return employeeInformationGetResponse;
	}
	
	@PostMapping("/employeeDetails")
	public EmployeeInformationUpdateResponse updateEmployeeInformations(@RequestBody List<EmployeeInformationDTO> employeeInformationDTOs) throws Exception {
		
		log.entry();
		EmployeeInformationUpdateResponse employeeInformationUpdateResponse = new EmployeeInformationUpdateResponse();
		employeeInformationUpdateResponse = employeeInformationService.updateEmployeeInformations(employeeInformationDTOs);
		log.exit();
		return employeeInformationUpdateResponse;
	}

}
