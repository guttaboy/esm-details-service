package com.wood.esm.detailservice.common.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

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
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseInformation 
{
	
	@Transient
	RowActionCodeEnum rowAction = RowActionCodeEnum.NOACTION;
	
	@Column( name = "XTN_KEY" )
	private String transactionKey;
	
	@Column( name = "UPD_USR_NM" )
	private String updateUserId;
	
	@Column( name = "CRTE_REC_TS", insertable = false, updatable = false )
	private LocalDateTime createTimeStamp;
	
	@Column( name = "UPD_REC_TS", insertable = false, updatable = false )
	private LocalDateTime updateTimeStamp;

}
