package org.springframework.social.yahoo.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.yahoo.api.*;
import org.springframework.util.Assert;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Most of the code in YahooTemplate.class has been taken from TwitterTemplate.java
 * See Here: https://github.com/spring-projects/spring-social-twitter/blob/master/spring-social-twitter/src/main/java/org/springframework/social/twitter/api/impl/TwitterTemplate.java
 */

public class YahooTemplate extends AbstractOAuth1ApiBinding implements Yahoo {
    private RestTemplate clientRestTemplate = null;
    private TickerOperations tickerOperations;
    private ForexOperations forexOperations;
    private ProfileOperations profileDetailsOperations;

    public YahooTemplate(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        initSubApis();
    }

    public YahooTemplate(String clientToken) {
        super();
        Assert.notNull(clientToken, "Constructor argument 'clientToken' cannot be null.");
        this.clientRestTemplate = createClientRestTemplate(clientToken);
        initSubApis();
    }
    @Override
    public ProfileOperations profileDetailsOperations() {
        return profileDetailsOperations;
    }


    public YahooTemplate(String consumerKey, String consumerSecret) {
        this(exchangeCredentialsForClientToken(consumerKey, consumerSecret));
    }

    public RestOperations restOperations() {
        return getRestTemplate();
    }

    // Override getRestTemplate() to return an app-authorized RestTemplate if a client token is available.
    @Override
    public RestTemplate getRestTemplate() {
        if (clientRestTemplate != null) {
            return clientRestTemplate;
        }
        return super.getRestTemplate();
    }

    @Override
    public TickerOperations tickerOperations() {
        return tickerOperations;
    }

    @Override
    public ForexOperations forexOperations() {
        return forexOperations;
    }
// AbstractOAuth1ApiBinding hooks

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();

        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    //@Override
    //protected FormHttpMessageConverter getFormMessageConverter() {
      //  return new YahooEscapingFormHttpMessageConverter();
    //}

    @Override
    protected void configureRestTemplate(RestTemplate restTemplate) {
        restTemplate.setErrorHandler(new YahooErrorHandler());
    }

    // private helper
    private static String exchangeCredentialsForClientToken(String consumerKey, String consumerSecret) {
        OAuth2Template oauth2 = new OAuth2Template(consumerKey, consumerSecret, "", "https://api.login.yahoo.com/oauth/v2/get_token");
        return oauth2.authenticateClient().getAccessToken();
    }

    private RestTemplate createClientRestTemplate(String clientToken) {
        RestTemplate restTemplate = new ClientAuthorizedYahooTemplate(clientToken).getRestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        configureRestTemplate(restTemplate);
        return restTemplate;
    }

    private void initSubApis() {
        this.forexOperations = new ForexTemplate(getRestTemplate(), isAuthorized(), isAuthorizedForApp());
        this.tickerOperations = new TickerTemplate(getRestTemplate(), isAuthorized(), isAuthorizedForApp());
        this.profileDetailsOperations = new ProfileTemplate(getRestTemplate(), isAuthorized(), isAuthorizedForApp());
    }

    private boolean isAuthorizedForApp() {
        return clientRestTemplate != null;
    }
}
