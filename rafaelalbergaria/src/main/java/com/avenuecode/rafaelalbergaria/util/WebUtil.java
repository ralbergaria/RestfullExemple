package com.avenuecode.rafaelalbergaria.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * 
 * @author Rafael Albergaria
 * Classe com utilidades
 */
public class WebUtil {
	
	/**
	 * Classe utilizada para realizar as chamadas aos serviços rest criados.
	 * @param json
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String callRestService(String json, String url, String mediaType) throws IOException {
		
    	URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", mediaType);
		con.setRequestProperty("Accept-Charset", "UTF-8");
		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
		
		writer.write(json);
		writer.flush();
		writer.close();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		con.disconnect();		
 		
		return response.toString();
		
	}
	/**
	 * Classe utilizada para realizar as chamadas aos serviços rest criados.
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String callRestService(String url, String mediaType) throws IOException {
		
    	URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", mediaType);
		con.setRequestProperty("Accept-Charset", "UTF-8");
				
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		con.disconnect();		
 		
		return response.toString();
		
	}
}
