package org.httpcustom.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class TestCustomAction {

	public static void main(String[] args) throws IOException, JSONException {
		
		String url="http://10.0.0.115:8080/wm/acl/rules/json";
		URL object=new URL(url);

		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		
		
		JSONObject rule = new JSONObject();
		rule.put("src-ip","10.0.0.1/32");
		rule.put("dst-ip", "10.0.0.2/32");
		rule.put("action", "deny");


		OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
		wr.write(rule.toString());
		wr.flush();
		
		StringBuilder sb = new StringBuilder();  
		int HttpResult = con.getResponseCode(); 
		
		if(HttpResult == HttpURLConnection.HTTP_OK){
		    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));  
		    String line = null;  
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }  

		    br.close();  

		    System.out.println(""+sb.toString());  

		}else{
		    System.out.println(con.getResponseMessage());  
		}  
	}

}
