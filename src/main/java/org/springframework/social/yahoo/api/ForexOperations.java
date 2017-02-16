package org.springframework.social.yahoo.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.social.yahoo.ticker.AbstractTicker;
import org.springframework.social.yahoo.ticker.ForexName;
import org.springframework.social.yahoo.ticker.Ticker;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by khaliluddin on 13/07/2015.
 */
public interface ForexOperations {
    public Ticker ticker(ForexName forexNames) throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException;
    public List<Ticker> tickers(ForexName[] forexNames) throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException;
    public List<AbstractTicker> historicalData(ForexName firstName, LocalDate startDate, LocalDate endDate)  throws UnsupportedEncodingException,JsonParseException, JsonMappingException, IOException;
}
