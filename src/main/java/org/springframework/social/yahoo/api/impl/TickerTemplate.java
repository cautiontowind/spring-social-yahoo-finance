package org.springframework.social.yahoo.api.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.yahoo.api.TickerOperations;
import org.springframework.social.yahoo.ticker.AbstractTicker;
import org.springframework.social.yahoo.ticker.IndexSymbol;
import org.springframework.social.yahoo.ticker.LondonExchangeSymbol;
import org.springframework.social.yahoo.ticker.Ticker;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    public Ticker ticker(LondonExchangeSymbol tickerName) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException {
        requireUserAuthorization();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("env",UriUtils.encodePath("store://datatables.org/alltableswithkeys","UTF-8"));

        request.set("format","json");

        String yqlQuery = UriUtils.encodePath("select * from yahoo.finance.quotes where symbol in (\"" + tickerName.getSymbol() + "\")", "UTF-8");

        JsonNode node = restTemplate.postForObject(buildUri(yqlQuery), request, JsonNode.class);


        JsonNode node1 = node.path("query").path("results").path("quote");

        Ticker aTicker = objectMapper().readValue(node1.toString(), Ticker.class);
        String date = node.findValue("created").asText();
        //DateTime dateTime = new DateTime(date);//DateTime.parse(date, ISODateTimeFormat.dateTime());
        DateTime dateTime = new DateTime(date);
        aTicker.setDate(dateTime);
       return aTicker;
    }

    @Override
    public Ticker index(IndexSymbol indexSymbol) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException {
        requireUserAuthorization();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("env",UriUtils.encodePath("store://datatables.org/alltableswithkeys","UTF-8"));

        request.set("format","json");

        String yqlQuery = UriUtils.encodePath("select * from yahoo.finance.quotes where symbol in (\"" + indexSymbol.getSymbol() + "\")", "UTF-8");

        JsonNode node = restTemplate.postForObject(buildUri(yqlQuery), request, JsonNode.class);


        JsonNode node1 = node.path("query").path("results").path("quote");

        Ticker aTicker = objectMapper().readValue(node1.toString(), Ticker.class);
        String date = node.findValue("created").asText();
        //DateTime dateTime = DateTime.parse(date, ISODateTimeFormat.dateTime());
        DateTime dateTime = new DateTime(date);
        aTicker.setDate(dateTime);
        return aTicker;
    }

    @Override
    public List<Ticker> index(IndexSymbol[] indexSymbols) throws UnsupportedEncodingException, JsonParseException, JsonMappingException, IOException {
        requireUserAuthorization();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("env",UriUtils.encodePath("store://datatables.org/alltableswithkeys","UTF-8"));
        request.set("format", "json");

        List<String> merge = new ArrayList<String>();

        for (IndexSymbol symbol : indexSymbols) {
            if(symbol.getSymbol().startsWith("^")){
                merge.add(symbol.getSymbol());
            }else {
                merge.add(symbol.getSymbol() + ".L");
            }
        }
        //Add quotes to each element and join
        String allSymbols = merge.stream().map((sym) -> "\"" + sym + "\"").collect(Collectors.joining(", "));
        String yqlQuery = UriUtils.encodePath("select * from yahoo.finance.quotes where symbol in (" + allSymbols + ")", "UTF-8");
        JsonNode node = restTemplate.postForObject(buildUri(yqlQuery), request, JsonNode.class);
        JsonNode node1 = node.path("query").path("results").path("quote");
        List<Ticker> tickers = new ArrayList<Ticker>();
        String date = node.findValue("created").asText();
        DateTime dateTime = DateTime.parse(date, ISODateTimeFormat.dateTime());
        if (node1.isArray()) {
            for (final JsonNode objNode : node1) {
                Ticker aTicker = objectMapper().readValue(objNode.toString(), Ticker.class);
                aTicker.setDate(dateTime);
                tickers.add(aTicker);

            }
            return tickers;
        }

        Ticker aTicker = objectMapper().readValue(node1.toString(), Ticker.class);
        aTicker.setDate(dateTime);
        tickers.add(aTicker);
        return tickers;
    }

    @Override
    public List<Ticker> tickers(LondonExchangeSymbol[] tickerNames) throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException {
        requireUserAuthorization();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("env",UriUtils.encodePath("store://datatables.org/alltableswithkeys","UTF-8"));
        request.set("format", "json");

        List<String> merge = new ArrayList<String>();

        for (LondonExchangeSymbol symbol : tickerNames) {
            if(symbol.getSymbol().startsWith("^")){
                merge.add(symbol.getSymbol());
            }else {
                merge.add(symbol.getSymbol() + ".L");
            }
        }
        //Add quotes to each element and join
        String allSymbols = merge.stream().map((sym) -> "\"" + sym + "\"").collect(Collectors.joining(", "));
        String yqlQuery = UriUtils.encodePath("select * from yahoo.finance.quotes where symbol in (" + allSymbols + ")", "UTF-8");
        JsonNode node = restTemplate.postForObject(buildUri(yqlQuery), request, JsonNode.class);
        JsonNode node1 = node.path("query").path("results").path("quote");
        List<Ticker> tickers = new ArrayList<Ticker>();
        String date = node.findValue("created").asText();
        DateTime dateTime = DateTime.parse(date, ISODateTimeFormat.dateTime());
        if (node1.isArray()) {
            for (final JsonNode objNode : node1) {
                Ticker aTicker = objectMapper().readValue(objNode.toString(), Ticker.class);
                aTicker.setDate(dateTime);
                tickers.add(aTicker);

            }
            return tickers;
        }

        Ticker aTicker = objectMapper().readValue(node1.toString(), Ticker.class);
        aTicker.setDate(dateTime);
        tickers.add(aTicker);
        return tickers;
    }

    @Override
    public List<AbstractTicker> historicalData(LondonExchangeSymbol tickerName, LocalDate startDate, LocalDate endDate) throws UnsupportedEncodingException, JsonParseException, JsonMappingException, IOException {
        requireUserAuthorization();
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("env",UriUtils.encodePath("store://datatables.org/alltableswithkeys","UTF-8"));

        request.set("format","json");
        request.set("diagnostics","false");
        request.set("debug","false");

        String yqlQuery = UriUtils.encodePath("select * from yahoo.finance.historicaldata where symbol =\"" + tickerName.getSymbol() + "\" and startDate =\""+startDate+"\" and endDate = \""+endDate+"\"", "UTF-8");

        JsonNode node = restTemplate.postForObject(buildUri(yqlQuery), request, JsonNode.class);


        JsonNode node1 = node.path("query").path("results").path("quote");
        List<AbstractTicker> tickers = new ArrayList<AbstractTicker>();
        String date = node.findValue("created").asText();

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/d");

        Date localDate = null;
        try {
            localDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (node1.isArray()) {
            for (final JsonNode objNode : node1) {
                AbstractTicker aTicker = objectMapper().readValue(objNode.toString(), AbstractTicker.class);
                if(localDate != null) {
                    aTicker.setDate(new DateTime(localDate));
                }
                tickers.add(aTicker);

            }
            return tickers;
        }

        Ticker aTicker = objectMapper().readValue(node1.toString(), Ticker.class);
        aTicker.setDate(new DateTime(localDate));
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
