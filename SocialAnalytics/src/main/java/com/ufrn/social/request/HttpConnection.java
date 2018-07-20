package com.ufrn.social.request;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ufrn.social.model.Post;
import com.ufrn.social.utils.CommonThings;

public class HttpConnection {
	
	public ArrayList<String> getSentiments(List<Post> posts){
		
		String query_url = CommonThings.POLARIZADOR_URL;
		ArrayList<String> sentimentos = new ArrayList<String>();
		
        String json = mountJson(posts);
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
	        //System.out.println(result);
	        //System.out.println("result after Reading JSON Response");
	        JSONObject myResponse = new JSONObject(result);
	        //System.out.println("jsonrpc- "+myResponse.getString("jsonrpc"));
	        //System.out.println("id- "+myResponse.getInt("id"));
	        //System.out.println("result- "+myResponse.getString("result"));
	        in.close();
	        conn.disconnect();
	        
	        JSONArray array = myResponse.getJSONArray("sentiments");
	        
	        for(int i = 0 ; i<array.length() ; i++){
	        	sentimentos.add(array.get(i).toString());
	        }
        
        } catch (Exception e) {
			System.out.println(e);
		}
        
        return sentimentos;
	}
	
	public String mountJson(List<Post> posts){
		//"{ \"sentences\":[\"testando\",\"teste2\"]}";
		String json = "{ \"sentences\":[";
		
		for(int i = 0 ; i<posts.size() ;i++){
			Post p = posts.get(i);
			String postText = p.getText().replaceAll("\"", "");
			json += "\"" + postText + "\"";
			if(i+1<posts.size()){
				json += ",";
			}
		}
		json += "]}";
		
		//System.out.println(json);
		return json;
	}

}
