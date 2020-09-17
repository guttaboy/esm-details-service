package com.wood.esm.detailservice.common.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chinn
 *
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DateInformation extends BaseInformation
{
	
	@Column( name = "EFF_DT" )
	private LocalDate effectiveDate;
	
	@Column( name = "END_DT" )
	private LocalDate endDate;

}
