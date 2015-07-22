package org.springframework.social.yahoo.oauth1;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.YahooOAuth1Template;
import org.springframework.social.oauth1.OAuth1Template;
import org.springframework.social.oauth1.OAuth1Version;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.util.MultiValueMap;

public class CustomOAuth1TemplateTest {
	private static final String ACCESS_TOKEN_URL = "https://api.login.yahoo.com/oauth/v2/get_token";
	
	
	private static final String AUTHENTICATE_URL = "https://api.login.yahoo.com/oauth/v2/request_auth";

	private static final String AUTHORIZE_URL = "https://www.someprovider.com/oauth/authorize";

	private static final String REQUEST_TOKEN_URL = "https://api.login.yahoo.com/oauth/v2/get_request_token";
	
	private YahooOAuth1Template oauth10a;
	
//	private YahooOAuth1Template oauth10;
	
	//private YahooOAuth1Template customOauth10;
	
	@Before
	public void setUp() throws Exception {
		oauth10a = new YahooOAuth1Template("consumer_key", "consumer_secret",
				REQUEST_TOKEN_URL,
				AUTHENTICATE_URL,
				ACCESS_TOKEN_URL);
		
	//	oauth10 = new YahooOAuth1Template("consumer_key", "consumer_secret", REQUEST_TOKEN_URL, AUTHORIZE_URL, AUTHENTICATE_URL, ACCESS_TOKEN_URL, OAuth1Version.CORE_10);

	//	customOauth10 = new YahooOAuth1Template("consumer_key", "consumer_secret", REQUEST_TOKEN_URL, AUTHORIZE_URL, null, ACCESS_TOKEN_URL, OAuth1Version.CORE_10) {
		//	protected void addCustomAuthorizationParameters(MultiValueMap<String,String> parameters) {
			//	parameters.set("custom_parameter", "custom_parameter_value");
		//	};
		//};
	}

	@Test
	public void testExchangeForYahooAccessToken() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(oauth10a.getRestTemplate());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		mockServer
				.expect(requestTo(ACCESS_TOKEN_URL))
				.andExpect(method(POST))
				.andExpect(headerContains("Authorization", "oauth_version=\"1.0\""))
				.andExpect(headerContains("Authorization", "oauth_signature_method=\"HMAC-SHA1\""))
				.andExpect(headerContains("Authorization", "oauth_consumer_key=\"consumer_key\""))
				.andExpect(headerContains("Authorization", "oauth_token=\"1234567890\""))
				.andExpect(headerContains("Authorization", "oauth_verifier=\"verifier\""))
				.andExpect(headerContains("Authorization", "oauth_nonce=\""))
				.andExpect(headerContains("Authorization", "oauth_signature=\""))
				.andExpect(headerContains("Authorization", "oauth_timestamp=\""))
				.andRespond(withSuccess(new ClassPathResource("oauth_nonce=1835745213&oauth_callback=&oauth_consumer_key=consumer_key&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1429873536&oauth_version=1.0&oauth_signature=ONDsax%2FB7DYML9uxi2obmDUw0NU%3D", getClass()), MediaType.APPLICATION_FORM_URLENCODED));

		OAuthToken requestToken = new OAuthToken("1234567890", "abcdefghijklmnop");
		MultiValueMap<String, String> response = oauth10a.exchangeForYahooAccessToken(new AuthorizedRequestToken(requestToken, "verifier"), null);
		assertEquals("9876543210", response.getFirst("oauth_token"));
		assertEquals("ponmlkjihgfedcba", response.getFirst("oauth_token_secret"));
	}
	

	@Test
	public void testFetchYahooRefreshAccessToken() {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(oauth10a.getRestTemplate());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		mockServer
				.expect(requestTo(ACCESS_TOKEN_URL))
				.andExpect(method(POST))
				.andExpect(headerContains("Authorization", "oauth_signature_method=\"HMAC-SHA1\""))
				.andExpect(headerContains("Authorization", "oauth_version=\"1.0\""))
				.andExpect(headerContains("Authorization", "oauth_consumer_key=\"consumer_key\""))
				.andExpect(headerContains("Authorization", "oauth_token=\"1234567890\""))
				.andExpect(headerContains("Authorization", "oauth_nonce=\""))
				.andExpect(headerContains("Authorization", "oauth_signature=\""))
				.andExpect(headerContains("Authorization", "oauth_timestamp=\""))
				.andExpect(headerContains("Authorization", "oauth_session_handle=\""))
				.andRespond(withSuccess(new ClassPathResource("accessToken.formencoded", getClass()), MediaType.APPLICATION_FORM_URLENCODED));

		MultiValueMap<String, String> additionalParameters = new HttpHeaders();
		additionalParameters.add("oauth_consumer_key", "123456891011121314151617181920");
		additionalParameters.add("oauth_signature_method", "HMAC-SHA1");
		additionalParameters.add("oauth_token", "1234567890");
		additionalParameters.add("oauth_timestamp","919314350");
		additionalParameters.add("oauth_nonce", "ef3a091928d5491624c0ac54d697124422705091");
		additionalParameters.add("oauth_session_handle", "ALKVBsl8DHR1rsAHSwTmAxYIsIGs3l31syRaA_aaF.RDs.MknmVM4P");
		additionalParameters.add("oauth_version", "1.0");
		
		//OAuthToken requestToken = new OAuthToken("1234567890", "abcdefghijklmnop");
		MultiValueMap<String, String> response = oauth10a.fetchYahooRefreshAccessToken("", additionalParameters,"abcdefghijklmnop","1234567890","abcdefgh" );
	
		assertEquals("9876543210", response.getFirst("oauth_token"));
		assertEquals("ponmlkjihgfedcba", response.getFirst("oauth_token_secret"));
	}


	@SuppressWarnings("unchecked")
	private RequestMatcher headerContains(String name, String substring) {
		return header(name, containsString(substring));
	}
}
