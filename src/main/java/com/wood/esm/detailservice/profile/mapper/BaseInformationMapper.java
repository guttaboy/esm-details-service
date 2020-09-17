package com.wood.esm.detailservice.profile.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wood.esm.detailservice.common.DTO.BaseInformationDTO;
import com.wood.esm.detailservice.common.domain.BaseInformation;

/**
 * @author chinn
 *
 */
@Mapper
public interface BaseInformationMapper 
{
	
	BaseInformationMapper INSTANCE = Mappers.getMapper( BaseInformationMapper.class );
	
	/**
	 * method: toDTO
	 * @param baseInformation
	 * @return
	 */
	BaseInformationDTO toDTO( BaseInformation baseInformation);
	
	/**
	 * method: fromDTO
	 * @param baseInformationDTO
	 * @return
	 */
	@InheritInverseConfiguration
	BaseInformation fromDTO( BaseInformationDTO baseInformationDTO );

}
