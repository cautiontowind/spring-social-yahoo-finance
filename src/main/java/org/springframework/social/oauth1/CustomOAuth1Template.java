/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.oauth1;

import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomOAuth1Template extends OAuth1Template {

	private final URI requestTokenUrl;
	
	private final String consumerKey;
	
	private final String consumerSecret;

	private final URI accessTokenUrl;

	private final RestTemplate restTemplate;

	private final OAuth1Version version;
	
	private final CustomSigningSupport signingUtils;

	public CustomOAuth1Template(String consumerKey, String consumerSecret, String requestTokenUrl, String authorizeUrl, String accessTokenUrl) {
		this(consumerKey, consumerSecret, requestTokenUrl, authorizeUrl, accessTokenUrl, OAuth1Version.CORE_10_REVISION_A);
	}

	public CustomOAuth1Template(String consumerKey, String consumerSecret, String requestTokenUrl, String authorizeUrl, String accessTokenUrl, OAuth1Version version) {
		this(consumerKey, consumerSecret, requestTokenUrl, authorizeUrl, null, accessTokenUrl, version);
	}

	public CustomOAuth1Template(String consumerKey, String consumerSecret, String requestTokenUrl, String authorizeUrl, String authenticateUrl, String accessTokenUrl) {
		this(consumerKey, consumerSecret, requestTokenUrl, authorizeUrl, authenticateUrl, accessTokenUrl, OAuth1Version.CORE_10_REVISION_A);
	}
	
	public CustomOAuth1Template(String consumerKey, String consumerSecret, String requestTokenUrl, String authorizeUrl, String authenticateUrl, String accessTokenUrl, OAuth1Version version) {
		super(consumerKey, consumerSecret, requestTokenUrl, authorizeUrl, authenticateUrl, accessTokenUrl, version);
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.requestTokenUrl = encodeTokenUri(requestTokenUrl);
		this.accessTokenUrl = encodeTokenUri(accessTokenUrl);
		this.version = version;
		this.restTemplate = createRestTemplate();
		this.signingUtils = new CustomSigningSupport();
	}

	public MultiValueMap<String, String> exchangeForYahooAccessToken(AuthorizedRequestToken requestToken, MultiValueMap<String, String> additionalParameters) {
		Map<String, String> tokenParameters = new HashMap<String, String>(2, 1);
		tokenParameters.put("oauth_token", requestToken.getValue());
		if (version == OAuth1Version.CORE_10_REVISION_A) {
			tokenParameters.put("oauth_verifier", requestToken.getVerifier());
		}
		return exchangeForToken(accessTokenUrl, tokenParameters, additionalParameters, requestToken.getSecret());
	}
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private MultiValueMap<String, String> exchangeForToken(URI tokenUrl, Map<String, String> tokenParameters, MultiValueMap<String, String> additionalParameters, String tokenSecret) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", buildAuthorizationHeaderValue(tokenUrl, tokenParameters, additionalParameters, tokenSecret));
		ResponseEntity<MultiValueMap> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, new HttpEntity<MultiValueMap<String, String>>(additionalParameters, headers), MultiValueMap.class);		
		MultiValueMap<String, String> body = response.getBody();
		return body;
	}
	
	
	
	public MultiValueMap<String, String> fetchYahooRefreshAccessToken(String callbackUrl, MultiValueMap<String, String> additionalParameters, String tokenSecret,  String oldAccessToken, String refreshToken) {
		Map<String, String> oauthParameters = new HashMap<String, String>(1, 1);
		if (version == OAuth1Version.CORE_10_REVISION_A) {
			oauthParameters.put("oauth_callback", callbackUrl);
		}
		return refreshAccessToken(accessTokenUrl, oauthParameters, additionalParameters, tokenSecret, oldAccessToken, refreshToken);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private MultiValueMap<String, String> refreshAccessToken(URI tokenUrl, Map<String, String> tokenParameters, MultiValueMap<String, String> additionalParameters, String tokenSecret, String oldAccessToken, String refreshToken) {
		
		HttpHeaders headers = new HttpHeaders();		
		headers.add("Authorization", buildRefreshAuthorizationHeaderValue(tokenUrl, tokenParameters, additionalParameters, tokenSecret, oldAccessToken, refreshToken));
		ResponseEntity<MultiValueMap> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, new HttpEntity<MultiValueMap<String, String>>(additionalParameters, headers), MultiValueMap.class);		
		MultiValueMap<String, String> body = response.getBody();
		//return createOAuthToken(body.getFirst("oauth_token"), body.getFirst("oauth_token_secret"), body);
		return body;
	}
	

	private String buildRefreshAuthorizationHeaderValue(URI tokenUrl, Map<String, String> tokenParameters, MultiValueMap<String, String> additionalParameters,String tokenSecret, String accessToken, String refreshToken) {
		
		Map<String, String> oauthParameters = signingUtils.yahooOAuthParameters(consumerKey, accessToken, refreshToken);
		oauthParameters.putAll(tokenParameters);
		if (additionalParameters == null) {
			additionalParameters = EmptyMultiValueMap.instance();
		}
		
		//return signingUtils.buildRefreshAuthorizationHeaderValue(HttpMethod.POST, tokenUrl, oauthParameters, additionalParameters, consumerSecret,  tokenSecret);
		return signingUtils.buildAuthorizationHeaderValue(HttpMethod.POST, tokenUrl, oauthParameters, additionalParameters, consumerSecret,  tokenSecret);
	}
	
	
	
	
	
	private String buildAuthorizationHeaderValue(URI tokenUrl, Map<String, String> tokenParameters, MultiValueMap<String, String> additionalParameters, String tokenSecret) {
		Map<String, String> oauthParameters = signingUtils.commonOAuthParameters(consumerKey);
		oauthParameters.putAll(tokenParameters);
		if (additionalParameters == null) {
			additionalParameters = EmptyMultiValueMap.instance();
		}
		return signingUtils.buildAuthorizationHeaderValue(HttpMethod.POST, tokenUrl, oauthParameters, additionalParameters, consumerSecret, tokenSecret);
	}
	// internal helpers

	private RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(ClientHttpRequestFactorySelector.getRequestFactory());
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>(1);
		converters.add(new FormHttpMessageConverter() {
			public boolean canRead(Class<?> clazz, MediaType mediaType) {
				// always read MultiValueMaps as x-www-url-formencoded even if contentType not set properly by provider				
				return MultiValueMap.class.isAssignableFrom(clazz);
			}
		});
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}
	
	private URI encodeTokenUri(String url) {
		return UriComponentsBuilder.fromUriString(url).build().toUri();
	}
	// testing hooks
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}



}
