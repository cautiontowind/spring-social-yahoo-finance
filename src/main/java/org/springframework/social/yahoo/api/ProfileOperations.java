package org.springframework.social.yahoo.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.social.yahoo.profile.Profile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public interface ProfileOperations {
    public Profile profile()  throws JsonParseException, JsonMappingException, IOException,UnsupportedEncodingException;
}
