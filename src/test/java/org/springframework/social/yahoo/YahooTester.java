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

        String key = "dj0yJmk9ejcwMTU2TkhSYmp4JmQ9WVdrOWJVOUxjRnBMTkhNbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD1kYw--";

        String secret ="3252e33b311be16db9cecacb19e101e93330ce0b";
       YahooTemplate template = new YahooTemplate(key,secret);
        try {
            Ticker ticker = template.tickerOperations().ticker(TickerName.LSE_LONDON_STOCK_EXCHANGE_GROUP_PLC);
            System.out.print(ticker);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
