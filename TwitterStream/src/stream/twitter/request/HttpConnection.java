package stream.twitter.request;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpConnection {
	
	public StringBuffer request(String urlParameters, String url) throws IOException{
    	URL obj = new URL(url);
    	HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
    	
		//add reuqest header
		con.setRequestMethod("POST");
	 	con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    	// Send post request
    			con.setDoOutput(true);
    			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    			wr.writeBytes(urlParameters);
    			wr.flush();
    			wr.close();

    			int responseCode = con.getResponseCode();
    			System.out.println("\nSending 'POST' request to URL : " + url);
    			System.out.println("Post parameters : " + urlParameters);
    			System.out.println("Response Code : " + responseCode);

    			BufferedReader in = new BufferedReader(
    			        new InputStreamReader(con.getInputStream()));
    			String inputLine;
    			StringBuffer response = new StringBuffer();

    			while ((inputLine = in.readLine()) != null) {
    				response.append(inputLine);
    			}
    			in.close();

    			  
    			System.out.println(response.toString());
    			return response;
    }

}
