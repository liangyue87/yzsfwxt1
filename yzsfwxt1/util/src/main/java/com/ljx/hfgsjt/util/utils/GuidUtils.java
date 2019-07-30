package com.ljx.hfgsjt.util.utils;

import java.util.Date;
import java.util.UUID;

/**
 * <p>Guid生成工具类</p>
 * ClassName:GuidUtils
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * @author   WZJ
 * @Date	 2017年4月20日 下午3:31:17
 */
public class GuidUtils {
	public String guidUtiles(){
		return (new Date().getTime()+"");
	}
	public String consNoUtiles(){
		return (new Date().getYear()+"-"+new Date().getMonth()+new Date().getDate()+new Date().getHours()+new Date().getMinutes()+new Date().getSeconds()+new Date().getSeconds());
	}
	/**
	 * 根据UUID生成guid
	 * @return
	 */
	public static String getGuid(){
		String str=UUID.randomUUID().toString();
		return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
	}
}
