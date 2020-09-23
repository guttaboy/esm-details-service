package com.wood.esm.detailservice.profile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "EMP_ADDR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeAddress extends BaseInformation {

	@Column(name = "EMP_ADDR_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeAddressId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_INFO_ID", updatable = false)
	private EmployeeInformation employeeInformation;

	@Column(name = "EMP_CD")
	private String employeeCode;

	@Column(name = "STATE_CD")
	private String stateCode;

	@Column(name = "CTY_NM")
	private String cityName;

	@Column(name = "CNTRY_CD")
	private String countryCode;

	@Column(name = "ADDR_LN_1")
	private String addressLine1;

	@Column(name = "ADDR_LN_2")
	private String addressLine2;

	@Column(name = "ZIP_CD")
	private Integer zipCode;

}
