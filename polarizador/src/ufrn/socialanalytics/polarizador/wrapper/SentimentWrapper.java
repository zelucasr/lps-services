package ufrn.socialanalytics.polarizador.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SentimentWrapper {
	
	List<Integer> sentiments;

	public List<Integer> getSentiments() {
		return sentiments;
	}

	public void setSentiments(List<Integer> sentiments) {
		this.sentiments = sentiments;
	}

}
