package org.springframework.social.yahoo.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth1ConnectionFactory;
import org.springframework.social.oauth1.OAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.yahoo.api.Yahoo;
import org.springframework.social.yahoo.connect.support.YahooOAuth1Connection;

public class YahooConnectionFactory extends OAuth1ConnectionFactory<Yahoo> {
	private final String consumerKey;
    public YahooConnectionFactory(String consumerKey, String consumerSecret) {
        super("yahoo", new YahooServiceProvider(consumerKey, consumerSecret), new YahooAdapter());
        this.consumerKey = consumerKey;
    }
    
    @Override
	public Connection<Yahoo> createConnection(OAuthToken accessToken) {
		String providerUserId = extractProviderUserId(accessToken);
		return new YahooOAuth1Connection<Yahoo>(consumerKey,getProviderId(), providerUserId, accessToken.getValue(), accessToken.getSecret(),null,null, getOAuth1ServiceProvider(), getApiAdapter());		
	}
    
	@Override
	public Connection<Yahoo> createConnection(ConnectionData data) {
		return new YahooOAuth1Connection<Yahoo>(consumerKey,data, getOAuth1ServiceProvider(), getApiAdapter());
	}
	
	public Connection<Yahoo> createConnection(String providerUserId, String accessTokenValue, String accessTokenSecret, String refreshToken, Long expireToken) {
		return new YahooOAuth1Connection<Yahoo>(consumerKey,getProviderId(), providerUserId, accessTokenValue, accessTokenSecret, refreshToken, expireToken, getOAuth1ServiceProvider(), getApiAdapter());		
	}
    
	
	// internal helpers
	
	private OAuth1ServiceProvider<Yahoo> getOAuth1ServiceProvider() {
		return (OAuth1ServiceProvider<Yahoo>) getServiceProvider();
	}
}
