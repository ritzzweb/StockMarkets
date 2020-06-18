package com.mann.stockmarket.Model;

public class IntradayTips {
    String buyabove;
    String stockname;
    String stoploss;
    String tgt;

    public String getBuyabove() {
        return buyabove;
    }

    public void setBuyabove(String buyabove) {
        this.buyabove = buyabove;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getStoploss() {
        return stoploss;
    }

    public void setStoploss(String stoploss) {
        this.stoploss = stoploss;
    }

    public String getTgt() {
        return tgt;
    }

    public void setTgt(String tgt) {
        this.tgt = tgt;
    }
}
