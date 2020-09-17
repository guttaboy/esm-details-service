package com.wood.esm.detailservice.common.DTO;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wood.esm.detailservice.common.enums.DateTimePattern;

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
public class DateInformationDTO extends BaseInformationDTO 
{
	
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = DateTimePattern.DatePattern )
	private LocalDate effectiveDate;
	
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = DateTimePattern.DatePattern )
	private LocalDate endDate;


}
