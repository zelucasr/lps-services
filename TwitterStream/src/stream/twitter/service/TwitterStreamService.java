package stream.twitter.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import stream.twitter.model.Post;
import stream.twitter.util.TwitterStreamBuilderUtil;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;

public class TwitterStreamService {
	
	private String postServiceUrl;

	public void startStream() throws IOException {
		TwitterStream stream = TwitterStreamBuilderUtil.getStream();

		StatusListener listener = new StatusListener() {

			@Override
			public void onException(Exception e) {
				System.out.println("Exception occured:" + e.getMessage());
				e.printStackTrace();
			}

			@Override
			public void onTrackLimitationNotice(int n) {
				System.out.println("Track limitation notice for " + n);
			}

			@Override
			public void onStatus(Status status) {
				String username = status.getUser().getScreenName();
				long tweetId = status.getId();

				String content = status.getText();
				System.out.println("Usuário: " + username);
				System.out.println("Tweet Id: " + tweetId);
				System.out.println("Tweet: " + content);
				System.out.println("------------");
				/*
				 * Enviar aqui para o serviço de recebimento de posts
				 * utilizando json, atráves de uma requisição POST.
				 */
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				System.out.println("Stall warning");
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				System.out.println("Scrub geo with:" + arg0 + ":" + arg1);
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				System.out.println("Status deletion notice");
			}
		};
		
		//Configurações de busca
		
		FilterQuery qry = mountQry(); //captura as configurações do txt
		
		//Fim das configurações

		stream.addListener(listener);
		stream.filter(qry);
	}
	
	@SuppressWarnings("resource")
	public FilterQuery mountQry() throws IOException{
		File file = new File("config.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		ArrayList<String> keys = new ArrayList<String>(); //keywords
		ArrayList<String> languages = new ArrayList<String>(); //languages
		int count = -1;
		
		while ((st = br.readLine()) != null){
			String[] parts = st.split(" ");
			for(String p: parts){
				if(p.equals("k")){ //é uma keyword
					keys.add(parts[1]);
				}
				if(p.equals("l")){ //language
					languages.add(parts[1]);
				}
				if(p.equals("c")){ //count
					count = Integer.parseInt(parts[1]);
				}
				if(p.equals("u")){ //url do post service
					this.setPostServiceUrl(parts[1]);
				}
			}
		}
		
		FilterQuery qry = new FilterQuery();
		
		if(!keys.isEmpty()){
			String[] keywords = new String[keys.size()];
			keywords = keys.toArray(keywords);
			qry.track(keywords);
		}
		
		if(!languages.isEmpty()){
			String[] languagesArray = new String[languages.size()];
			languagesArray = languages.toArray(languagesArray);
			qry.language(languagesArray);
		}
		
		if(count != -1){
			qry.count(-1);
		}
		
		return qry;
	}
	
	public void sendPost(Post post){
		//...
	}

	public String getPostServiceUrl() {
		return postServiceUrl;
	}

	public void setPostServiceUrl(String postServiceUrl) {
		this.postServiceUrl = postServiceUrl;
	}

}
