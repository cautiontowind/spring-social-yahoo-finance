package org.springframework.social.yahoo.api.impl;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.*;
import org.springframework.social.yahoo.connect.support.YahooOAuth1Connection;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Most of the code in YahooErrorHandler.class has been taken from TwitterErrorHandler.java
 * See here: https://github.com/spring-projects/spring-social-twitter/blob/master/spring-social-twitter/src/main/java/org/springframework/social/twitter/api/impl/TwitterErrorHandler.java
 *
 */
public class YahooErrorHandler extends DefaultResponseErrorHandler {

    private static final int TOO_MANY_REQUESTS = 429;
    private static final int ENHANCE_YOUR_CALM = 420;
    Logger logger = LoggerFactory.getLogger(YahooErrorHandler.class);
    
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.series() == HttpStatus.Series.SERVER_ERROR) {
            handleServerErrors(statusCode);
        } else if (statusCode.series() == HttpStatus.Series.CLIENT_ERROR) {
            handleClientErrors(response);
        }

        // if not otherwise handled, do default handling and wrap with UncategorizedApiException
        try {
            super.handleError(response);
        } catch(Exception e) {
            throw new UncategorizedApiException("yahoo", "Error consuming Yahoo REST API", e);
        }
    }

    private void handleClientErrors(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();
        Map<String, Object> errorMap = extractErrorDetailsFromResponse(response);

        String errorText = "";
        if (errorMap.containsKey("error")) {
            errorText =  errorMap.get("error").toString();
        } else if(errorMap.containsKey("errors")) {
            Object errors = errorMap.get("errors");
            if (errors instanceof List) {
                @SuppressWarnings("unchecked")
                List<Map<String, String>> errorsList = (List<Map<String, String>>) errors;
                errorText = errorsList.get(0).get("message");
            } else if (errors instanceof String ) {
                errorText = (String) errors;
            }
        }

        if (statusCode == HttpStatus.BAD_REQUEST) {
            if (errorText.contains("Rate limit exceeded.")) {
                throw new RateLimitExceededException("yahoo");
            }
        } else if (statusCode == HttpStatus.UNAUTHORIZED) {
            if (errorText == null) {
                throw new NotAuthorizedException("yahoo", response.getStatusText());
            } else if (errorText.equals("Could not authenticate you.")) {
                throw new MissingAuthorizationException("yahoo");
            } else if (errorText.equals("Could not authenticate with OAuth.")) { // revoked token
                throw new RevokedAuthorizationException("yahoo");
            } else if (errorText.equals("Invalid / expired Token")) {
                throw new InvalidAuthorizationException("yahoo", errorText);
            } else {
                throw new NotAuthorizedException("yahoo", errorText);
            }
        } else if (statusCode == HttpStatus.FORBIDDEN) {
            throw new OperationNotPermittedException("yahoo", errorText);
        } else if (statusCode == HttpStatus.NOT_FOUND) {
            throw new ResourceNotFoundException("yahoo", errorText);
        } else if (statusCode == HttpStatus.valueOf(ENHANCE_YOUR_CALM) || statusCode == HttpStatus.valueOf(TOO_MANY_REQUESTS)) {
            throw new RateLimitExceededException("yahoo");
        }

    }

    private void handleServerErrors(HttpStatus statusCode) throws IOException {
        if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new InternalServerErrorException("yahoo", "Something is broken at Yahoo. Please see https://developer.yahoo.com/oauth/guide/ to report the issue.");
        } else if (statusCode == HttpStatus.BAD_GATEWAY) {
            throw new ServerDownException("yahoo", "Yahoo is down or is being upgraded.");
        } else if (statusCode == HttpStatus.SERVICE_UNAVAILABLE) {
            throw new ServerOverloadedException("yahoo", "Yahoo is overloaded with requests. Try again later.");
        }
    }

    private Map<String, Object> extractErrorDetailsFromResponse(ClientHttpResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.<Map<String, Object>>readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
        } catch (JsonParseException e) {
            return Collections.emptyMap();
        }
    }


}
