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

import com.wood.esm.detailservice.profile.DTO.EmployeeAddressDTO;
import com.wood.esm.detailservice.profile.service.EmployeeAddressService;
import com.wood.esm.detailservice.profile.web.response.EmployeeAddressGetResponse;
import com.wood.esm.detailservice.profile.web.response.EmployeeAddressUpdateResponse;

import lombok.extern.slf4j.XSlf4j;

/**
 * @author chinn
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/profile/v1")
@XSlf4j
public class EmployeeAddressController {

	@Autowired
	private EmployeeAddressService employeeAddressService;

	@GetMapping("/employeeAddresses")
	public EmployeeAddressGetResponse getEmployeeAddresses(@RequestParam(required = true) Integer employeeInfoId,
			@RequestParam(required = true) String employeeCode) throws Exception {

		log.entry();

		EmployeeAddressGetResponse employeeGetResponse = employeeAddressService.getEmployeeAddresses(employeeInfoId,
				employeeCode);
		log.exit();
		return employeeGetResponse;
	}
	
	@PostMapping("/employeeAddresses")
	public EmployeeAddressUpdateResponse updateAddresses(@RequestBody List<EmployeeAddressDTO> employeeAddressDTOs) 
			throws Exception{
		log.entry();
		
		EmployeeAddressUpdateResponse employeeAddressUpdateResponse = new EmployeeAddressUpdateResponse();
		employeeAddressUpdateResponse = employeeAddressService.updateEmployeeAddresses(employeeAddressDTOs);
		
		log.exit();
		return employeeAddressUpdateResponse;
		
	}

}
