package org.springframework.social.yahoo.ticker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by khaliluddin on 14/02/2017.
 */
public class AbstractTicker  implements Serializable {

    @JsonProperty("Symbol")
    protected String symbol;

    @JsonProperty("created")
    protected String date;

    @JsonProperty("High")
    private String high;


    @JsonProperty("Low")
    private String low;

    @JsonProperty("Close")
    private String close;


    @JsonProperty("Adj_Close")
    private String adjClose;

    @JsonProperty("Open")
    protected String open;

    @JsonProperty("Volume")
    protected String volume;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(String adjClose) {
        this.adjClose = adjClose;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Symbol: ").append(symbol).append(", ");
        sb.append("High: ").append(high).append(", ");
        sb.append("Low: ").append(low).append(", ");
        sb.append("Close: ").append(close).append(", ");
        sb.append("AdjClose: ").append(adjClose).append(", ");
        sb.append("Open: ").append(open).append(", ");
        sb.append("Volume: ").append(volume).append(", ");
        sb.append("Date: ").append(date);
        return sb.toString();
    }
}
