package stream.twitter.main;

import java.io.IOException;

import stream.twitter.service.TwitterStreamService;

public class Main {

	public static void main(String[] args) throws IOException{
		
		TwitterStreamService stream = new TwitterStreamService();
		stream.startStream();
		
	}
	
	
}
