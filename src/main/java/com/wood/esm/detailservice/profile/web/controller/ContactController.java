package com.wood.esm.detailservice.profile.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wood.esm.detailservice.profile.DTO.ContactDTO;
import com.wood.esm.detailservice.profile.service.ContactService;
import com.wood.esm.detailservice.profile.web.response.ContactGetResponse;
import com.wood.esm.detailservice.profile.web.response.ContactUpdateResponse;

import lombok.extern.slf4j.XSlf4j;

/**
 * @author chinn
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/profile/v1")
@XSlf4j
public class ContactController {

	@Autowired
	private ContactService contactService;

	/**
	 * @param employeeInfoId
	 * @param employeeCode
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/contacts")
	public ContactGetResponse getContacts(@RequestParam(required = true) Integer employeeInfoId,
			@RequestParam(required = true) String employeeCode) throws Exception {

		log.entry();

		ContactGetResponse contactGetResponse = contactService.getContacts(employeeInfoId, employeeCode);

		log.exit();
		return contactGetResponse;
	}

	/**
	 * @param contactDTOs
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/contacts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ContactUpdateResponse updateContacts(@RequestBody List<ContactDTO> contactDTOs) throws Exception {

		log.entry();
		ContactUpdateResponse contactUpdateResponse = new ContactUpdateResponse();

		contactUpdateResponse = contactService.updateContacts(contactDTOs);
		log.exit();

		return contactUpdateResponse;

	}

}
