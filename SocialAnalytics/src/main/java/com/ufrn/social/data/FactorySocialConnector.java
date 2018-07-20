package com.ufrn.social.data;

public class FactorySocialConnector {
	
	public SocialConnector getConnector(String fonte){
		if(fonte.equals("Twitter")){
			return new TwitterConnector(fonte);
		} else if(fonte.equals("Facebook")){
			return new FacebookConnector(fonte);
		} else {
			return null;
		}
	}

}