package com.elreddit.util;

/**
 * This utility class is used to operate different functions on method's parameters.
 */
public final class ParametersUtil {
	
	private ParametersUtil(){}
	
	/**
	 * This static method is used to check if any of the given parameters is <b>null</b>.
	 * 
	 * @param array of parameters
	 * @throws NullPointerException - if any of the given parameter is <b>null</b>.
	 */
	public static void checkNullParameters(Object... paras){
		synchronized (paras) {
			if(paras != null){
				for(Object o : paras){
					if(o == null){
						throw new NullPointerException();
					}
				}
			}
		}		
	}
}
