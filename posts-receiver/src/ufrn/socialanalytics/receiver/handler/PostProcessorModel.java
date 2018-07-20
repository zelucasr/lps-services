package ufrn.socialanalytics.receiver.handler;

import ufrn.socialanalytics.receiver.model.Post;

public interface PostProcessorModel {
	public Post cleanSentence(Post post);
}
