package com.wood.esm.detailservice.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wood.esm.detailservice.implementations.UserNameImplementation;
import com.wood.esm.detailservice.profile.DTO.EmployeeActivationDTO;
import com.wood.esm.detailservice.profile.DTO.EmployeeInformationDTO;
import com.wood.esm.detailservice.profile.domain.EmployeeActivation;
import com.wood.esm.detailservice.profile.domain.EmployeeInformation;
import com.wood.esm.detailservice.profile.mapper.EmployeeActivationMapper;
import com.wood.esm.detailservice.profile.mapper.EmployeeInformationMapper;
import com.wood.esm.detailservice.profile.repository.EmployeeActivationRepository;
import com.wood.esm.detailservice.profile.repository.EmployeeInformationRepository;
import com.wood.esm.detailservice.profile.service.base.BaseService;
import com.wood.esm.detailservice.profile.web.response.EmployeeActivationGetResponse;
import com.wood.esm.detailservice.profile.web.response.EmployeeActivationResponse;
import com.wood.esm.detailservice.profile.web.response.EmployeeActivationUpdateResponse;

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
public class EmployeeActivationService extends BaseService {

	@Autowired
	private EmployeeActivationRepository employeeActivationRepository;

	@Autowired
	private EmployeeActivationMapper employeeActivationMapper;

	@Autowired
	private EmployeeInformationRepository employeeInformationRepository;

	@Autowired
	private EmployeeInformationService employeeInformationService;

	@Autowired
	private EmployeeInformationMapper employeeInformationMapper;

	@Autowired
	private UserNameImplementation userNameImplementation;

	/**
	 * Method: getEmployeeActivationDetails
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public EmployeeActivationGetResponse getEmployeeActivationDetails(String userName, String password) {
		log.entry();

		List<EmployeeActivation> employeeActivationRetrieved = employeeActivationRepository
				.findEmployeeActivationIdByUserNameAndPassword(userName, password);

		EmployeeActivationGetResponse employeeActivationGetResponse = new EmployeeActivationGetResponse();
		if (!employeeActivationRetrieved.isEmpty()) {
			List<EmployeeActivationDTO> employeeActivationDTOResult = employeeActivationMapper
					.toDTOs(employeeActivationRetrieved);
			employeeActivationGetResponse
					.setEmployeeActivationDTOs(retrieveAndMapEmployeeInformations(employeeActivationDTOResult.get(0)));
		} else {
			// TODO: Exception Handling
		}
		log.exit();
		return employeeActivationGetResponse;

	}

	/**
	 * Method: retrieveAndMapEmployeeInformations
	 * 
	 * @param employeeActivationDTOs
	 * @return
	 */
	private EmployeeActivationDTO retrieveAndMapEmployeeInformations(EmployeeActivationDTO employeeActivationDTO) {
		List<EmployeeInformation> employeeInformationsRetrieved = employeeInformationRepository
				.findEmployeeInformationIdByEmployeeActivationIdAndEmployeeCode(
						employeeActivationDTO.getEmployeeActivationId(), employeeActivationDTO.getEmployeeCode());
		if (employeeInformationsRetrieved.isEmpty()) {
			// TODO: throw exception
		}
		List<EmployeeInformationDTO> employeeInformationsDTOResult = employeeInformationMapper
				.toDTOs(employeeInformationsRetrieved);
		if (employeeInformationsDTOResult.size() > 0) {
			employeeActivationDTO.setEmployeeInformationDTOs(employeeInformationsDTOResult);
		}
		return employeeActivationDTO;
	}

	/**
	 * Method: updateEmployeeActivation
	 * 
	 * @param employeeActivationDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public EmployeeActivationUpdateResponse updateEmployeeActivation(EmployeeActivationDTO employeeActivationDTO)
			throws Exception {
		log.entry();
		String traceId = getTraceId();
		EmployeeActivationUpdateResponse employeeActivationUpdateResponse = new EmployeeActivationUpdateResponse();
		employeeActivationDTO.setTransactionKey(traceId);

		// set userName
		if (employeeActivationDTO.getUserName() == null || employeeActivationDTO.getUserName().isEmpty()) {
			String userName = userNameImplementation.generateUserName(employeeActivationDTO);

			while (!employeeActivationRepository.findEmployeeActivationByUserName(userName).isEmpty()) {
				userName = userNameImplementation.generateUserName(employeeActivationDTO);
			}

			employeeActivationDTO.setUserName(userName);
		} else {
			// Junit test case check
			employeeActivationUpdateResponse.setStatus("Do Not Provide UserName");
		}

		// Password check!!
		boolean pass = false;
		pass = passwordvalidation(employeeActivationDTO);

		EmployeeActivation employeeActivation = employeeActivationMapper.fromDTO(employeeActivationDTO);

		try {
			switch (employeeActivationDTO.getRowAction()) {
			case INSERT: {
				if (pass == false) {
					employeeActivationUpdateResponse.setStatus("Your Password must match the requirement");
					break;
				} else {
				employeeActivationRepository.updateEmployeeActivation(employeeActivation);

				// retrieve EmployeeActivationID using saveAndFlush
				// method in repository layer.
				Integer employeeActivationIdRetrieved = employeeActivation.getEmployeeActivationId();

				// Upating Informations when DTOs are passed.
				if (!employeeActivationDTO.getEmployeeInformationDTOs().isEmpty()) {

					List<EmployeeInformationDTO> employeeInformationDTOs = employeeActivationDTO
							.getEmployeeInformationDTOs();

					// set employeeActivationId to all employeeInformation objects
					employeeInformationDTOs.forEach(employeeInformationDTO -> {
						employeeInformationDTO.setEmployeeActivationId(employeeActivationIdRetrieved);
					});
					employeeInformationService.updateEmployeeInformations(employeeInformationDTOs);
				}
				EmployeeActivationResponse employeeActivationResponse = new EmployeeActivationResponse();
				employeeActivationResponse.setEmployeeActivationId(employeeActivationIdRetrieved);
				employeeActivationResponse.setUserName(employeeActivationDTO.getUserName());
				employeeActivationUpdateResponse.setEmployeeActivationResponse(employeeActivationResponse);
				employeeActivationUpdateResponse.setStatus("Success :D");
				
				break;
				}
			}
			case UPDATE: {
				employeeActivationUpdateResponse.setStatus("no updates are Allowed:C");
				break;
			}
			case DELETE: {
				employeeActivationUpdateResponse.setStatus("Thanks for Trying it! wait for the result:X");
				break;
			}
			case NOACTION: {
				// updateEmployeeInformationDetails(traceId);
				break;
			}
			default:

			{
				// TODO future code
				throw new RuntimeException(employeeActivationDTO.getRowAction().name() + " Invalid Row Action");
			}
			}
			
		} catch (Exception ex) {

			// if any exceptions set status to fail
			employeeActivationUpdateResponse.setStatus("Fail");

		}
		log.exit();
		return employeeActivationUpdateResponse;

	}

	/**
	 * Method: passwordCheck
	 * 
	 * @param employeeActivationDTO
	 * @return
	 */
	public boolean passwordvalidation(EmployeeActivationDTO employeeActivationDTO) {
		String passwd = employeeActivationDTO.getPassword();
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
		return passwd.matches(pattern);

	}

}
