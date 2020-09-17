package com.wood.esm.detailservice.profile.DTO;

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
public class EmployeeAddressDTO extends BaseInformationDTO
{
	
	private Integer employeeAddressId;
	
	private Integer employeeInfoId;
	
	private String employeeCode;
	
	private String stateCode;
	
	private String cityName;
	
	private String countryCode;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private Integer zipCode;

}
