package com.wood.esm.detailservice.profile.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wood.esm.detailservice.profile.DTO.ContactDTO;
import com.wood.esm.detailservice.profile.domain.Contact;

/**
 * @author chinn
 *
 */
@Mapper( componentModel = "spring" )
public interface ContactMapper 
{
	
	ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );
	
	/**
	 * @param contact
	 * @return
	 */
	@Mapping( source = "employeeInformation.employeeInfoId", target = "employeeInfoId" )
	ContactDTO toDTO( Contact contact );
	
	/**
	 * @param contactDTO
	 * @return
	 */
	@InheritInverseConfiguration
	Contact fromDTO( ContactDTO contactDTO );
	
	/**
	 * @param contacts
	 * @return
	 */
	List<ContactDTO> toDTOs( List<Contact> contacts );
	
	/**
	 * @param contactDTOs
	 * @return
	 */
	@InheritInverseConfiguration
	List<Contact> fromDTOs( List<ContactDTO> contactDTOs );

}
