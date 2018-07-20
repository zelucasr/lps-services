package stream.twitter.util;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamBuilderUtil {

	public static TwitterStream getStream() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(TwitterConstants.CONSUMERKEY);
		cb.setOAuthConsumerSecret(TwitterConstants.CONSUMERSECRET);
		cb.setOAuthAccessToken(TwitterConstants.ACCESSTOKEN);
		cb.setOAuthAccessTokenSecret(TwitterConstants.ACCESSTOKENSECRET);

		return new TwitterStreamFactory(cb.build()).getInstance();
	}

}
