package com.wood.esm.detailservice.profile.service.base;

import org.springframework.beans.factory.annotation.Autowired;

import brave.Tracer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.XSlf4j;

/**
 * @author chinn
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XSlf4j
public class BaseService 
{
	
	@Autowired
	private Tracer tracer;
	
	protected String getTraceId()
	{
		String traceId = tracer.currentSpan().context().traceIdString();
		log.debug( "Trace Id" + traceId );
		return traceId;
	}

}
