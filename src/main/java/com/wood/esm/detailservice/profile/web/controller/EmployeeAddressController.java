package com.wood.esm.detailservice.profile.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wood.esm.detailservice.profile.service.EmployeeAddressService;
import com.wood.esm.detailservice.profile.web.response.EmployeeAddressGetResponse;

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

}
