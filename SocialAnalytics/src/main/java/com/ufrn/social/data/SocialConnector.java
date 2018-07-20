package com.ufrn.social.data;

import java.util.List;

import com.ufrn.social.model.Post;

public abstract class SocialConnector {
	
	public SocialConnector(String fonte){
		this.fonte = fonte;
	}
	
	private String fonte; //Twitter, facebook, google plus...
	
	public abstract List<Post> findPosts(String searchString, String user, int limit, String dateQuerySince, String dateQueryUntil, boolean hasDate);

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

}
