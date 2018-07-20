package com.ufrn.social.data;

import java.util.ArrayList;
import java.util.List;

import com.ufrn.social.model.Post;

public class FacebookConnector extends SocialConnector {
	
	public FacebookConnector(String fonte) {
		super(fonte);
	}

	public List<Post> findPosts(String searchString, String user, int limit, String dateQuerySince,
			String dateQueryUntil, boolean hasDate) {
		// Conexão com o Facebook para capturar os posts
		return new ArrayList<Post>();
	}
	
}
