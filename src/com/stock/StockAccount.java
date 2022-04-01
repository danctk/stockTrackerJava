package com.stock;

import java.util.Scanner;

public class StockAccount {
    Stock stockRef = new Stock();
    private String fullName;
    private double balance;
    static final double STARTING_BALANCE = 1000;

    public StockAccount(String fullName, double balance) {
        super();
        this.fullName = fullName;
        this.balance = balance;
    }

    public Stock getStock() {

        return stockRef;
    }

    public void sellStock(Stock soldStock) throws StockException{
        if (soldStock.getStockSymbol().equals(stockRef.getStockSymbol())) {
            if (soldStock.getShares() <= stockRef.getShares()) {
                stockRef.setShares(stockRef.getShares() - soldStock.getShares());
                stockRef.setPrice(soldStock.getPrice());
                this.balance += (soldStock.getShares() * soldStock.getPrice());
            } else {
                System.out.println("You do not have enough shares of this stock: you have " + stockRef.getShares() + " shares." +
                        "would you like to sell all of your shares? (y/n)");
                Scanner scan = new Scanner(System.in);
                String answer = scan.nextLine();
                if (answer.equals("y")) {
                    this.balance += (stockRef.getShares() * soldStock.getPrice());
                    stockRef.setShares(0);
                    stockRef.setStockSymbol("");
                    stockRef.setPrice(0);
                    System.out.println("You've sold " + soldStock.getShares() +" shares of " +
                            soldStock.getStockSymbol() + " at $" + soldStock.getPrice());
                }

            }
        } else {
            System.out.println("You do not own shares of this stock.");
        }

    }


    public void buyStock(Stock stock) throws StockException{
        if(stockRef.getShares() > 0 && (stock.getStockSymbol().equals(stockRef.getStockSymbol()))) {
            if (stock.getPrice() * stock.getShares() > this.balance) {
                System.out.println("cannot buy stock, insufficient balance. Would you like to buy as many shares as you can afford? (y/n)");
                Scanner scan = new Scanner(System.in);
                String answer = scan.nextLine();
                if (answer.equals("y")) {
                    stock.setShares(stockRef.getShares() + (int) (balance / stock.getPrice()));
                    this.stockRef = stock;
                    this.balance -= ((int) (balance / stock.getPrice()) * stock.getPrice());
                    System.out.println("Purchased completed.");
                    System.out.println("Stock: " + stock.getStockSymbol() + " :" + stock.getShares() +
                            " shares at $" + stock.getPrice());
                    System.out.println("Balance is at : " + this.balance);
                } else {
                    System.out.println("Order Cancelled.");
                }

            } else {
                this.balance -= (stock.getPrice() * stock.getShares());
                stock.setShares(stockRef.getShares() + stock.getShares());
                this.stockRef = stock;
                System.out.println("Purchased completed. ");
                System.out.println("You purchased: " + stock.getStockSymbol() + " :" + stock.getShares() +
                        " shares at $" + stock.getPrice());
                System.out.println("Balance is at : " + this.balance);
            }
        } else if(stockRef.getShares() > 0) {
            System.out.println("This is the wrong stock symbol. cannot track more than one stock atm.");
        } else {
            if (stock.getPrice() * stock.getShares() > this.balance) {
                System.out.println("cannot buy stock, insufficient balance. Would you like to buy as many shares as you can afford? (y/n)");
                Scanner scan = new Scanner(System.in);
                String answer = scan.nextLine();
                if (answer.equals("y")) {

                    stock.setShares((int) (balance / stock.getPrice()));
                    this.stockRef = stock;
                    this.balance -= ((int) (balance / stock.getPrice()) * stock.getPrice());
                    System.out.println("Purchased completed. ");
                    System.out.println("Stock: " + stock.getStockSymbol() + " :" + stock.getShares() +
                            " shares at $" + stock.getPrice());
                    System.out.println("Balance is at : " + this.balance);
                } else {
                    System.out.println("Order Cancelled.");
                }

            } else {
                this.balance -= (stock.getPrice() * stock.getShares());
                stock.setShares(stockRef.getShares() + stock.getShares());
                this.stockRef = stock;
                System.out.println("Purchased completed.");
                System.out.println("You purchased: " + stock.getStockSymbol() + " :" + stock.getShares() +
                        " shares at $" + stock.getPrice());
                System.out.println("Balance is at : " + this.balance);
            }
        }

    }

    public StockAccount(String fullName) {
        this(fullName, STARTING_BALANCE);
    }

    public String getFullName() {
        return fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double initialBalance) {
       if(initialBalance <=0) {
           System.out.println("cannot set balance to that number, using default balance:");
           balance = STARTING_BALANCE;
       } else {
        balance = initialBalance; }
    }

    public void setFullName(String setName) {
        fullName = setName;
    }

}
