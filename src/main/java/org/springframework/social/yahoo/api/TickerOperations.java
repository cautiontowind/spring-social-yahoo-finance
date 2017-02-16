package org.springframework.social.yahoo.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.social.yahoo.ticker.AbstractTicker;
import org.springframework.social.yahoo.ticker.IndexSymbol;
import org.springframework.social.yahoo.ticker.LondonExchangeSymbol;
import org.springframework.social.yahoo.ticker.Ticker;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;


public interface TickerOperations {

    public Ticker index(IndexSymbol indexSymbol)  throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException;
    public List<Ticker> index(IndexSymbol[] indexSymbols)  throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException;
    public Ticker ticker(LondonExchangeSymbol tickerName)  throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException;
    public List<Ticker> tickers(LondonExchangeSymbol[] tickerNames) throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException;
    public List<AbstractTicker> historicalData(LondonExchangeSymbol tickerName, LocalDate startDate, LocalDate endDate)  throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException;


}
