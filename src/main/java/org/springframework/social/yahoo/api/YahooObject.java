package org.springframework.social.yahoo.api;

import java.util.Map;

/**
 *  Offers an extraData property for carrying any data in response from Twitter that won't be otherwise mapped to any properties of the subclass.
 */
public class YahooObject {

    private Map<String, Object> extraData;

    protected void add(String key, Object value) {
        extraData.put(key, value);
    }
}

