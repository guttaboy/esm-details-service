package com.wood.esm.detailservice.profile.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wood.esm.detailservice.profile.DTO.EmployeeActivationDTO;
import com.wood.esm.detailservice.profile.domain.EmployeeActivation;

/**
 * @author chinn
 *
 */
@Mapper( componentModel = "spring" )
public interface EmployeeActivationMapper 
{
	
	EmployeeActivationMapper INSTANCE = Mappers.getMapper( EmployeeActivationMapper.class );
	
		/**
		 * @param employeeActivation
		 * @return
		 */
		@Mapping( target = "employeeInformationDTOs", ignore = true )
		EmployeeActivationDTO toDTO( EmployeeActivation employeeActivation );
		
		/**
		 * @param employeeActivationDTO
		 * @return
		 */
		@InheritInverseConfiguration
		EmployeeActivation fromDTO( EmployeeActivationDTO employeeActivationDTO );
		
		/**
		 * @param employeeActivations
		 * @return
		 */
		List<EmployeeActivationDTO> toDTOs( List<EmployeeActivation> employeeActivations );
		
		/**
		 * @param employeeActivationDTOs
		 * @return
		 */
		@InheritInverseConfiguration
		List<EmployeeActivation> fromDTOs( List<EmployeeActivationDTO> employeeActivationDTOs );

}
