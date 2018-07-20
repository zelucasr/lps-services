package com.ufrn.social.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ufrn.social.model.Post;

import me.jhenrique.manager.TweetManager;
import me.jhenrique.manager.TwitterCriteria;
import me.jhenrique.model.Tweet;

public class TwitterConnector extends SocialConnector {

	public TwitterConnector(String fonte) {
		super(fonte);
	}

	public List<Post> findPosts(String searchString, String user, int limit, String dateQuerySince,
			String dateQueryUntil, boolean hasDate) {
		
		int limiteUtilizado = 10;
		if(limit>0){
			limiteUtilizado = limit; //Determina o limite (o padrão é 10)
		}
		
		TwitterCriteria criteria = null;
		
		if(!searchString.equals("") && user.equals("") && !hasDate){ //se a pesquisa for apenas por searchString
			criteria = TwitterCriteria.create()
	    			.setQuerySearch(searchString)
	    			.setMaxTweets(limiteUtilizado);
		} else if(searchString.equals("") && !user.equals("") && !hasDate){ //se for por usuário
			criteria = TwitterCriteria.create()
					.setUsername(user)
					.setMaxTweets(limiteUtilizado);
			
		} else if(searchString.equals("") && !user.equals("") && hasDate) { //por usuário com data
			criteria = TwitterCriteria.create()
	    			.setQuerySearch(searchString)
	    			.setUsername(user)
	    			.setSince(dateQuerySince)
	    			.setUntil(dateQueryUntil)
	    			.setMaxTweets(limiteUtilizado);
		} else if(!searchString.equals("") && user.equals("") && hasDate){ //por searchString com data
			criteria = TwitterCriteria.create()
	    			.setQuerySearch(searchString)
	    			.setSince(dateQuerySince)
	    			.setUntil(dateQueryUntil)
	    			.setMaxTweets(limiteUtilizado);
		} else if(!searchString.equals("") && !user.equals("") && !hasDate){ //por searchString e usuário
			criteria = TwitterCriteria.create() 
	    			.setQuerySearch(searchString)
	    			.setUsername(user)
	    			.setMaxTweets(limiteUtilizado);
		} else { //por searchString e usuário com data
			criteria = TwitterCriteria.create()
	    			.setQuerySearch(searchString)
	    			.setUsername(user)
	    			.setSince(dateQuerySince)
	    			.setUntil(dateQueryUntil)
	    			.setMaxTweets(limiteUtilizado);
		}
		
		List<Post> posts = new ArrayList<Post>(); //reinicia a lista
		
		 for(Tweet t: TweetManager.getTweets(criteria)){
			 Date data = t.getDate();
			 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			 String dataString = format.format(data);
			 Post newPost = new Post(t.getText(), t.getUsername(), t.getId(), t.getPermalink(), dataString);
			 posts.add(newPost);
		 }
		
		return posts;
	}

}
