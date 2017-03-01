
package org.springframework.social.yahoo.ticker;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

public class Ticker extends AbstractTicker implements Serializable{

// "EarningsShare":"0.2160",
// "":null,
// "EPSEstimateCurrentYear":"5.3100",
// "EPSEstimateNextYear":null,
// "EPSEstimateNextQuarter":"0.0000",
// "DaysLow":"235.1500",
// "DaysHigh":"237.6000",
// "YearLow":"179.1000",
// "YearHigh":"258.0000",
// "HoldingsGainPercent":null,"AnnualizedGain":null,"HoldingsGain":null,"HoldingsGainPercentRealtime":null,"HoldingsGainRealtime":null,"MoreInfo":null,"OrderBookRealtime":null,"MarketCapitalization":"62.89B","MarketCapRealtime":null,"EBITDA":"10.12B","ChangeFromYearLow":"58.1000","PercentChangeFromYearLow":"+32.4400%","LastTradeRealtimeWithTime":null,"ChangePercentRealtime":null,"ChangeFromYearHigh":"-20.8000","PercebtChangeFromYearHigh":"-8.0620%","LastTradeWithTime":"3:49pm - <b>237.2000</b>","LastTradePriceOnly":"237.2000","HighLimit":null,"LowLimit":null,"DaysRange":"235.1500 - 237.6000","DaysRangeRealtime":null,"FiftydayMovingAverage":"239.6150","TwoHundreddayMovingAverage":"231.1190","ChangeFromTwoHundreddayMovingAverage":"6.0810","PercentChangeFromTwoHundreddayMovingAverage":"+2.6311%","ChangeFromFiftydayMovingAverage":"-2.4150","PercentChangeFromFiftydayMovingAverage":"-1.0079%","Name":"VODAFONE GROUP","Notes":null,"Open":"236.3500","PreviousClose":"236.5500","PricePaid":null,"ChangeinPercent":"+0.2748%","PriceSales":"148.5169","PriceBook":"94.8096","ExDividendDate":"6/11/2015","PERatio":"1098.1481","DividendPayDate":null,"PERatioRealtime":null,"PEGRatio":"-1.5400","PriceEPSEstimateCurrentYear":"44.9242","PriceEPSEstimateNextYear":"38.3819","Symbol":"vod.l","SharesOwned":null,"ShortRatio":"0.0000","LastTradeTime":"3:49pm","TickerTrend":null,"OneyrTargetPrice":null,"Volume":"13912355","HoldingsValue":null,"HoldingsValueRealtime":null,"YearRange":"179.1000 - 258.0000","DaysValueChange":null,"DaysValueChangeRealtime":null,"StockExchange":"LSE","DividendYield":null,"PercentChange":"+0.2748%"}}}}";

	@JsonProperty("AfterHoursChangeRealtime")
	private String afterHoursChangeRealtime;
	@JsonProperty("AnnualizedGain")
	private String annualizedGain;
	@JsonProperty("Ask")
	private String ask;
	@JsonProperty("AskRealtime")
	private String askRealtime;
	@JsonProperty("AverageDailyVolume")
	private String averageDailyVolume;
	@JsonProperty("Bid")
	private String bid;
	@JsonProperty("BidRealtime")
	private String bidRealtime;
	@JsonProperty("BookValue")
	private String bookValue;
	@JsonProperty("Change")
	private String change;
	@JsonProperty("ChangeFromFiftyDayMovingAverage")
	private String changeFromFiftyDayMovingAverage;
	@JsonProperty("ChangeFromTwoHundredDayMovingAverage")
	private String changeFromTwoHundredDayMovingAverage;
	@JsonProperty("ChangeFromYearHigh")
	private String changeFromYearHigh;
	@JsonProperty("ChangeFromYearLow")
	private String changeFromYearLow;
	@JsonProperty("ChangePercentRealtime")
	private String changePercentRealtime;
	@JsonProperty("ChangeRealtime")
	private String changeRealtime;
	@JsonProperty("Change_PercentChange")
	private String changePercentChange;
	@JsonProperty("ChangeinPercent")
	private String changeInPercent;
	@JsonProperty("Commission")
	private String commission;
	@JsonProperty("Currency")
	private String currency;
	@JsonProperty("DaysHigh")
	private String daysHigh;
	@JsonProperty("DaysLow")
	private String daysLow;
	@JsonProperty("DaysRange")
	private String daysRange;
	@JsonProperty("DaysRangeRealtime")
	private String daysRangeRealtime;
	@JsonProperty("DaysValueChange")
	private String daysValueChange;
	@JsonProperty("DaysValueChangeRealtime")
	private String daysValueChangeRealtime;
	@JsonProperty("DividendPayDate")
	private String dividendPayDate;
	@JsonProperty("DividendShare")
	private String dividendShare;
	@JsonProperty("DividendYield")
	private String dividendYield;
	@JsonProperty("EBITDA")
	private String eBITDA;
	@JsonProperty("EPSEstimateCurrentYear")
	private String ePSEstimateCurrentYear;
	@JsonProperty("EPSEstimateNextQuarter")
	private String ePSEstimateNextQuarter;
	@JsonProperty("EPSEstimateNextYear")
	private String ePSEstimateNextYear;
	@JsonProperty("EarningsShare")
	private String earningsShare;
	@JsonProperty("ErrorIndicationreturnedforsymbolchangedinvalid")
	private String errorIndicationReturnedForSymbolChangedInvalid;
	@JsonProperty("ExDividendDate")
	private String exDividendDate;
	@JsonProperty("FiftyDayMovingAverage")
	private String fiftyDayMovingAverage;
	@JsonProperty("HighLimit")
	private String highLimit;
	@JsonProperty("HoldingsGain")
	private String holdingsGain;
	@JsonProperty("HoldingsGainPercent")
	private String holdingsGainPercent;
	@JsonProperty("HoldingsGainPercentRealtime")
	private String holdingsGainPercentRealtime;
	@JsonProperty("HoldingsGainRealtime")
	private String holdingsGainRealtime;
	@JsonProperty("HoldingsValue")
	private String holdingsValue;
	@JsonProperty("HoldingsValueRealtime")
	private String holdingsValueRealtime;
	@JsonProperty("LastTradeDate")
	private String lastTradeDate;
	@JsonProperty("LastTradePriceOnly")
	private String lastTradePriceOnly;
	@JsonProperty("LastTradeRealtimeWithTime")
	private String lastTradeRealtimeWithTime;
	@JsonProperty("LastTradeTime")
	private String lastTradeTime;
	@JsonProperty("LastTradeWithTime")
	private String lastTradeWithTime;
	@JsonProperty("LowLimit")
	private String lowLimit;
	@JsonProperty("MarketCapRealtime")
	private String marketCapRealtime;
	@JsonProperty("MarketCapitalization")
	private String marketCapitalization;
	@JsonProperty("MoreInfo")
	private String moreInfo;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Notes")
	private String notes;
	@JsonProperty("OneyrTargetPrice")
	private String oneYrTargetPrice;

	@JsonProperty("OrderBookRealtime")
	private String orderBookRealtime;
	@JsonProperty("PEGRatio")
	private String pEGRatio;
	@JsonProperty("PERatio")
	private String pERatio;
	@JsonProperty("PERatioRealtime")
	private String pERatioRealtime;
	@JsonProperty("PercebtChangeFromYearHigh")
	private String percebtChangeFromYearHigh;
	@JsonProperty("PercentChange")
	private String percentChange;
	@JsonProperty("PercentChangeFromFiftydayMovingAverage")
	private String percentChangeFromFiftyDayMovingAverage;
	@JsonProperty("PercentChangeFromTwoHundreddayMovingAverage")
	private String percentChangeFromTwoHundredDayMovingAverage;
	@JsonProperty("PercentChangeFromYearLow")
	private String percentChangeFromYearLow;
	@JsonProperty("PreviousClose")
	private String previousClose;
	@JsonProperty("PriceBook")
	private String priceBook;
	@JsonProperty("PriceEPSEstimateCurrentYear")
	private String priceEPSEstimateCurrentYear;
	@JsonProperty("PriceEPSEstimateNextYear")
	private String priceEPSEstimateNextYear;
	@JsonProperty("PricePaid")
	private String pricePaid;
	@JsonProperty("PriceSales")
	private String priceSales;
	@JsonProperty("SharesOwned")
	private String sharesOwned;
	@JsonProperty("ShortRatio")
	private String shortRatio;
	@JsonProperty("StockExchange")
	private String stockExchange;
	@JsonProperty("TickerTrend")
	private String tickerTrend;
	@JsonProperty("TradeDate")
	private String tradeDate;
	@JsonProperty("TwoHundredDayMovingAverage")
	private String twoHundredDayMovingAverage;

	@JsonProperty("YearHigh")
	private String yearHigh;
	@JsonProperty("YearLow")
	private String yearLow;
	@JsonProperty("YearRange")
	private String yearRange;


	//Accessors


	public String getAfterHoursChangeRealtime() {
		return afterHoursChangeRealtime;
	}

	public void setAfterHoursChangeRealtime(String afterHoursChangeRealtime) {
		this.afterHoursChangeRealtime = afterHoursChangeRealtime;
	}

	public String getAnnualizedGain() {
		return annualizedGain;
	}

	public void setAnnualizedGain(String annualizedGain) {
		this.annualizedGain = annualizedGain;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getAskRealtime() {
		return askRealtime;
	}

	public void setAskRealtime(String askRealtime) {
		this.askRealtime = askRealtime;
	}

	public String getAverageDailyVolume() {
		return averageDailyVolume;
	}

	public void setAverageDailyVolume(String averageDailyVolume) {
		this.averageDailyVolume = averageDailyVolume;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBidRealtime() {
		return bidRealtime;
	}

	public void setBidRealtime(String bidRealtime) {
		this.bidRealtime = bidRealtime;
	}

	public String getBookValue() {
		return bookValue;
	}

	public void setBookValue(String bookValue) {
		this.bookValue = bookValue;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getChangeFromFiftyDayMovingAverage() {
		return changeFromFiftyDayMovingAverage;
	}

	public void setChangeFromFiftyDayMovingAverage(String changeFromFiftyDayMovingAverage) {
		this.changeFromFiftyDayMovingAverage = changeFromFiftyDayMovingAverage;
	}

	public String getChangeFromTwoHundredDayMovingAverage() {
		return changeFromTwoHundredDayMovingAverage;
	}

	public void setChangeFromTwoHundredDayMovingAverage(String changeFromTwoHundredDayMovingAverage) {
		this.changeFromTwoHundredDayMovingAverage = changeFromTwoHundredDayMovingAverage;
	}

	public String getChangeFromYearHigh() {
		return changeFromYearHigh;
	}

	public void setChangeFromYearHigh(String changeFromYearHigh) {
		this.changeFromYearHigh = changeFromYearHigh;
	}

	public String getChangeFromYearLow() {
		return changeFromYearLow;
	}

	public void setChangeFromYearLow(String changeFromYearLow) {
		this.changeFromYearLow = changeFromYearLow;
	}

	public String getChangePercentRealtime() {
		return changePercentRealtime;
	}

	public void setChangePercentRealtime(String changePercentRealtime) {
		this.changePercentRealtime = changePercentRealtime;
	}

	public String getChangeRealtime() {
		return changeRealtime;
	}

	public void setChangeRealtime(String changeRealtime) {
		this.changeRealtime = changeRealtime;
	}

	public String getChangePercentChange() {
		return changePercentChange;
	}

	public void setChangePercentChange(String changePercentChange) {
		this.changePercentChange = changePercentChange;
	}

	public String getChangeInPercent() {
		return changeInPercent;
	}

	public void setChangeInPercent(String changeInPercent) {
		this.changeInPercent = changeInPercent;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDaysHigh() {
		return daysHigh;
	}

	public void setDaysHigh(String daysHigh) {
		this.daysHigh = daysHigh;
	}

	public String getDaysLow() {
		return daysLow;
	}

	public void setDaysLow(String daysLow) {
		this.daysLow = daysLow;
	}

	public String getDaysRange() {
		return daysRange;
	}

	public void setDaysRange(String daysRange) {
		this.daysRange = daysRange;
	}

	public String getDaysRangeRealtime() {
		return daysRangeRealtime;
	}

	public void setDaysRangeRealtime(String daysRangeRealtime) {
		this.daysRangeRealtime = daysRangeRealtime;
	}

	public String getDaysValueChange() {
		return daysValueChange;
	}

	public void setDaysValueChange(String daysValueChange) {
		this.daysValueChange = daysValueChange;
	}

	public String getDaysValueChangeRealtime() {
		return daysValueChangeRealtime;
	}

	public void setDaysValueChangeRealtime(String daysValueChangeRealtime) {
		this.daysValueChangeRealtime = daysValueChangeRealtime;
	}

	public String getDividendPayDate() {
		return dividendPayDate;
	}

	public void setDividendPayDate(String dividendPayDate) {
		this.dividendPayDate = dividendPayDate;
	}

	public String getDividendShare() {
		return dividendShare;
	}

	public void setDividendShare(String dividendShare) {
		this.dividendShare = dividendShare;
	}

	public String getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(String dividendYield) {
		this.dividendYield = dividendYield;
	}

	public String geteBITDA() {
		return eBITDA;
	}

	public void seteBITDA(String eBITDA) {
		this.eBITDA = eBITDA;
	}

	public String getePSEstimateCurrentYear() {
		return ePSEstimateCurrentYear;
	}

	public void setePSEstimateCurrentYear(String ePSEstimateCurrentYear) {
		this.ePSEstimateCurrentYear = ePSEstimateCurrentYear;
	}

	public String getePSEstimateNextQuarter() {
		return ePSEstimateNextQuarter;
	}

	public void setePSEstimateNextQuarter(String ePSEstimateNextQuarter) {
		this.ePSEstimateNextQuarter = ePSEstimateNextQuarter;
	}

	public String getePSEstimateNextYear() {
		return ePSEstimateNextYear;
	}

	public void setePSEstimateNextYear(String ePSEstimateNextYear) {
		this.ePSEstimateNextYear = ePSEstimateNextYear;
	}

	public String getEarningsShare() {
		return earningsShare;
	}

	public void setEarningsShare(String earningsShare) {
		this.earningsShare = earningsShare;
	}

	public String getErrorIndicationReturnedForSymbolChangedInvalid() {
		return errorIndicationReturnedForSymbolChangedInvalid;
	}

	public void setErrorIndicationReturnedForSymbolChangedInvalid(String errorIndicationReturnedForSymbolChangedInvalid) {
		this.errorIndicationReturnedForSymbolChangedInvalid = errorIndicationReturnedForSymbolChangedInvalid;
	}

	public String getExDividendDate() {
		return exDividendDate;
	}

	public void setExDividendDate(String exDividendDate) {
		this.exDividendDate = exDividendDate;
	}

	public String getFiftyDayMovingAverage() {
		return fiftyDayMovingAverage;
	}

	public void setFiftyDayMovingAverage(String fiftyDayMovingAverage) {
		this.fiftyDayMovingAverage = fiftyDayMovingAverage;
	}

	public String getHighLimit() {
		return highLimit;
	}

	public void setHighLimit(String highLimit) {
		this.highLimit = highLimit;
	}

	public String getHoldingsGain() {
		return holdingsGain;
	}

	public void setHoldingsGain(String holdingsGain) {
		this.holdingsGain = holdingsGain;
	}

	public String getHoldingsGainPercent() {
		return holdingsGainPercent;
	}

	public void setHoldingsGainPercent(String holdingsGainPercent) {
		this.holdingsGainPercent = holdingsGainPercent;
	}

	public String getHoldingsGainPercentRealtime() {
		return holdingsGainPercentRealtime;
	}

	public void setHoldingsGainPercentRealtime(String holdingsGainPercentRealtime) {
		this.holdingsGainPercentRealtime = holdingsGainPercentRealtime;
	}

	public String getHoldingsGainRealtime() {
		return holdingsGainRealtime;
	}

	public void setHoldingsGainRealtime(String holdingsGainRealtime) {
		this.holdingsGainRealtime = holdingsGainRealtime;
	}

	public String getHoldingsValue() {
		return holdingsValue;
	}

	public void setHoldingsValue(String holdingsValue) {
		this.holdingsValue = holdingsValue;
	}

	public String getHoldingsValueRealtime() {
		return holdingsValueRealtime;
	}

	public void setHoldingsValueRealtime(String holdingsValueRealtime) {
		this.holdingsValueRealtime = holdingsValueRealtime;
	}

	public String getLastTradeDate() {
		return lastTradeDate;
	}

	public void setLastTradeDate(String lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}

	public String getLastTradePriceOnly() {
		return lastTradePriceOnly;
	}

	public void setLastTradePriceOnly(String lastTradePriceOnly) {
		this.lastTradePriceOnly = lastTradePriceOnly;
	}

	public String getLastTradeRealtimeWithTime() {
		return lastTradeRealtimeWithTime;
	}

	public void setLastTradeRealtimeWithTime(String lastTradeRealtimeWithTime) {
		this.lastTradeRealtimeWithTime = lastTradeRealtimeWithTime;
	}

	public String getLastTradeTime() {
		return lastTradeTime;
	}

	public void setLastTradeTime(String lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

	public String getLastTradeWithTime() {
		return lastTradeWithTime;
	}

	public void setLastTradeWithTime(String lastTradeWithTime) {
		this.lastTradeWithTime = lastTradeWithTime;
	}

	public String getLowLimit() {
		return lowLimit;
	}

	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}

	public String getMarketCapRealtime() {
		return marketCapRealtime;
	}

	public void setMarketCapRealtime(String marketCapRealtime) {
		this.marketCapRealtime = marketCapRealtime;
	}

	public String getMarketCapitalization() {
		return marketCapitalization;
	}

	public void setMarketCapitalization(String marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOneYrTargetPrice() {
		return oneYrTargetPrice;
	}

	public void setOneYrTargetPrice(String oneYrTargetPrice) {
		this.oneYrTargetPrice = oneYrTargetPrice;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public String getOrderBookRealtime() {
		return orderBookRealtime;
	}

	public void setOrderBookRealtime(String orderBookRealtime) {
		this.orderBookRealtime = orderBookRealtime;
	}

	public String getpEGRatio() {
		return pEGRatio;
	}

	public void setpEGRatio(String pEGRatio) {
		this.pEGRatio = pEGRatio;
	}

	public String getpERatio() {
		return pERatio;
	}

	public void setpERatio(String pERatio) {
		this.pERatio = pERatio;
	}

	public String getpERatioRealtime() {
		return pERatioRealtime;
	}

	public void setpERatioRealtime(String pERatioRealtime) {
		this.pERatioRealtime = pERatioRealtime;
	}

	public String getPercebtChangeFromYearHigh() {
		return percebtChangeFromYearHigh;
	}

	public void setPercebtChangeFromYearHigh(String percebtChangeFromYearHigh) {
		this.percebtChangeFromYearHigh = percebtChangeFromYearHigh;
	}

	public String getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}

	public String getPercentChangeFromFiftyDayMovingAverage() {
		return percentChangeFromFiftyDayMovingAverage;
	}

	public void setPercentChangeFromFiftyDayMovingAverage(String percentChangeFromFiftyDayMovingAverage) {
		this.percentChangeFromFiftyDayMovingAverage = percentChangeFromFiftyDayMovingAverage;
	}

	public String getPercentChangeFromTwoHundredDayMovingAverage() {
		return percentChangeFromTwoHundredDayMovingAverage;
	}

	public void setPercentChangeFromTwoHundredDayMovingAverage(String percentChangeFromTwoHundredDayMovingAverage) {
		this.percentChangeFromTwoHundredDayMovingAverage = percentChangeFromTwoHundredDayMovingAverage;
	}

	public String getPercentChangeFromYearLow() {
		return percentChangeFromYearLow;
	}

	public void setPercentChangeFromYearLow(String percentChangeFromYearLow) {
		this.percentChangeFromYearLow = percentChangeFromYearLow;
	}

	public String getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(String previousClose) {
		this.previousClose = previousClose;
	}

	public String getPriceBook() {
		return priceBook;
	}

	public void setPriceBook(String priceBook) {
		this.priceBook = priceBook;
	}

	public String getPriceEPSEstimateCurrentYear() {
		return priceEPSEstimateCurrentYear;
	}

	public void setPriceEPSEstimateCurrentYear(String priceEPSEstimateCurrentYear) {
		this.priceEPSEstimateCurrentYear = priceEPSEstimateCurrentYear;
	}

	public String getPriceEPSEstimateNextYear() {
		return priceEPSEstimateNextYear;
	}

	public void setPriceEPSEstimateNextYear(String priceEPSEstimateNextYear) {
		this.priceEPSEstimateNextYear = priceEPSEstimateNextYear;
	}

	public String getPricePaid() {
		return pricePaid;
	}

	public void setPricePaid(String pricePaid) {
		this.pricePaid = pricePaid;
	}

	public String getPriceSales() {
		return priceSales;
	}

	public void setPriceSales(String priceSales) {
		this.priceSales = priceSales;
	}

	public String getSharesOwned() {
		return sharesOwned;
	}

	public void setSharesOwned(String sharesOwned) {
		this.sharesOwned = sharesOwned;
	}

	public String getShortRatio() {
		return shortRatio;
	}

	public void setShortRatio(String shortRatio) {
		this.shortRatio = shortRatio;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public String getTickerTrend() {
		return tickerTrend;
	}

	public void setTickerTrend(String tickerTrend) {
		this.tickerTrend = tickerTrend;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTwoHundredDayMovingAverage() {
		return twoHundredDayMovingAverage;
	}

	public void setTwoHundredDayMovingAverage(String twoHundredDayMovingAverage) {
		this.twoHundredDayMovingAverage = twoHundredDayMovingAverage;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public String getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(String yearHigh) {
		this.yearHigh = yearHigh;
	}

	public String getYearLow() {
		return yearLow;
	}

	public void setYearLow(String yearLow) {
		this.yearLow = yearLow;
	}

	public String getYearRange() {
		return yearRange;
	}

	public void setYearRange(String yearRange) {
		this.yearRange = yearRange;
	}

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


	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("AfterHoursChangeRealtime: ").append(afterHoursChangeRealtime).append(", ");
		sb.append("Name: ").append(name).append(", ");
		sb.append("AnnualizedGain: ").append(annualizedGain).append(", ");
		sb.append("Date: ").append(date).append(", ");
		sb.append("Ask: ").append(ask).append(", ");
		sb.append("AskRealtime: ").append(askRealtime).append(", ");
		sb.append("AverageDailyVolume: ").append(averageDailyVolume).append(", ");
		sb.append("Bid: ").append(bid).append(", ");
		sb.append("BidRealtime: ").append(bidRealtime).append(", ");
		sb.append("BookValue: ").append(bookValue).append(", ");
		sb.append("Change: ").append(change).append(", ");
		sb.append("ChangeFromFiftydayMovingAverage: ").append(changeFromFiftyDayMovingAverage).append(", ");
		sb.append("ChangeFromTwoHundreddayMovingAverage: ").append(changeFromTwoHundredDayMovingAverage).append(", ");
		sb.append("AverageDailyVolume: ").append(averageDailyVolume).append(", ");
		sb.append("ChangeFromYearHigh: ").append(changeFromYearHigh).append(", ");
		sb.append("ChangeFromYearLow: ").append(changeFromYearLow).append(", ");
		sb.append("ChangePercentRealtime: ").append(changePercentRealtime).append(", ");
		sb.append("ChangeRealtime: ").append(changeRealtime).append(", ");
		sb.append("ChangePercentChange: ").append(changePercentChange).append(", ");
		sb.append("ChangeinPercent: ").append(changeInPercent).append(", ");
		sb.append("Commission: ").append(commission).append(", ");
		sb.append("Currency: ").append(currency).append(", ");
		sb.append("DaysHigh: ").append(daysHigh).append(", ");
		sb.append("DaysLow: ").append(daysLow).append(", ");
		sb.append("DaysLow: ").append(daysLow).append(", ");
		sb.append("DaysLow: ").append(daysLow).append(", ");
		sb.append("DaysRange: ").append(daysRange).append(", ");
		sb.append("DaysRangeRealtime: ").append(daysRangeRealtime).append(", ");
		sb.append("DaysValueChange: ").append(daysValueChange).append(", ");
		sb.append("DaysValueChangeRealtime: ").append(daysValueChangeRealtime).append(", ");
		sb.append("DividendPayDate: ").append(daysValueChangeRealtime).append(", ");
		sb.append("DividendShare: ").append(dividendShare).append(", ");
		sb.append("DividendYield: ").append(dividendYield).append(", ");
		sb.append("eBITDA: ").append(eBITDA).append(", ");
		sb.append("ePSEstimateCurrentYear: ").append(ePSEstimateCurrentYear).append(", ");
		sb.append("ePSEstimateNextQuarter: ").append(ePSEstimateNextQuarter).append(", ");
		sb.append("ePSEstimateNextYear: ").append(ePSEstimateNextYear).append(", ");
		sb.append("EarningsShare: ").append(earningsShare).append(", ");
		sb.append("ErrorIndicationReturnedForSymbolChangedInvalid: ").append(errorIndicationReturnedForSymbolChangedInvalid).append(", ");
		sb.append("FiftydayMovingAverage: ").append(fiftyDayMovingAverage).append(", ");
		sb.append("HighLimit: ").append(highLimit).append(", ");
		sb.append("HoldingsGain: ").append(holdingsGain).append(", ");
		sb.append("HoldingsGainPercent: ").append(holdingsGainPercent).append(", ");
		sb.append("HoldingsValue: ").append(holdingsValue).append(", ");
		sb.append("HoldingsValueRealtime: ").append(holdingsValueRealtime).append(", ");
		sb.append("LastTradeDate: ").append(lastTradeDate).append(", ");
		sb.append("LastTradePriceOnly: ").append(lastTradePriceOnly).append(", ");
		sb.append("lastTradeRealtimeWithTime: ").append(lastTradeRealtimeWithTime).append(", ");
		sb.append("LastTradeTime: ").append(lastTradeTime).append(", ");
		sb.append("LastTradeWithTime: ").append(lastTradeWithTime).append(", ");
		sb.append("LowLimit: ").append(lowLimit).append(", ");
		sb.append("MarketCapRealtime: ").append(marketCapRealtime).append(", ");
		sb.append("MarketCapitalization: ").append(marketCapitalization).append(", ");
		sb.append("MoreInfo: ").append(moreInfo).append(", ");
		sb.append("Name: ").append(name).append(", ");
		sb.append("Notes: ").append(notes).append(", ");
		sb.append("OneYrTargetPrice: ").append(oneYrTargetPrice).append(", ");
		sb.append("Open: ").append(open).append(", ");
		sb.append("OrderBookRealtime: ").append(orderBookRealtime).append(", ");
		sb.append("PEGRatio: ").append(pEGRatio).append(", ");
		sb.append("pERatio: ").append(pERatio).append(", ");
		sb.append("pERatioRealtime: ").append(pERatioRealtime).append(", ");
		sb.append("PercebtChangeFromYearHigh: ").append(percebtChangeFromYearHigh).append(", ");
		sb.append("PercentChange: ").append(percentChange).append(", ");
		sb.append("PercentChangeFromFiftydayMovingAverage: ").append(percentChangeFromFiftyDayMovingAverage).append(", ");
		sb.append("PercentChangeFromTwoHundreddayMovingAverage: ").append(percentChangeFromTwoHundredDayMovingAverage).append(", ");
		sb.append("Volume: ").append(volume).append(", ");
		sb.append("PercentChangeFromYearLow: ").append(percentChangeFromYearLow).append(", ");
		sb.append("PreviousClose: ").append(previousClose).append(", ");
		sb.append("PriceBook: ").append(priceBook).append(", ");
		sb.append("PriceEPSEstimateCurrentYear: ").append(priceEPSEstimateCurrentYear).append(", ");
		sb.append("PriceEPSEstimateNextYear: ").append(priceEPSEstimateNextYear).append(", ");
		sb.append("PricePaid: ").append(pricePaid).append(", ");
		sb.append("PriceSalesh: ").append(priceSales).append(", ");
		sb.append("SharesOwned: ").append(sharesOwned).append(", ");
		sb.append("ShortRatio: ").append(shortRatio).append(", ");
		sb.append("StockExchange: ").append(stockExchange).append(", ");
		sb.append("TickerTrend: ").append(tickerTrend).append(", ");
		sb.append("TradeDate: ").append(tradeDate).append(", ");
		sb.append("TwoHundreddayMovingAverage: ").append(twoHundredDayMovingAverage).append(", ");
		sb.append("YearHigh: ").append(yearHigh).append(", ");
		sb.append("YearLow: ").append(yearLow).append(", ");
		sb.append("Symbol: ").append(symbol).append(", ");
		sb.append("Date: ").append(date);
		return sb.toString();
	}

}
