package com.wood.esm.detailservice.profile.DTO;

import java.time.LocalDate;
import java.util.List;

import com.wood.esm.detailservice.common.DTO.DateInformationDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chinn
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EmployeeInformationDTO extends DateInformationDTO {

	private Integer employeeInfoId;

	private Integer employeeActivationId;

	private String employeeCode;

	private String employeeFirstName;

	private String employeeMiddleName;

	private String employeeLastName;

	private String employeeSSN;

	private LocalDate employeeDOB;

	private String employeeSex;

	private List<ContactDTO> contacts;

	private List<EmployeeAddressDTO> employeeAddress;

}
