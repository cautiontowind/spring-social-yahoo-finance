package org.springframework.social.oauth1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CustomSigningSupport extends SigningSupport {

	public Map<String, String> yahooOAuthParameters(String consumerKey, String accessToken, String refreshToken) {
		TimestampGenerator timestampGenerator = new DefaultTimestampGenerator();
		Map<String, String> oauthParameters = new HashMap<String, String>();// HashMultiValueMap<String, String>();
		oauthParameters.put("oauth_token", accessToken);
		oauthParameters.put("oauth_consumer_key", consumerKey);
		oauthParameters.put("oauth_signature_method", HMAC_SHA1_SIGNATURE_NAME);
		long timestamp = timestampGenerator.generateTimestamp();
		oauthParameters.put("oauth_timestamp", Long.toString(timestamp));
		oauthParameters.put("oauth_nonce", Long.toString(timestampGenerator.generateNonce(timestamp)));
		oauthParameters.put("oauth_version", "1.0");
		oauthParameters.put("oauth_session_handle", refreshToken);
		return oauthParameters;
	}

	private static class DefaultTimestampGenerator implements TimestampGenerator {

		public long generateTimestamp() {
			return System.currentTimeMillis() / 1000;
		}
		
		public long generateNonce(long timestamp) {
			return timestamp + RANDOM.nextInt();		
		}
		
		static final Random RANDOM = new Random();
		
	}
	
	private static final String HMAC_SHA1_SIGNATURE_NAME = "HMAC-SHA1";
	//private static final String UTF8_CHARSET_NAME = "UTF-8";
	//private static final String HMAC_SHA1_MAC_NAME = "HmacSHA1";
	
}
