package org.springframework.social.yahoo.config.xml;

import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.security.provider.SocialAuthenticationService;
import org.springframework.social.yahoo.config.support.YahooApiHelper;
import org.springframework.social.yahoo.connect.YahooConnectionFactory;
import org.springframework.social.yahoo.security.YahooAuthenticationService;

public class YahooConfigBeanDefinitionParser extends AbstractProviderConfigBeanDefinitionParser {

    public YahooConfigBeanDefinitionParser() {
        super(YahooConnectionFactory.class, YahooApiHelper.class);
    }

    @Override
    protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
        return YahooAuthenticationService.class;
    }
}
