package org.springframework.social.yahoo.api.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.yahoo.api.ForexOperations;
import org.springframework.social.yahoo.ticker.ForexName;
import org.springframework.social.yahoo.ticker.Ticker;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khaliluddin on 13/07/2015.
 */
public class ForexTemplate extends AbstractYahooOperations implements ForexOperations {
    private final RestTemplate restTemplate;
    public ForexTemplate(RestTemplate restTemplate, boolean isUserAuthorized, boolean isAppAuthorized) {
        super(isUserAuthorized, isAppAuthorized);
        this.restTemplate = restTemplate;
    }


    @Override
    public Ticker ticker(ForexName forexName) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException {
        requireUserAuthorization();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("env",UriUtils.encodePath("http://datatables.org/alltables.env","UTF-8"));

        request.set("format","json");
        String yqlQuery = UriUtils.encodePath("select * from yahoo.finance.quotes where symbol in (" + forexName.getSymbol() + ")", "UTF-8");

        JsonNode node = restTemplate.postForObject(buildUri(yqlQuery), request, JsonNode.class);


        JsonNode node1 = node.path("query").path("results").path("quote");

        Ticker aTicker = objectMapper().readValue(node1.toString(), Ticker.class);
        aTicker.setDate(node.findValue("created").asText());
        return aTicker;
    }

    @Override
    public List<Ticker> tickers(ForexName[] forexNames) throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException {
        requireUserAuthorization();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("env",UriUtils.encodePath("http://datatables.org/alltables.env","UTF-8"));
        request.set("format", "json");

        List<String> merge = new ArrayList<String>();

        for (ForexName symbol : forexNames) {
            merge.add(symbol.getSymbol());
        }

        String allSymbols = String.join(",", merge);

        String yqlQuery = UriUtils.encodePath("select * from yahoo.finance.quotes where symbol in (" + allSymbols + ")", "UTF-8");
        JsonNode node = restTemplate.postForObject(buildUri(yqlQuery), request, JsonNode.class);

        JsonNode node1 = node.path("query").path("results").path("quote");
        List<Ticker> tickers = new ArrayList<Ticker>();
        String date = node.findValue("created").asText();
        if (node1.isArray()) {
            for (final JsonNode objNode : node1) {
                Ticker aTicker = objectMapper().readValue(objNode.toString(), Ticker.class);
                aTicker.setDate(date);
                tickers.add(aTicker);

            }
            return tickers;
        }
        Ticker aTicker = objectMapper().readValue(node1.toString(), Ticker.class);
        aTicker.setDate(date);
        tickers.add(aTicker);
        return tickers;
    }

    private ObjectMapper objectMapper(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = messageConverter.getObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper;
    }
}
