package org.springframework.social.yahoo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.yahoo.ticker.Ticker;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khaliluddin on 14/07/2015.
 */
public class JsonNodeTester {

    public static void main(String[] argz){
        String value ="{\"query\":{\"count\":2,\"created\":\"2015-07-14T21:01:58Z\",\"lang\":\"en-US\",\"results\":{\"quote\":[{\"symbol\":\"vod.l\",\"Ask\":\"237.95\",\"AverageDailyVolume\":\"57640700\",\"Bid\":\"237.85\",\"AskRealtime\":null,\"BidRealtime\":null,\"BookValue\":\"2.49\",\"Change_PercentChange\":\"+1.35 - +0.57%\",\"Change\":\"+1.35\",\"Commission\":null,\"Currency\":\"GBp\",\"ChangeRealtime\":null,\"AfterHoursChangeRealtime\":null,\"DividendShare\":null,\"LastTradeDate\":\"7/14/2015\",\"TradeDate\":null,\"EarningsShare\":\"0.22\",\"ErrorIndicationreturnedforsymbolchangedinvalid\":null,\"EPSEstimateCurrentYear\":\"5.31\",\"EPSEstimateNextYear\":null,\"EPSEstimateNextQuarter\":\"0.00\",\"DaysLow\":\"235.15\",\"DaysHigh\":\"237.90\",\"YearLow\":\"179.10\",\"YearHigh\":\"258.00\",\"HoldingsGainPercent\":null,\"AnnualizedGain\":null,\"HoldingsGain\":null,\"HoldingsGainPercentRealtime\":null,\"HoldingsGainRealtime\":null,\"MoreInfo\":null,\"OrderBookRealtime\":null,\"MarketCapitalization\":\"63.07B\",\"MarketCapRealtime\":null,\"EBITDA\":\"10.12B\",\"ChangeFromYearLow\":\"58.80\",\"PercentChangeFromYearLow\":\"+32.83%\",\"LastTradeRealtimeWithTime\":null,\"ChangePercentRealtime\":null,\"ChangeFromYearHigh\":\"-20.10\",\"PercebtChangeFromYearHigh\":\"-7.79%\",\"LastTradeWithTime\":\"4:48pm - <b>237.90</b>\",\"LastTradePriceOnly\":\"237.90\",\"HighLimit\":null,\"LowLimit\":null,\"DaysRange\":\"235.15 - 237.90\",\"DaysRangeRealtime\":null,\"FiftydayMovingAverage\":\"239.57\",\"TwoHundreddayMovingAverage\":\"231.21\",\"ChangeFromTwoHundreddayMovingAverage\":\"6.69\",\"PercentChangeFromTwoHundreddayMovingAverage\":\"+2.89%\",\"ChangeFromFiftydayMovingAverage\":\"-1.67\",\"PercentChangeFromFiftydayMovingAverage\":\"-0.70%\",\"Name\":\"VODAFONE GROUP\",\"Notes\":null,\"Open\":\"236.35\",\"PreviousClose\":\"236.55\",\"PricePaid\":null,\"ChangeinPercent\":\"+0.57%\",\"PriceSales\":\"148.52\",\"PriceBook\":\"94.81\",\"ExDividendDate\":\"6/11/2015\",\"PERatio\":\"1101.39\",\"DividendPayDate\":null,\"PERatioRealtime\":null,\"PEGRatio\":\"-1.54\",\"PriceEPSEstimateCurrentYear\":\"45.06\",\"PriceEPSEstimateNextYear\":\"38.50\",\"Symbol\":\"vod.l\",\"SharesOwned\":null,\"ShortRatio\":\"0.00\",\"LastTradeTime\":\"4:48pm\",\"TickerTrend\":null,\"OneyrTargetPrice\":null,\"Volume\":\"34256548\",\"HoldingsValue\":null,\"HoldingsValueRealtime\":null,\"YearRange\":\"179.10 - 258.00\",\"DaysValueChange\":null,\"DaysValueChangeRealtime\":null,\"StockExchange\":\"LSE\",\"DividendYield\":null,\"PercentChange\":\"+0.57%\"},{\"symbol\":\"bt.l\",\"Ask\":\"463.15\",\"AverageDailyVolume\":\"16025400\",\"Bid\":\"463.05\",\"AskRealtime\":null,\"BidRealtime\":null,\"BookValue\":\"0.10\",\"Change_PercentChange\":\"+2.95 - +0.64%\",\"Change\":\"+2.95\",\"Commission\":null,\"Currency\":\"GBp\",\"ChangeRealtime\":null,\"AfterHoursChangeRealtime\":null,\"DividendShare\":null,\"LastTradeDate\":\"7/14/2015\",\"TradeDate\":null,\"EarningsShare\":null,\"ErrorIndicationreturnedforsymbolchangedinvalid\":null,\"EPSEstimateCurrentYear\":\"18.33\",\"EPSEstimateNextYear\":null,\"EPSEstimateNextQuarter\":\"0.00\",\"DaysLow\":\"457.85\",\"DaysHigh\":\"463.10\",\"YearLow\":\"351.90\",\"YearHigh\":\"472.85\",\"HoldingsGainPercent\":null,\"AnnualizedGain\":null,\"HoldingsGain\":null,\"HoldingsGainPercentRealtime\":null,\"HoldingsGainRealtime\":null,\"MoreInfo\":null,\"OrderBookRealtime\":null,\"MarketCapitalization\":\"38.30B\",\"MarketCapRealtime\":null,\"EBITDA\":\"5.59B\",\"ChangeFromYearLow\":\"111.20\",\"PercentChangeFromYearLow\":\"+31.60%\",\"LastTradeRealtimeWithTime\":null,\"ChangePercentRealtime\":null,\"ChangeFromYearHigh\":\"-9.75\",\"PercebtChangeFromYearHigh\":\"-2.06%\",\"LastTradeWithTime\":\"4:35pm - <b>463.10</b>\",\"LastTradePriceOnly\":\"463.10\",\"HighLimit\":null,\"LowLimit\":null,\"DaysRange\":\"457.85 - 463.10\",\"DaysRangeRealtime\":null,\"FiftydayMovingAverage\":\"451.57\",\"TwoHundreddayMovingAverage\":\"444.24\",\"ChangeFromTwoHundreddayMovingAverage\":\"18.86\",\"PercentChangeFromTwoHundreddayMovingAverage\":\"+4.24%\",\"ChangeFromFiftydayMovingAverage\":\"11.53\",\"PercentChangeFromFiftydayMovingAverage\":\"+2.55%\",\"Name\":\"BT GROUP\",\"Notes\":null,\"Open\":\"461.55\",\"PreviousClose\":\"460.15\",\"PricePaid\":null,\"ChangeinPercent\":\"+0.64%\",\"PriceSales\":\"211.67\",\"PriceBook\":\"4695.41\",\"ExDividendDate\":\"12/29/2014\",\"PERatio\":\"1774.33\",\"DividendPayDate\":null,\"PERatioRealtime\":null,\"PEGRatio\":\"0.00\",\"PriceEPSEstimateCurrentYear\":null,\"PriceEPSEstimateNextYear\":null,\"Symbol\":\"bt.l\",\"SharesOwned\":null,\"ShortRatio\":\"0.00\",\"LastTradeTime\":\"4:35pm\",\"TickerTrend\":null,\"OneyrTargetPrice\":null,\"Volume\":\"9829623\",\"HoldingsValue\":null,\"HoldingsValueRealtime\":null,\"YearRange\":\"351.90 - 472.85\",\"DaysValueChange\":null,\"DaysValueChangeRealtime\":null,\"StockExchange\":\"LSE\",\"DividendYield\":null,\"PercentChange\":\"+0.64%\"}]}}}";

        try {
            JsonNode node = objectMapper().readValue(value, JsonNode.class);

            JsonNode node1 = node.path("query").path("results").path("quote");
            List<Ticker> tickers = new ArrayList<Ticker>();
            String date = node.findValue("created").asText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
            LocalDate localDate = LocalDate.parse(date,formatter);
            if (node1.isArray()) {
                for (final JsonNode objNode : node1) {
                    Ticker aTicker = objectMapper().readValue(objNode.toString(), Ticker.class);
                    aTicker.setDate(new DateTime(localDate));
                    tickers.add(aTicker);

                }
            }
            System.out.println(tickers.get(0));
            System.out.println(tickers.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static ObjectMapper objectMapper(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = messageConverter.getObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper;
    }
}
