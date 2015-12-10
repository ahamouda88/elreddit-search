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
	public String getHttpResponse(URL url) throws IOException {
		ParametersUtil.checkNullParameters(url);
		
		String result = getResponse(url);
		return result;
	}
	
	private String getResponse(URL url) throws IOException {
		ParametersUtil.checkNullParameters(url);
		
		StringBuilder sb = new StringBuilder();	
		HttpURLConnection conn = null;
		InputStreamReader inputStream = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			int statusCode = conn.getResponseCode();
			// If status of the HTTP response is not a client error or server error then proceed.
			if(statusCode < CLIENT_ERROR_CODE){
				inputStream = new InputStreamReader(conn.getInputStream());
				BufferedReader buff = new BufferedReader(inputStream);
				String line;
				while((line = buff.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}
			}
		} finally{
			// Disconnect the connection in any case either there is an exception or not.
			if(conn != null){
				conn.disconnect();
			}
			// Closing the input stream.
			if(inputStream != null){
				inputStream.close();
			}
		}
		return sb.toString();
	}
	
}
