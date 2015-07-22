package org.springframework.social.yahoo.security;

import org.springframework.social.security.provider.OAuth1AuthenticationService;
import org.springframework.social.yahoo.api.Yahoo;
import org.springframework.social.yahoo.connect.YahooConnectionFactory;

public class YahooAuthenticationService extends OAuth1AuthenticationService<Yahoo>  {

    public YahooAuthenticationService(String apiKey, String appSecret) {
        super(new YahooConnectionFactory(apiKey, appSecret));
    }
}