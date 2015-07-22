package org.springframework.social.yahoo.api.impl;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class YahooObjectMixin {

    @JsonAnySetter
    abstract void add(String key, Object value);
}
