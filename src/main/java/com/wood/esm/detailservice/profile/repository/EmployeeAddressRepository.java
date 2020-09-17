package com.wood.esm.detailservice.profile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wood.esm.detailservice.profile.domain.EmployeeAddress;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Integer> 
{
	
	/**
	 * @param employeeInfoId
	 * @param employeeCode
	 * @return
	 */
	@Query( "select employeeAddress from EmployeeAddress employeeAddress "
			+ "where employeeAddress.employeeInformation.employeeInfoId = :employeeInfoId "
			+ "and employeeAddress.employeeCode = :employeeCode" )
	@Transactional( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
	List<EmployeeAddress> findEmployeeAddressIdByEmployeeInfoIdAndEmployeeCode( @Param( "employeeInfoId") Integer employeeInfoId,
			@Param( "employeeCode") String employeeCode );
	
	/**
	 * @param employeeInfoId
	 * @return
	 */
	@Query( "select employeeAddress from EmployeeAddress employeeAddress "
			+ "where employeeAddress.employeeInformation.employeeInfoId = :employeeInfoId " )
	@Transactional( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
	List<EmployeeAddress> findEmployeeAddressIdByEmployeeInfoId( @Param( "employeeInfoId") Integer employeeInfoId );

}
