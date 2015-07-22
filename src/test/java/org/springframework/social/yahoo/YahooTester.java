package org.springframework.social.yahoo;

import org.springframework.social.yahoo.ticker.TickerName;
import org.springframework.social.yahoo.ticker.Ticker;
import org.springframework.social.yahoo.api.impl.YahooTemplate;

import java.io.IOException;

/**
 * Created by khaliluddin on 15/01/15.
 */
public class YahooTester {

    public static void main(String[] args) {

        String key = "dj0yJmk9MVhtYVZMZXhNS0dkJmQ9WVdrOWRUWmhkakJITXpBbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD04Yg--";
        String secret ="054e0b254fa06eae070af2c79d6d9feeae0e1988";
       YahooTemplate template = new YahooTemplate(key,secret);
        try {
            Ticker ticker = template.tickerOperations().ticker(TickerName.LSE_LONDON_STOCK_EXCHANGE_GROUP_PLC);
            System.out.print(ticker);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
