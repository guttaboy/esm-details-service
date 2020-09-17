package com.wood.esm.detailservice.profile.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "EMP_ACT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeActivation extends BaseInformation {

	@Column(name = "EMP_ACT_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_activation_generator")
	@SequenceGenerator(name = "employee_activation_generator", sequenceName = "EMP_ACT", allocationSize = 1)
	private Integer employeeActivationId;

	@Column(name = "EMP_CD")
	private String employeeCode;

	@Column(name = "USR_NM")
	private String userName;

	@Column(name = "PSWRD")
	private String password;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeActivation")
	private List<EmployeeInformation> employeeInformations;

}
