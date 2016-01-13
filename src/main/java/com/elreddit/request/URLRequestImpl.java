package com.elreddit.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.elreddit.util.ParametersUtil;

public class URLRequestImpl implements URLRequest{

	private static final int CLIENT_ERROR_CODE = 400;
	
	@Override
	public String getHttpResponse(URL url) {
		ParametersUtil.checkNullParameters(url);
		String result = null;
		try{
			result = getResponse(url);
		}catch(IOException ex){
			System.out.println("URLRequestImpl.getHttpResponse - IOException: " + ex.getMessage());
		}
		return result;
	}
	
	private String getResponse(URL url) throws IOException{
		ParametersUtil.checkNullParameters(url);
		
		StringBuilder sb = null;	
		HttpURLConnection conn = null;
		InputStreamReader inputStream = null;
		BufferedReader buff = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			// Create a custom User-Agent, to avoid the "Too Many Requests" response error.
			conn.setRequestProperty("User-Agent", "linux:com.elreddit.request:v1.0");
			int statusCode = conn.getResponseCode();
			// If status of the HTTP response is not a client error or server error then proceed.
			if(statusCode < CLIENT_ERROR_CODE){
				sb = new StringBuilder();
				inputStream = new InputStreamReader(conn.getInputStream());
				buff = new BufferedReader(inputStream);
				String line;
				while((line = buff.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}
			}
		} finally {
			// Closing the input stream.
			if(inputStream != null){
				inputStream.close();
			}
			// Disconnect the connection in any case either there is an exception or not.
			if(conn != null){
				conn.disconnect();
			}			
		}
		return sb != null ? sb.toString() : null;
	}
	
}
