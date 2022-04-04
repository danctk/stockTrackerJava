package com.stock;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

public class Stock implements Serializable {
    private static final int serialVersionUID = 1;
    private String stockSymbol;
    private int shares;
    private double price;

    public Stock(String stockSymbol, int shares, double price) {
        super();
        this.stockSymbol = stockSymbol;
        this.shares = shares;
        this.price = price;
    }

    @Override
    public String toString() {
        return "|Stock Symbol: "+ stockSymbol + "| Shares: " + shares + "| Price: " + price;
    }

    public Stock(){
        this("",0,0);
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getShares() {
        return shares;
    }

    public double getPrice() {
        return price;
    }
}
