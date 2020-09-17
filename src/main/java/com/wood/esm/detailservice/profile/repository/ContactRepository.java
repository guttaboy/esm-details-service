package com.wood.esm.detailservice.profile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wood.esm.detailservice.profile.domain.Contact;

/**
 * @author chinn
 *
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	/**
	 * @param employeeInfoId
	 * @param employeeCode
	 * @return
	 */
	@Query("select contact from Contact contact "
			+ "where contact.employeeInformation.employeeInfoId = :employeeInfoId "
			+ "and contact.employeeCode = :employeeCode")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	List<Contact> findContactIdByEmployeeInfoIdAndEmployeeCode(@Param("employeeInfoId") Integer employeeInfoId,
			@Param("employeeCode") String employeeCode);

	/**
	 * @param employeeInfoId
	 * @return
	 */
	@Query("select contact from Contact contact "
			+ "where contact.employeeInformation.employeeInfoId = :employeeInfoId ")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	List<Contact> findContactIdByEmployeeInfoId(@Param("employeeInfoId") Integer employeeInfoId);

	/**
	 * Method: updateContacts
	 * 
	 * @param contacts
	 */
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
	public default void updateContacts(List<Contact> contacts) {

		contacts.forEach(contact -> {
			switch (contact.getRowAction()) {
			case INSERT: {
				save(contact);
				break;
			}
			case UPDATE: {
				save(contact);
				break;
			}
			case DELETE: {
				delete(contact);
				break;
			}
			case NOACTION: {
				break;
			}
			default: {
				throw new RuntimeException(contact.getRowAction().name() + "Invalid Row Action");
			}
			}
		});
	}

}
