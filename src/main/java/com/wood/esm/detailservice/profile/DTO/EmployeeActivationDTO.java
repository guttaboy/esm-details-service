package com.wood.esm.detailservice.profile.DTO;

import java.util.List;

import com.wood.esm.detailservice.common.DTO.BaseInformationDTO;

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
@ToString( callSuper = true )
public class EmployeeActivationDTO extends BaseInformationDTO
{
	
	private Integer employeeActivationId;
	
	private String employeeCode;
	
	private String userName;
	
	private String password;
	
	private List<EmployeeInformationDTO> employeeInformationDTOs; 

}
