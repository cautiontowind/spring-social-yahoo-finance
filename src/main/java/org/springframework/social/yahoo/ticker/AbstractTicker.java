package org.springframework.social.yahoo.ticker;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by khaliluddin on 14/02/2017.
 */
public class AbstractTicker  implements Serializable {

    @JsonProperty("Symbol")
    protected String symbol;

    @JsonProperty("created")
    protected DateTime date;

    @JsonProperty("High")
    private BigDecimal high;


    @JsonProperty("Low")
    private BigDecimal low;

    @JsonProperty("Close")
    private BigDecimal close;


    @JsonProperty("Adj_Close")
    private BigDecimal adjClose;

    @JsonProperty("Open")
    protected BigDecimal open;

    @JsonProperty("Volume")
    protected Long volume;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(BigDecimal adjClose) {
        this.adjClose = adjClose;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
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
