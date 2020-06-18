package com.mann.stockmarket.Model;

public class PositionalTips {

    String buyabove;
    String stockname;
    String stoploss;
    String tgt;
    String remarks;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
