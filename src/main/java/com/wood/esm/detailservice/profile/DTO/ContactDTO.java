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
public class ContactDTO extends BaseInformationDTO
{
	
	private Integer contactId;
	
	private Integer employeeInfoId;
	
	private String employeeCode;
	
	private String emailId;
	
	private Integer homePhoneNumber;
	
	private Integer workPhoneNumber;

}
