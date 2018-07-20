package com.ufrn.social.handler;

public class FactoryHandler {
	
	public Handler getHandler(String type){
		if(type.equals("url")){
			return new UrlHandler();
		} else if(type.equals("hashtag")){
			return new HashtagHandler();
		} else {
			return null;
		}
	}

}
