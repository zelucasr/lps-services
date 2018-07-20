package com.ufrn.social.model;


public class Post {
	
	private String id;
	private String text;
	private String source;
	private String date;
	private String user;
	private GeoLocation location;
	private String processedText;
	private String link;
	private String sentiment;
	
	public Post(String text, String source, String date, String user, GeoLocation location, String processedText) {
		super();
		this.text = text;
		this.source = source;
		this.setDate(date);
		this.user = user;
		this.location = location;
		this.processedText = processedText;
	}
	
	public Post(String text, String user, String id, String link, String date){
		this.text = text;
		this.user = user;
		this.id = id;
		this.link = link;
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public GeoLocation getLocation() {
		return location;
	}

	public void setLocation(GeoLocation location) {
		this.location = location;
	}

	public String getProcessedText() {
		return processedText;
	}

	public void setProcessedText(String processedText) {
		this.processedText = processedText;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}