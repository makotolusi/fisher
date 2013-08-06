package com.fisher.app.util;

import java.util.Map;


public class Utils {
	public static final String CODE="code";
	public static final String MSG="msg";
	public static final String RESULT="result";
	public static final String IMG="img";
	
	public static Map<String, Object> resultSUC(Map<String, Object> result) {
		result.put(CODE, StateEnum.SUC.name());
		result.put(MSG, StateEnum.SUC.getMsg());
		if(result.containsKey(RESULT))
			result.put(RESULT, result.get(RESULT));
		return result;
	}

	
	public static Map<String, Object> resultFAL(Map<String, Object> result) {
		result.put(CODE, StateEnum.FAL.name());
		result.put(MSG, StateEnum.FAL.getMsg());
		return result;
	}
	
	public static Map<String, Object> resultExist(Map<String, Object> result) {
		result.put("CODE", StateEnum.EXIST.name());
		result.put(MSG, StateEnum.EXIST.getMsg());
		return result;
	}
	
}
