package com.wood.esm.detailservice.profile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wood.esm.detailservice.common.domain.BaseInformation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chinn
 *
 */
@Entity
@Table(name = "CNTC")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact extends BaseInformation {

	@Column(name = "CNTC_ID")
	@Id
	/*
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	 * "contact_generator")
	 * 
	 * @SequenceGenerator(name = "contact_generator", sequenceName = "",
	 * allocationSize = 1)
	 */
	private Integer contactId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_INFO_ID", updatable = false)
	private EmployeeInformation employeeInformation;

	@Column(name = "EMP_CD")
	private String employeeCode;

	@Column(name = "EML_ID")
	private String emailId;

	@Column(name = "HME_PHN_NBR")
	private Integer homePhoneNumber;

	@Column(name = "WRK_PHN_NBR")
	private Integer workPhoneNumber;

}
