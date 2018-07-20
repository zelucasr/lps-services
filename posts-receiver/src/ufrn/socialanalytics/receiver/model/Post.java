package ufrn.socialanalytics.receiver.model;

import java.util.Date;

public class Post {

	private String text;
	private String source;
	private Date date;
	private String user;
	private GeoLocation location;
	private String processedText;
	
	public Post(String text, String source, Date date, String user, GeoLocation location, String processedText) {
		super();
		this.text = text;
		this.source = source;
		this.date = date;
		this.user = user;
		this.location = location;
		this.processedText = processedText;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

}
