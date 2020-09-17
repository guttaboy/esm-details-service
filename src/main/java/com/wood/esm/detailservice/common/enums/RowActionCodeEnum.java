package com.wood.esm.detailservice.common.enums;

/**
 * @author chinn
 *
 */
public enum RowActionCodeEnum 
{
	
	NOACTION,
	INSERT,
	UPDATE,
	DELETE;
	
	/**
	 * method: value
	 * @return
	 */
	public String value()
	{
		return name();
	}
	
	public static RowActionCodeEnum fromvalue( String v )
	{
		
		return valueOf (v);
		
	}

}
