package com.wood.esm.detailservice.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wood.esm.detailservice.profile.DTO.EmployeeAddressDTO;
import com.wood.esm.detailservice.profile.domain.EmployeeAddress;
import com.wood.esm.detailservice.profile.mapper.EmployeeAddressMapper;
import com.wood.esm.detailservice.profile.repository.EmployeeAddressRepository;
import com.wood.esm.detailservice.profile.service.base.BaseService;
import com.wood.esm.detailservice.profile.web.response.EmployeeAddressGetResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.XSlf4j;

/**
 * @author chinn
 *
 */
@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XSlf4j
public class EmployeeAddressService extends BaseService {

	@Autowired
	private EmployeeAddressMapper employeeAddressMapper;

	@Autowired
	private EmployeeAddressRepository employeeAddressRepository;

	/**
	 * @param employeeInfoId
	 * @param employeeCode
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public EmployeeAddressGetResponse getEmployeeAddresses(Integer employeeInfoId, String employeeCode) {

		log.entry();
		List<EmployeeAddress> employeeAddressRetrieved = employeeAddressRepository
				.findEmployeeAddressIdByEmployeeInfoIdAndEmployeeCode(employeeInfoId, employeeCode);

		List<EmployeeAddressDTO> employeeAddressDTOsRetrieved = employeeAddressMapper.toDTOs(employeeAddressRetrieved);

		EmployeeAddressGetResponse employeeGetResponse = new EmployeeAddressGetResponse();

		employeeGetResponse.setEmployeeAddressDTOs(employeeAddressDTOsRetrieved);

		log.exit();
		return employeeGetResponse;
	}
}
