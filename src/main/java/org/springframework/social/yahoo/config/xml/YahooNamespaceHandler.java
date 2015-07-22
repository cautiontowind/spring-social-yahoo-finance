package org.springframework.social.yahoo.config.xml;

import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.config.xml.AbstractProviderConfigNamespaceHandler;


public class YahooNamespaceHandler extends AbstractProviderConfigNamespaceHandler {

    @Override
    protected AbstractProviderConfigBeanDefinitionParser getProviderConfigBeanDefinitionParser() {
        return new YahooConfigBeanDefinitionParser();
    }
}
