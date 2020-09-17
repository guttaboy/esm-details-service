package com.wood.esm.detailservice.profile.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wood.esm.detailservice.common.DTO.DateInformationDTO;
import com.wood.esm.detailservice.common.domain.DateInformation;

/**
 * @author chinn
 *
 */
@Mapper( componentModel = "spring", uses = { BaseInformationMapper.class } )
public interface DateInformationMapper 
{
	
	DateInformationMapper INSTANCE = Mappers.getMapper( DateInformationMapper.class );
	
	/**
	 * method: toDTO
	 * @param dateInformation
	 * @return
	 */
	DateInformationDTO toDTO( DateInformation dateInformation);
	
	/**
	 * method: fromDTO
	 * @param dateInformationDTO
	 * @return
	 */
	@InheritInverseConfiguration
	DateInformation fromDTO( DateInformationDTO dateInformationDTO );

}
