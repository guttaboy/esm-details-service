package com.wood.esm.detailservice.profile.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.wood.esm.detailservice.profile.DTO.EmployeeInformationDTO;
import com.wood.esm.detailservice.profile.domain.EmployeeInformation;

/**
 * @author chinn
 *
 */
@Mapper( componentModel = "spring" )
public interface EmployeeInformationMapper 
{
	
	EmployeeInformationMapper INSTANCE = Mappers.getMapper( EmployeeInformationMapper.class );
	
	/**
	 * @param employeeInformation
	 * @return
	 */
	@Mappings({ @Mapping( source = "employeeActivation.employeeActivationId", target = "employeeActivationId" ),
	@Mapping( target = "contactDTOs", ignore = true ),
	@Mapping( target = "employeeAddressDTOs", ignore = true ) })
	EmployeeInformationDTO toDTO( EmployeeInformation employeeInformation );
	
	/**
	 * @param employeeInformationDTO
	 * @return
	 */
	@InheritInverseConfiguration
	EmployeeInformation fromDTO( EmployeeInformationDTO employeeInformationDTO );
	
	/**
	 * @param employeeInformations
	 * @return
	 */
	List<EmployeeInformationDTO> toDTOs( List<EmployeeInformation> employeeInformations );
	
	/**
	 * @param employeeInformationDTOs
	 * @return
	 */
	@InheritInverseConfiguration
	List<EmployeeInformation> fromDTOs( List<EmployeeInformationDTO> employeeInformationDTOs );

}
