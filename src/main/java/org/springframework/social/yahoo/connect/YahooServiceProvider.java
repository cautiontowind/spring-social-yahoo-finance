package org.springframework.social.yahoo.connect;

import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.oauth1.CustomOAuth1Template;
import org.springframework.social.yahoo.api.Yahoo;
import org.springframework.social.yahoo.api.impl.YahooTemplate;

public final class YahooServiceProvider extends AbstractOAuth1ServiceProvider<Yahoo> {
    public YahooServiceProvider(String consumerKey, String consumerSecret) {

        super(consumerKey, consumerSecret,new CustomOAuth1Template(consumerKey, consumerSecret,
                "https://api.login.yahoo.com/oauth/v2/get_request_token",
                "https://api.login.yahoo.com/oauth/v2/request_auth",
//                "https://api.login.yahoo.com/oauth/v2/request_auth",
                "https://api.login.yahoo.com/oauth/v2/get_token"));

    }

    @Override
    public Yahoo getApi(String accessToken, String secret) {
        return new YahooTemplate(getConsumerKey(), getConsumerSecret(), accessToken, secret);
    }

}