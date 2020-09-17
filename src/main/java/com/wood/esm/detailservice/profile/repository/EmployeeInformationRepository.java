package com.wood.esm.detailservice.profile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wood.esm.detailservice.profile.domain.EmployeeInformation;

public interface EmployeeInformationRepository extends JpaRepository<EmployeeInformation, Integer> 
{
	
	/**
	 * Method: findEmployeeInformationIdByEmployeeActivationAndEmployeeCode
	 * @param employeeActivationId
	 * @param employeeCode
	 * @return
	 */
	@Query( "select employeeInformation from EmployeeInformation employeeInformation "
			+ "where employeeInformation.employeeActivation.employeeActivationId = :employeeActivationId "
			+ "and employeeInformation.employeeCode = :employeeCode" )
	@Transactional( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
	List<EmployeeInformation> findEmployeeInformationIdByEmployeeActivationIdAndEmployeeCode( @Param( "employeeActivationId") Integer employeeActivationId,
			@Param( "employeeCode") String employeeCode );
	
	/**
	 * @param employeeInformation
	 */
	@Transactional( readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = Exception.class )
	public default void updateEmployeeInformations( EmployeeInformation employeeInformation )
	{
		switch( employeeInformation.getRowAction())
		{
		case INSERT:
		{
			save( employeeInformation );
			break;
		}
		case UPDATE:
		{
			save( employeeInformation );
			break;
		}
		case DELETE:
		{
			delete(employeeInformation);
			break;
		}
		case NOACTION:
		{
			break;
		}
		default:
		{
			//TODO future code work
			throw new RuntimeException(employeeInformation.getRowAction().name() + "Invalid Row Action" );
		}
		}
	}
	
	/**
	 * @param employeeInformations
	 */
	@Transactional( readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = Exception.class )
	public default void updateEmployeeInformations( List<EmployeeInformation> employeeInformations )
	{
		
		employeeInformations.forEach(employeeInformation -> {
			updateEmployeeInformations( employeeInformation );
		});
		
	}

}
