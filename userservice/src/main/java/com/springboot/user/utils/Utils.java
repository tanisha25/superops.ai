package com.springboot.user.utils;

import java.util.List;

/**
 * 
 * @author tanisha
 *
 */
public class Utils {

	public static <T> Boolean isEmpty(T object)
	{
		if(object != null && !"".equals(object))
		   return false;
		return true;
	}
	
	public static <T> Boolean isEmpty(String str)
	{
		if(str != null && str.length() > 0 && !str.trim().equals(""))
		   return false;
		return true;
	}
	
	public static <T> Boolean isEmpty(List<T> objects)
	{
		if(objects != null && objects.size() > 0)
			return false;
		return true;
	}

}
