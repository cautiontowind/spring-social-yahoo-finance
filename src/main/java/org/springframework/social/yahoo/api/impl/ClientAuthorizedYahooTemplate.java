package org.springframework.social.yahoo.api.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

public class ClientAuthorizedYahooTemplate extends AbstractOAuth2ApiBinding {

    public ClientAuthorizedYahooTemplate(String clientToken) {
        super(clientToken);
    }

}