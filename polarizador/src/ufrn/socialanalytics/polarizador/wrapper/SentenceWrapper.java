package ufrn.socialanalytics.polarizador.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SentenceWrapper {
	
	List<String> sentences;

	public List<String> getSentences() {
		return sentences;
	}

	public void setSentences(List<String> sentences) {
		this.sentences = sentences;
	}
	
}
