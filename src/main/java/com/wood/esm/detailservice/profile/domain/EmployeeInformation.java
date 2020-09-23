package com.wood.esm.detailservice.profile.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wood.esm.detailservice.common.domain.DateInformation;

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
@Table(name = "EMP_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeInformation extends DateInformation {

	@Column(name = "EMP_INFO_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeInfoId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ACT_ID", updatable = false)
	private EmployeeActivation employeeActivation;

	@Column(name = "EMP_CD")
	private String employeeCode;

	@Column(name = "EMP_FRST_NM")
	private String employeeFirstName;

	@Column(name = "EMP_MID_NM")
	private String employeeMiddleName;

	@Column(name = "EMP_LST_NM")
	private String employeeLastName;

	@Column(name = "EMP_GOV_ISS_ID_NBR")
	private String employeeGovernmentIssueIdNuber;

	@Column(name = "GOV_ISS_ID_TYP")
	private String governmentIssueIdType;

	@Column(name = "EMP_DOB")
	private LocalDate employeeDOB;

	@Column(name = "EMP_SEX")
	private String employeeSex;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeInformation")
	private List<Contact> contacts;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeInformation")
	private List<EmployeeAddress> employeeAddresses;

}
