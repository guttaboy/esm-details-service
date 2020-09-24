package com.wood.esm.detailservice.profile.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wood.esm.detailservice.profile.DTO.EmployeeInformationDTO;
import com.wood.esm.detailservice.profile.domain.Contact;
import com.wood.esm.detailservice.profile.domain.EmployeeAddress;
import com.wood.esm.detailservice.profile.domain.EmployeeInformation;
import com.wood.esm.detailservice.profile.mapper.ContactMapper;
import com.wood.esm.detailservice.profile.mapper.EmployeeAddressMapper;
import com.wood.esm.detailservice.profile.mapper.EmployeeInformationMapper;
import com.wood.esm.detailservice.profile.repository.ContactRepository;
import com.wood.esm.detailservice.profile.repository.EmployeeAddressRepository;
import com.wood.esm.detailservice.profile.repository.EmployeeInformationRepository;
import com.wood.esm.detailservice.profile.service.base.BaseService;
import com.wood.esm.detailservice.profile.web.response.EmployeeInformationGetResponse;
import com.wood.esm.detailservice.profile.web.response.EmployeeInformationUpdateResponse;

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
public class EmployeeInformationService extends BaseService {

	@Autowired
	private EmployeeInformationRepository employeeInformationRepository;

	@Autowired
	private EmployeeInformationMapper employeeInformationMapper;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactMapper contactMapper;
	
	@Autowired
	private EmployeeAddressMapper employeeAddressMapper;
	
	@Autowired
	private EmployeeAddressRepository employeeAddressRepository;

	/**
	 * Method: getEmployeeInformationDetails
	 * 
	 * @param employeeActivationId
	 * @param employeeCode
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public EmployeeInformationGetResponse getEmployeeInformationDetails(Integer employeeActivationId,
			String employeeCode) {

		log.entry();
		List<EmployeeInformation> employeeInformationRetrieved = employeeInformationRepository
				.findEmployeeInformationIdByEmployeeActivationIdAndEmployeeCode(employeeActivationId, employeeCode);

		List<EmployeeInformationDTO> employeeInformationDTOs = employeeInformationMapper
				.toDTOs(employeeInformationRetrieved);
		retrieveAndMapContacts(employeeInformationDTOs);

		EmployeeInformationGetResponse employeeInformationGetResponse = new EmployeeInformationGetResponse();
		employeeInformationGetResponse.setEmployeeInformationDTOs(employeeInformationDTOs);

		log.exit();
		return employeeInformationGetResponse;

	}

	/**
	 * Method: retrieveAndMapContacts
	 * 
	 * @param employeeInformationDTO
	 * @return
	 */
	private void retrieveAndMapContacts(List<EmployeeInformationDTO> employeeInformationDTOs) {
		employeeInformationDTOs.stream().forEach((employeeInformationDTO) -> {
			employeeInformationDTO
					.setContactDTOs(contactMapper.toDTOs(contactRepository.findContactIdByEmployeeInfoIdAndEmployeeCode(
							employeeInformationDTO.getEmployeeInfoId(), employeeInformationDTO.getEmployeeCode())));
		});
	}

	/**
	 * method: updateEmployeeInformations
	 * 
	 * @param employeeInformationDTOs
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public EmployeeInformationUpdateResponse updateEmployeeInformations(
			List<EmployeeInformationDTO> employeeInformationDTOs) throws Exception {
		log.entry();

		String traceId = getTraceId();
		EmployeeInformationUpdateResponse employeeInformationUpdateResponse = new EmployeeInformationUpdateResponse();

		employeeInformationDTOs.forEach(employeeInformationDTO -> {
			employeeInformationDTO.setTransactionKey(traceId);

			EmployeeInformation employeeInformationRetrieved = employeeInformationMapper
					.fromDTO(employeeInformationDTO);

			List<Contact> contactReferences = null;
			if (CollectionUtils.isNotEmpty(employeeInformationDTO.getContactDTOs())) {
				employeeInformationDTO.getContactDTOs().forEach(contactDTO -> {
					contactDTO.setTransactionKey(traceId);
				});
				contactReferences = contactMapper.fromDTOs(employeeInformationDTO.getContactDTOs());
			}
			
			List<EmployeeAddress> addressReferences = null;
			if (CollectionUtils.isNotEmpty(employeeInformationDTO.getEmployeeAddressDTOs())) {
				employeeInformationDTO.getEmployeeAddressDTOs().forEach(employeeAddressDTO -> {
					employeeAddressDTO.setTransactionKey(traceId);
				});
				addressReferences = employeeAddressMapper.fromDTOs(employeeInformationDTO.getEmployeeAddressDTOs());
			}
			try {
				switch (employeeInformationDTO.getRowAction()) {
				case INSERT: {
					employeeInformationRepository.updateEmployeeInformations(employeeInformationRetrieved);

					// Retrieved employeeInfoId from saveAndFlush Method in Repository
					if (CollectionUtils.isNotEmpty(contactReferences)) {
						contactReferences.forEach(contact -> {
							contact.setEmployeeInformation(employeeInformationRetrieved);
						}); 
						contactRepository.updateContacts(contactReferences);
					}
					if (CollectionUtils.isNotEmpty(addressReferences)) {
						addressReferences.forEach(address -> {
							address.setEmployeeInformation(employeeInformationRetrieved);
						}); 
						employeeAddressRepository.updateEmployeeAddresses(addressReferences);
					}
					
					break;
				}
				case UPDATE: {
					employeeInformationRepository.updateEmployeeInformations(employeeInformationRetrieved);
					if (CollectionUtils.isNotEmpty(contactReferences)) {
						contactReferences.forEach(contact -> {
							contact.setEmployeeInformation(employeeInformationRetrieved);
						});
						contactRepository.updateContacts(contactReferences);
					}
					if (CollectionUtils.isNotEmpty(addressReferences)) {
						addressReferences.forEach(address -> {
							address.setEmployeeInformation(employeeInformationRetrieved);
						}); 
						employeeAddressRepository.updateEmployeeAddresses(addressReferences);
					}
					break;
				}
				case DELETE: {
					if (CollectionUtils.isNotEmpty(contactReferences)) {
						contactRepository.deleteAll(contactReferences);
					}
					if (CollectionUtils.isNotEmpty(addressReferences)) {
						employeeAddressRepository.deleteAll(addressReferences);
					}
					employeeInformationRepository.delete(employeeInformationRetrieved);
					break;
				}
				case NOACTION: {
					if (CollectionUtils.isNotEmpty(contactReferences)) {
						contactRepository.updateContacts(contactReferences);
					}
					if (CollectionUtils.isNotEmpty(addressReferences)) {
						employeeAddressRepository.updateEmployeeAddresses(addressReferences);
					}
					break;
				}
				default: {
					throw new RuntimeException(employeeInformationDTO.getRowAction().name() + "Invalid Row Action");
				}
				}
				employeeInformationUpdateResponse.setStatus("UPDATED SUCCESSFULLY");
			} catch (Exception e) {
				employeeInformationUpdateResponse.setStatus("FAIL");
			}

		});

		return employeeInformationUpdateResponse;
	}

}
