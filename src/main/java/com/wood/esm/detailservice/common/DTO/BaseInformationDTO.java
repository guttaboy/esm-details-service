package com.wood.esm.detailservice.common.DTO;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wood.esm.detailservice.common.enums.DateTimePattern;
import com.wood.esm.detailservice.common.enums.RowActionCodeEnum;

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
public class BaseInformationDTO 
{
	
	RowActionCodeEnum rowAction = RowActionCodeEnum.NOACTION;
	
	private String transactionKey;
	
	private String updateUserId;
	
	@JsonFormat( pattern = DateTimePattern.DateTimePattern )
	private LocalDateTime createTimeStamp;
	
	@JsonFormat( pattern = DateTimePattern.DateTimePattern )
	private LocalDateTime updateTimeStamp;

}
