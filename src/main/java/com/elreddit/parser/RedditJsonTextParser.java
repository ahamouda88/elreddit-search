package com.elreddit.parser;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.elreddit.util.ParametersUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A Class that implements the JsonTextParser interface, and uses Jackson API
 * to parse and traverse Json content.
 */
public class RedditJsonTextParser implements JsonTextParser{
	
	private final Object mutux = new Object();
	private final String DATA_NODE = "data";
	private final String CHILDREN_NODE = "children";
	private final ObjectMapper mapper;
	private final Map<String, List<JsonNode>> attributeMap;
	
	public RedditJsonTextParser() {
		mapper = new ObjectMapper();
		// Using a new comparator to sort strings in case insensitive order.
		attributeMap = new TreeMap<String, List<JsonNode>>((s1 ,s2) -> s1.toLowerCase().compareTo(s2.toLowerCase()));
	}
	
	@Override
	public String sortTextBy(String jsonText, String attrName) {	
		ParametersUtil.checkNullParameters(attrName, jsonText);
		
		String sortedResponse = null;
		try {
			synchronized(mutux) {
				attributeMap.clear();
				JsonNode rootNode = mapper.readTree(jsonText);
				
				parseFromJsonToMap(rootNode, attrName);
				List<JsonNode> list = createJsonNodeList();
				JsonNode dataNode = rootNode.get(DATA_NODE);
				if(dataNode != null){
					if(dataNode.isObject()){
						ObjectNode s = (ObjectNode) dataNode;
						ArrayNode arrayNode = s.putArray(CHILDREN_NODE);				
						arrayNode.addAll(list);	
					}
					sortedResponse = rootNode.toString();
				}
			}
		} catch (IOException e) {
			System.out.println("RedditJsonTextParser.sortTextBy - IOException: " + e.getMessage());
		}
		return sortedResponse;
	}
	
	/*
	 * A Method for parse a Json text and add it to map.
	 */
	private void parseFromJsonToMap(JsonNode rootNode, String attrName){
		synchronized(mutux){
			JsonNode dataNode = rootNode.get(DATA_NODE);	
			if(dataNode != null){
			    JsonNode children = dataNode.get(CHILDREN_NODE);
			    if(children != null){
				    Iterator<JsonNode> iterator = children.iterator();
				    while(iterator.hasNext()){	    	
						JsonNode entry = iterator.next();
						iterator.remove();
						JsonNode key = entry.findValue(attrName);
						if(key != null){
							String keyValue = key.textValue();
							List<JsonNode> nodes = attributeMap.get(keyValue) == null ? 
									new LinkedList<JsonNode>() : attributeMap.get(keyValue);
							nodes.add(entry);
							attributeMap.put(keyValue, nodes);
						}
					}
			    }
			}
		}
	}
	
	private List<JsonNode> createJsonNodeList(){
		synchronized(mutux){
			List<JsonNode> list = new LinkedList<JsonNode>();
			for(List<JsonNode> tmpList : attributeMap.values()){
				list.addAll(tmpList);
			}
			return list;
		}
	}
}
