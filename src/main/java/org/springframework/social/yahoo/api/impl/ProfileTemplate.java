package org.springframework.social.yahoo.api.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.yahoo.profile.Profile;
import org.springframework.social.yahoo.api.ProfileOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class ProfileTemplate extends AbstractYahooOperations implements ProfileOperations {


    private final RestTemplate restTemplate;

    public ProfileTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp){
        super(isAuthorizedForUser, isAuthorizedForApp);
        this.restTemplate = restTemplate;
    }


    @Override
    public Profile profile() throws JsonParseException, JsonMappingException, IOException,UnsupportedEncodingException {
        requireUserAuthorization();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("format","json");
        String yqlQuery = UriUtils.encodePath("select * from social.profile where guid = me", "UTF-8");
        JsonNode node = restTemplate.postForObject(buildUri(yqlQuery),request, JsonNode.class);
        Profile profile = objectMapper().readValue(node.findValue("profile").toString(), Profile.class);
        System.out.print("Output: " + profile.toString());
        return profile;
    }
	private ObjectMapper objectMapper(){
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = messageConverter.getObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		return objectMapper;
	}

}
