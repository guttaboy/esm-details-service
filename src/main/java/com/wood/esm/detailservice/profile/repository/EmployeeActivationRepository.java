package com.wood.esm.detailservice.profile.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wood.esm.detailservice.profile.domain.EmployeeActivation;
import com.wood.esm.detailservice.profile.web.response.EmployeeActivationResponse;
import com.wood.esm.detailservice.profile.web.response.EmployeeActivationUpdateResponse;

public interface EmployeeActivationRepository extends JpaRepository<EmployeeActivation, Integer> 
{
	
	/**
	 * Method: findEmployeeActivationIdByUserNameAndPassword
	 * @param userName
	 * @param password
	 * @return
	 */
	@Query( "select employeeActivation from EmployeeActivation employeeActivation "
			+ "where employeeActivation.userName = :userName "
			+ "and employeeActivation.password = :password" )
	@Transactional( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
	List<EmployeeActivation> findEmployeeActivationIdByUserNameAndPassword( @Param( "userName") String userName,
			@Param( "password") String password );
	
	/**
	 * @param userName
	 * @return
	 */
	@Query( "select employeeActivation from EmployeeActivation employeeActivation "
			+ "where employeeActivation.userName = :userName " )
	@Transactional( readOnly = true, propagation = Propagation.NOT_SUPPORTED )
	List<EmployeeActivation> findEmployeeActivationIdByUserName( @Param( "userName") String userName );
	
	/**
	 * @param employeeActivation
	 * @return
	 */
	@Transactional( readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = Exception.class )
	public default EmployeeActivationUpdateResponse updateEmployeeActivation( EmployeeActivation employeeActivation )
	{
		EmployeeActivationUpdateResponse updateResponse = new EmployeeActivationUpdateResponse();
		switch( employeeActivation.getRowAction() )
		{
		    case INSERT:
		    {
		    	save( employeeActivation );
		    	break;
		}
		    case UPDATE:
		    {
		    	save(employeeActivation);
		    	break;
		    }
		    case DELETE:
		    {
		    	if( StringUtils.equals( StringUtils.trimToEmpty(employeeActivation.getPassword() ), "JUNIT" ) )
		    	{
		    		delete(employeeActivation);
		    	}
		    	else
		    	{
		    		throw new RuntimeException( employeeActivation.getRowAction().name() + "Invalid Row Action" );
		    	}
		    	break;
		    }
		    case NOACTION:
	    	{
	    		break;
	    	}
	    	default:
	    	{
	    		throw new RuntimeException( employeeActivation.getRowAction().name() + "Invalid Row Action" );
	    	}
		}
		
		EmployeeActivationResponse employeeActivationResponse = new EmployeeActivationResponse( employeeActivation.getEmployeeActivationId(),
				employeeActivation.getUserName());
		updateResponse.setEmployeeActivationResponse(employeeActivationResponse);
		return updateResponse;
	}

}
