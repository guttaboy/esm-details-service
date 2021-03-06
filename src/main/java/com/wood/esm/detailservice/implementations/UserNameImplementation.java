package com.wood.esm.detailservice.implementations;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Component;

import com.wood.esm.detailservice.profile.DTO.EmployeeActivationDTO;

@Component
public class UserNameImplementation 
{
	
	public String generateUserName( EmployeeActivationDTO employeeActivationDTO )
	{
		
		
		String firstName = employeeActivationDTO.getEmployeeInformationDTOs().get(0).getEmployeeFirstName();
		//get First character from Frist Name
		char firstFN = Character.toUpperCase(firstName.charAt(0));
		String stringFirstFN =String.valueOf(firstFN);  
		
		String lastName = employeeActivationDTO.getEmployeeInformationDTOs().get(0).getEmployeeLastName();
		//get first character from Last Name
		char firstLN = Character.toUpperCase(lastName.charAt(0));
		String stringFirstLN =String.valueOf(firstLN);  
		
		//generate Random number
		int randonNumber = RandomUtils.nextInt(9999);
	    String randonNumberToString = "" + randonNumber;
		
		String userName = (stringFirstFN +  stringFirstLN + randonNumberToString );
		return userName;
		
	}

}
