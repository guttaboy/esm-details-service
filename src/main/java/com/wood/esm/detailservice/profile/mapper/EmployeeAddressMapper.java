package com.wood.esm.detailservice.profile.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wood.esm.detailservice.profile.DTO.EmployeeAddressDTO;
import com.wood.esm.detailservice.profile.domain.EmployeeAddress;

/**
 * @author chinn
 *
 */
@Mapper( componentModel = "spring" )
public interface EmployeeAddressMapper 
{
	
	EmployeeAddressMapper INSTANCE = Mappers.getMapper( EmployeeAddressMapper.class );
	
	/**
	 * @param employeeAddress
	 * @return
	 */
	@Mapping( source = "employeeInformation.employeeInfoId", target = "employeeInfoId" )
	EmployeeAddressDTO toDTO( EmployeeAddress employeeAddress );
	
	/**
	 * @param employeeAddressDTO
	 * @return
	 */
	@InheritInverseConfiguration
	EmployeeAddress fromDTO( EmployeeAddressDTO employeeAddressDTO );
	
	/**
	 * @param employeeAddresses
	 * @return
	 */
	List<EmployeeAddressDTO> toDTOs( List<EmployeeAddress> employeeAddresses );
	
	/**
	 * @param employeeAddressDTOs
	 * @return
	 */
	@InheritInverseConfiguration
	List<EmployeeAddress> fromDTOs( List<EmployeeAddressDTO> employeeAddressDTOs );

}
