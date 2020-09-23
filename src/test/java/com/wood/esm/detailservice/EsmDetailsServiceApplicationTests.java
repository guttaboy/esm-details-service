package com.wood.esm.detailservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.wood.esm.detailservice.common.enums.RowActionCodeEnum;
import com.wood.esm.detailservice.profile.DTO.EmployeeActivationDTO;
import com.wood.esm.detailservice.profile.DTO.EmployeeInformationDTO;

import lombok.extern.slf4j.XSlf4j;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@XSlf4j
@TestPropertySource(locations = "classpath:application.properties")
public class EsmDetailsServiceApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private EmployeeActivationDTO employeeActivationDTO;
	private EmployeeInformationDTO employeeInformationDTO;
	private List<EmployeeInformationDTO> employeeInformationDTOsList;

	public EmployeeActivationDTO mockEmployeeActivationDTO() {

		employeeActivationDTO = new EmployeeActivationDTO();

		employeeActivationDTO.setPassword("sxyzmnb");
		employeeActivationDTO.setEmployeeActivationId(1001);
		employeeActivationDTO.setEmployeeCode("PT1");
		employeeActivationDTO.setRowAction(RowActionCodeEnum.INSERT);
		employeeActivationDTO.setTransactionKey("xaguytr16545r");
		employeeActivationDTO.setUserName("JUNIT");
		employeeActivationDTO.setEmployeeInformationDTOs(employeeInformationDTOsList);

		return employeeActivationDTO;

	}

	public List<EmployeeInformationDTO> mockEmployeeInformationDTOs() {

		// Creating List of employeeInformations
		employeeInformationDTOsList = new ArrayList<EmployeeInformationDTO>();

		// Creating 1st employeeInformation
		employeeInformationDTO = new EmployeeInformationDTO();

		employeeInformationDTO.setEmployeeFirstName("John");
		employeeInformationDTO.setEmployeeMiddleName("peter");
		employeeInformationDTO.setEmployeeLastName("Gutta");
		employeeInformationDTO.setEmployeeInfoId(2001);
		employeeInformationDTO.setEmployeeSex("Male");
		employeeInformationDTO.setEmployeeGovernmentIssueIdNuber("999999999");
		employeeInformationDTO.setEmployeeDOB(LocalDate.of(1994, 12, 20));
		employeeInformationDTO.setCreateTimeStamp(LocalDateTime.of(2020, 9, 15, 22, 30));
		employeeInformationDTO.setEffectiveDate(LocalDate.of(2020, 9, 15));
		employeeInformationDTO.setEmployeeActivationId(1001);

		return employeeInformationDTOsList;
	}

	@Test
	public void contextLoads() {
	}

}
