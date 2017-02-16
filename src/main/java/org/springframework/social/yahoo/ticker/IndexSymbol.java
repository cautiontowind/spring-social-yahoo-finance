package org.springframework.social.yahoo.ticker;

/**
 * Created by khaliluddin on 14/02/2017.
 */
public enum IndexSymbol {
    FTSE100_INDEX("^FTSE"),
    DAX_INDEX("^GDAXI"),
    CAC40_INDEX("^FCHI");

    private final String symbol;

    public String getSymbol(){return symbol;}

    IndexSymbol(String symbol){this.symbol = symbol;}

}
