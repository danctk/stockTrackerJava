package com.stock;

import java.io.Serializable;
import java.util.*;

public class StockAccount implements Serializable {
    private static final int serialVersionUID = 1;
    private SortedMap<String, Stock> stockRef = new SortedMap<String, Stock>() {
        @Override
        public Comparator<? super String> comparator() {
            return null;
        }

        @Override
        public SortedMap<String, Stock> subMap(String fromKey, String toKey) {
            return null;
        }

        @Override
        public SortedMap<String, Stock> headMap(String toKey) {
            return null;
        }

        @Override
        public SortedMap<String, Stock> tailMap(String fromKey) {
            return null;
        }

        @Override
        public String firstKey() {
            return null;
        }

        @Override
        public String lastKey() {
            return null;
        }

        @Override
        public Set<String> keySet() {
            return null;
        }

        @Override
        public Collection<Stock> values() {
            return null;
        }

        @Override
        public Set<Entry<String, Stock>> entrySet() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public Stock get(Object key) {
            return null;
        }

        @Override
        public Stock put(String key, Stock value) {
            return null;
        }

        @Override
        public Stock remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends String, ? extends Stock> m) {

        }

        @Override
        public void clear() {

        }
    };
    private String fullName;
    private double balance;
    static final double STARTING_BALANCE = 1000;

    public StockAccount(String fullName, double balance) {
        super();
        this.fullName = fullName;
        this.balance = balance;
        stockRef = new TreeMap<>();
    }

    public Collection<Stock> getStock() {

        return stockRef.values();
    }

    public void sellStock(Stock soldStock) throws StockException {

        try {

            if (stockRef.isEmpty()) {
                throw new StockException("You do not have any stocks");

            } else {
                Stock heldStock = stockRef.get(soldStock.getStockSymbol());
                int heldShares = heldStock.getShares();
                if (stockRef.get(soldStock.getStockSymbol()) != null) {
                    if(soldStock.getShares() == heldShares) {
                        this.balance += (heldShares * soldStock.getPrice());
                        stockRef.remove(soldStock.getStockSymbol());
                        System.out.println("You've sold " + soldStock.getShares() + " shares of " +
                                soldStock.getStockSymbol() + " at $" + soldStock.getPrice());
                    } else if (soldStock.getShares() < heldShares) {
                        soldStock.setShares(heldStock.getShares() - soldStock.getShares());
                        stockRef.put(soldStock.getStockSymbol(), soldStock);
                        this.balance += (soldStock.getShares() * soldStock.getPrice());
                    } else {
                        throw new StockException("You do not have enough shares of this stock");
                    }
                }
            }
        } catch(StockException e) {

            if (stockRef.get(soldStock.getStockSymbol()) == null) {
                System.out.println(e.getMessage());
            } else {
                System.out.println(e.getMessage());
                Stock heldStock = stockRef.get(soldStock.getStockSymbol());
                int heldShares = heldStock.getShares();
                System.out.println("you have " + heldStock.getShares() + " shares." +
                        "would you like to sell all of your shares? (y/n)");
                Scanner scan = new Scanner(System.in);
                String answer = scan.nextLine();
                if (answer.equals("y")) {
                    this.balance += (heldShares * soldStock.getPrice());
                    stockRef.remove(soldStock.getStockSymbol());
                    System.out.println("You've sold " + soldStock.getShares() + " shares of " +
                            soldStock.getStockSymbol() + " at $" + soldStock.getPrice());
                }
            }
        }
        }




    public void buyStock(Stock stock) throws StockException {

        try {
           if (stockRef.get(stock.getStockSymbol()) == null  ) {

               if (stock.getPrice() * stock.getShares() > this.balance) {
                   throw new StockException("cannot buy stock, insufficient balance.");
               } else {
                   this.balance -= (stock.getPrice() * stock.getShares());
                   stock.setShares(stock.getShares());
                   stockRef.put(stock.getStockSymbol(), stock);
                   System.out.println("Purchased completed. ");


               }} else {
                   if (stock.getPrice() * stock.getShares() > this.balance) {
                       throw new StockException("cannot buy stock, insufficient balance.");

                   } else {
                       this.balance -= (stock.getPrice() * stock.getShares());
                       Stock heldStock = stockRef.get(stock.getStockSymbol());
                       int heldShares = heldStock.getShares();
                       stock.setShares( heldShares + stock.getShares());
                       stockRef.put(stock.getStockSymbol(), stock);
                       System.out.println("Purchased completed.");

                   }
               }

        } catch (StockException e) {
            System.out.println(e.getMessage());
            System.out.println("Would you like to buy as many shares as you can afford? (y/n)");
            Scanner scan = new Scanner(System.in);
            String answer = scan.nextLine();
            if (answer.equals("y")) {
                stock.setShares((int) (balance / stock.getPrice()));
                stockRef.put(stock.getStockSymbol(), stock);
                this.balance -= ((int) (balance / stock.getPrice()) * stock.getPrice());
                System.out.println("Purchased completed.");
            } else {
                System.out.println("Order Cancelled.");
            }
        }
 }

    public StockAccount(String fullName) {
            this(fullName, STARTING_BALANCE);
        }

        public String getFullName () {
            return fullName;
        }

        public double getBalance () {
            return balance;
        }

        public void setBalance ( double initialBalance){
            if (initialBalance <= 0) {
                System.out.println("cannot set balance to that number, using default balance:");
                balance = STARTING_BALANCE;
            } else {
                balance = initialBalance;
            }
        }

        public void setFullName (String setName){
            fullName = setName;
        }

    }
