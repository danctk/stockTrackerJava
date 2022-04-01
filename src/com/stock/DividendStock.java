package com.stock;

public class DividendStock extends Stock {

    private double dividend;

    public void setDividend(double dividend) {
        this.dividend = dividend;
    }

    public double getDividend() {
        return dividend;
    }

    public DividendStock(String stockSymbol, int shares, double price, double dividend) {
        super(stockSymbol, shares, price);
    }

    @Override
    public String toString() {
        return super.toString() + "| Dividend: " + this.dividend +"|";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
