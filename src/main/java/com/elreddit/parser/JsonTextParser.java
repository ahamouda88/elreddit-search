package com.elreddit.parser;

/**
 * An interface that represents simple methods operating on a Json formated text.
 */
public interface JsonTextParser {
	
	/**
	 * This method is used to sort a Json formated text according to a particular attribute,
	 * given the attribute name, and the Json text.
	 * 
	 * @param jsonText - a Json text.
	 * @param attrName - an attribute name.
	 * @return the Json text after sorting. Returns <b>null</b> if input content is invalid.
	 * 
	 * @throws NullPointerException if any of the given parameter is <b>null</b>.
	 */
	public String sortTextBy(String jsonText, String attrName);
}
