package com.wood.esm.detailservice.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wood.esm.detailservice.profile.DTO.ContactDTO;
import com.wood.esm.detailservice.profile.domain.Contact;
import com.wood.esm.detailservice.profile.mapper.ContactMapper;
import com.wood.esm.detailservice.profile.repository.ContactRepository;
import com.wood.esm.detailservice.profile.service.base.BaseService;
import com.wood.esm.detailservice.profile.web.response.ContactGetResponse;
import com.wood.esm.detailservice.profile.web.response.ContactUpdateResponse;

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
public class ContactService extends BaseService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactMapper contactMapper;

	/**
	 * @param employeeInfoId
	 * @param employeeCode
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public ContactGetResponse getContacts(Integer employeeInfoId, String employeeCode) {

		log.entry();

		List<Contact> contactsRetrieved = contactRepository.findContactIdByEmployeeInfoIdAndEmployeeCode(employeeInfoId,
				employeeCode);

		List<ContactDTO> contactDTOsRetrieved = contactMapper.toDTOs(contactsRetrieved);

		ContactGetResponse contactGetResponse = new ContactGetResponse();
		contactGetResponse.setContactDTOs(contactDTOsRetrieved);

		log.exit();
		return contactGetResponse;
	}

	/**
	 * @param contactDTO
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ContactUpdateResponse updateContacts(List<ContactDTO> contactDTOs) throws Exception {
		log.entry();

		String traceId = getTraceId();
		ContactUpdateResponse contactUpdateResponse = new ContactUpdateResponse();

		contactDTOs.forEach(contactDTO -> {
			contactDTO.setTransactionKey(traceId);
		});
		List<Contact> contactsRetrieved = contactMapper.fromDTOs(contactDTOs);
		contactRepository.updateContacts(contactsRetrieved);

		log.exit();
		return contactUpdateResponse;

	}

}
