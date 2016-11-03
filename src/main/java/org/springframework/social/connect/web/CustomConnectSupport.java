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
package org.springframework.social.connect.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.OAuth1ConnectionFactory;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth1.*;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.yahoo.connect.YahooConnectionFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map.Entry;

import static java.util.Arrays.asList;

public class CustomConnectSupport extends ConnectSupport {
	
	private final static Logger logger = LoggerFactory.getLogger(CustomConnectSupport.class);

	private boolean useAuthenticateUrl;

	private String applicationUrl;

	private String callbackUrl;
	
	private SessionStrategy sessionStrategy;
	
	public CustomConnectSupport() {
		this(new HttpSessionSessionStrategy());
	}
	
	public CustomConnectSupport(SessionStrategy sessionStrategy) {
		super(sessionStrategy);
		this.sessionStrategy = sessionStrategy;
	}
	
	public String buildOAuthUrl(ConnectionFactory<?> connectionFactory, NativeWebRequest request) {
		return buildOAuthUrl(connectionFactory, request, null);
	}
	
	public String buildOAuthUrl(ConnectionFactory<?> connectionFactory, NativeWebRequest request, MultiValueMap<String, String> additionalParameters) {
		
		if (connectionFactory instanceof OAuth1ConnectionFactory) {
			return buildOAuth1Url((OAuth1ConnectionFactory<?>) connectionFactory, request, additionalParameters);
		} else if (connectionFactory instanceof OAuth2ConnectionFactory) {
			return buildOAuth2Url((OAuth2ConnectionFactory<?>) connectionFactory, request, additionalParameters);
		} else {
			throw new IllegalArgumentException("ConnectionFactory not supported");
		}		
	}
	
	public Connection<?> completeConnection(OAuth1ConnectionFactory<?> connectionFactory, NativeWebRequest request) {
		String verifier = request.getParameter("oauth_verifier"); 
		AuthorizedRequestToken requestToken = new AuthorizedRequestToken(extractCachedRequestToken(request), verifier);
		
		if(connectionFactory instanceof YahooConnectionFactory){
			CustomOAuth1Template customOAuth1Template = (CustomOAuth1Template) connectionFactory.getOAuthOperations();
			
			MultiValueMap<String, String> response = customOAuth1Template.exchangeForYahooAccessToken(requestToken, null);
			YahooConnectionFactory yahooConnectionFactory = (YahooConnectionFactory) connectionFactory;
			
			String accessTokenValue = response.getFirst("oauth_token");
			String accessTokenSecret = response.getFirst("oauth_token_secret");
			String refreshToken = response.getFirst("oauth_session_handle");
			Long expireTime = new Long(response.getFirst("oauth_authorization_expires_in"));
			
			return yahooConnectionFactory.createConnection(null, accessTokenValue, accessTokenSecret, refreshToken, expireTime);
		}
		
		OAuthToken accessToken = connectionFactory.getOAuthOperations().exchangeForAccessToken(requestToken, null);
		return connectionFactory.createConnection(accessToken);
	}

	private OAuthToken extractCachedRequestToken(WebRequest request) {
		OAuthToken requestToken = (OAuthToken) sessionStrategy.getAttribute(request, OAUTH_TOKEN_ATTRIBUTE);
		sessionStrategy.removeAttribute(request, OAUTH_TOKEN_ATTRIBUTE);
		return requestToken;
	}
	
	private String buildOAuth2Url(OAuth2ConnectionFactory<?> connectionFactory, NativeWebRequest request, MultiValueMap<String, String> additionalParameters) {
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		String defaultScope = connectionFactory.getScope();
		OAuth2Parameters parameters = getOAuth2Parameters(request, defaultScope, additionalParameters);
		String state = connectionFactory.generateState();
		parameters.add("state", state);
		sessionStrategy.setAttribute(request, OAUTH2_STATE_ATTRIBUTE, state);
		if (useAuthenticateUrl) { 
			return oauthOperations.buildAuthenticateUrl(parameters);
		} else {
			return oauthOperations.buildAuthorizeUrl(parameters);
		}
	}

	private OAuth2Parameters getOAuth2Parameters(NativeWebRequest request, String defaultScope, MultiValueMap<String, String> additionalParameters) {
		OAuth2Parameters parameters = new OAuth2Parameters(additionalParameters);
		parameters.putAll(getRequestParameters(request, "scope"));
		parameters.setRedirectUri(callbackUrl(request));
		String scope = request.getParameter("scope");
		if (scope != null) {
			parameters.setScope(scope);
		} else if (defaultScope != null) {
			parameters.setScope(defaultScope);
		}
		return parameters;
	}

	protected String callbackUrl(NativeWebRequest request) {
		if (callbackUrl != null) {
			return callbackUrl;
		}
		HttpServletRequest nativeRequest = request.getNativeRequest(HttpServletRequest.class);
		if (applicationUrl != null) {
			return applicationUrl + connectPath(nativeRequest);
		} else {
			return nativeRequest.getRequestURL().toString();
		}
	}

	// internal helpers
	
	private String buildOAuth1Url(OAuth1ConnectionFactory<?> connectionFactory, NativeWebRequest request, MultiValueMap<String, String> additionalParameters) {
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		MultiValueMap<String, String> requestParameters = getRequestParameters(request);
		OAuth1Parameters parameters = getOAuth1Parameters(request, additionalParameters);
		parameters.putAll(requestParameters);
		if (oauthOperations.getVersion() == OAuth1Version.CORE_10) {
			parameters.setCallbackUrl(callbackUrl(request));
		}
		OAuthToken requestToken = fetchRequestToken(request, requestParameters, oauthOperations);
		sessionStrategy.setAttribute(request, OAUTH_TOKEN_ATTRIBUTE, requestToken);
		return buildOAuth1Url(oauthOperations, requestToken.getValue(), parameters);
	}

	private OAuth1Parameters getOAuth1Parameters(NativeWebRequest request, MultiValueMap<String, String> additionalParameters) {
		OAuth1Parameters parameters = new OAuth1Parameters(additionalParameters);
		parameters.putAll(getRequestParameters(request));
		return parameters;
	}

	private OAuthToken fetchRequestToken(NativeWebRequest request, MultiValueMap<String, String> requestParameters, OAuth1Operations oauthOperations) {
		if (oauthOperations.getVersion() == OAuth1Version.CORE_10_REVISION_A) {
			return oauthOperations.fetchRequestToken(callbackUrl(request), requestParameters);
		}
		return oauthOperations.fetchRequestToken(null, requestParameters);				
	}

	private String connectPath(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();
		return request.getServletPath() + (pathInfo != null ? pathInfo : "");
	}

	private String buildOAuth1Url(OAuth1Operations oauthOperations, String requestToken, OAuth1Parameters parameters) {
		if (useAuthenticateUrl) {
			return oauthOperations.buildAuthenticateUrl(requestToken, parameters);			
		} else {
			return oauthOperations.buildAuthorizeUrl(requestToken, parameters);
		}
	}

	private MultiValueMap<String, String> getRequestParameters(NativeWebRequest request, String... ignoredParameters) {
		List<String> ignoredParameterList = asList(ignoredParameters);
		MultiValueMap<String, String> convertedMap = new LinkedMultiValueMap<String, String>();
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			if (!ignoredParameterList.contains(entry.getKey())) {
				convertedMap.put(entry.getKey(), asList(entry.getValue()));
			}
		}
		return convertedMap;
	}

	private static final String OAUTH_TOKEN_ATTRIBUTE = "oauthToken";
	
	private static final String OAUTH2_STATE_ATTRIBUTE = "oauth2State";

}
