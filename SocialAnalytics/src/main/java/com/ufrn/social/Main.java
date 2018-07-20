package com.ufrn.social;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		
		
	    //http://138.197.98.244/polarizador/sentiment
		
		String query_url = "http://138.197.98.244/polarizador/sentiment";
        String json = "{ \"sentences\":[\"testando\",\"teste2\"]}";
        try {
        URL url = new URL(query_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes("UTF-8"));
        os.close(); 
        // read the response
        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = IOUtils.toString(in, "UTF-8");
        System.out.println(result);
        System.out.println("result after Reading JSON Response");
        JSONObject myResponse = new JSONObject(result);
        //System.out.println("jsonrpc- "+myResponse.getString("jsonrpc"));
        //System.out.println("id- "+myResponse.getInt("id"));
        //System.out.println("result- "+myResponse.getString("result"));
        in.close();
        conn.disconnect();

        ArrayList<String> sentimentos = new ArrayList<String>();
        JSONArray array = myResponse.getJSONArray("sentiments");
        
        for(int i = 0 ; i<array.length() ; i++){
        	sentimentos.add(array.get(i).toString());
        }
        
        
        } catch (Exception e) {
			System.out.println(e);
		}
		

	}

}
