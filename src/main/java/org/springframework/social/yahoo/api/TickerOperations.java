package org.springframework.social.yahoo.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.social.yahoo.ticker.Ticker;
import org.springframework.social.yahoo.ticker.TickerName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;


public interface TickerOperations {

    public Ticker ticker(TickerName tickerName)  throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException;
    public List<Ticker> tickers(TickerName[] tickerNames) throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException;
}
