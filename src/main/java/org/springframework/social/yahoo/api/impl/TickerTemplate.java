package org.springframework.social.yahoo.api.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.yahoo.api.TickerOperations;
import org.springframework.social.yahoo.ticker.Ticker;
import org.springframework.social.yahoo.ticker.TickerName;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TickerTemplate extends AbstractYahooOperations implements TickerOperations {

    private Logger logger = LoggerFactory.getLogger(TickerTemplate.class);
    private final RestTemplate restTemplate;

    public TickerTemplate(RestTemplate restTemplate, boolean isAuthorizedForUser, boolean isAuthorizedForApp){
        super(isAuthorizedForUser, isAuthorizedForApp);
        this.restTemplate = restTemplate;
    }


    @Override
    public Ticker ticker(TickerName tickerName) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException {
        requireUserAuthorization();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("env",UriUtils.encodePath("http://datatables.org/alltables.env","UTF-8"));

        request.set("format","json");
        String yqlQuery = UriUtils.encodePath("select * from yahoo.finance.quotes where symbol in (\"" + tickerName.getSymbol() + "\")", "UTF-8");

        JsonNode node = restTemplate.postForObject(buildUri(yqlQuery), request, JsonNode.class);


        JsonNode node1 = node.path("query").path("results").path("quote");

        Ticker aTicker = objectMapper().readValue(node1.toString(), Ticker.class);
        aTicker.setDate(node.findValue("created").asText());
       return aTicker;
    }

    @Override
    public List<Ticker> tickers(TickerName[] tickerNames) throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException {
        requireUserAuthorization();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("env",UriUtils.encodePath("http://datatables.org/alltables.env","UTF-8"));
        request.set("format", "json");

        List<String> merge = new ArrayList<String>();

        for (TickerName symbol : tickerNames) {
            merge.add(symbol.getSymbol());
        }
        //Add quotes to each element and join
        String allSymbols = merge.stream().map((sym) -> "\"" + sym + "\"").collect(Collectors.joining(", "));

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
        }

        return tickers;
    }

    private ObjectMapper objectMapper(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = messageConverter.getObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper;
    }
}
