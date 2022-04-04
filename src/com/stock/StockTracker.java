package com.stock;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;


public class StockTracker {

    public static void main(String[] args) throws StockException {
        Scanner scan = new Scanner(System.in);
       char action = 'x';

        StockAccount account = null;
        printIntro();
        try{
            StockFileManager stockFileManager = new StockFileManager();
            account =  stockFileManager.getStoredAccount();
        }
        catch ( NullPointerException e) {
            System.out.println("No file found, collecting new user");

        }
        if(account == null) {
            System.out.println("No file found, collecting new user");
            account = collectAccountInfo();
            continueOperation(scan, action, account);
        } else {
            continueOperation(scan, action, account);

        }

    }

    private static void continueOperation(Scanner scan, char action, StockAccount account) {
        printAccountSummary(account);
        System.out.println();

        while (action != 'q') {
            System.out.println("What do you want to do?");
            System.out.println("b = buy");
            System.out.println("s = sell");
            System.out.println("q = quit");
            action = scan.next().charAt(0);
            switch (action) {
                case 'q':
                    System.out.println("you've selected quit");
                    try {

                        StockFileManager.storeAccountInfo(account);
                    } catch (StockException se) {
                        System.out.println("an exception occured");
                        se.printStackTrace();
                    }
                    break;
                case 's':
                    System.out.println("Starting sell stock process:");
                    Stock stockSell = collectStockInfo();
                    try {
                        account.sellStock(stockSell);
                    } catch (StockException se) {
                        se.printStackTrace();
                    }
                    printAccountSummary(account);
                    break;
                case 'b':
                    System.out.println("Starting buy stock process:");
                    Stock stock = collectStockInfo();
                    try {
                        account.buyStock(stock);
                    } catch (StockException se) {
                        se.printStackTrace();
                    }
                    System.out.println();
                    printAccountSummary(account);
                    break;
                default:
                    System.out.println("invalid entry.");
                    break;
            }
        }
        System.out.println("exiting...");
        printAccountSummary(account);
    }


    private static StockAccount collectAccountInfo() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your first and last name");
        StockAccount account = new StockAccount(scan.nextLine());
        System.out.println("Hello " + account.getFullName());
        System.out.println("Enter your initial balance of your stock account");
        account.setBalance(scan.nextDouble());
        return account;
    }

    private static void printAccountSummary(StockAccount account) {
            System.out.println(("Account name: " + account.getFullName()));
            System.out.println("your balance is : $" + account.getBalance());
            Collection<Stock> stockRef =  account.getStock();
            if( stockRef.isEmpty()){
                System.out.println("You currently don't own any stocks" );
            } else {
                for (Object o : stockRef) {
                    System.out.println("You own this stock:" + o.toString());
                }
            }
    }
    private static Stock collectStockInfo() {


        Stock stock = new Stock();
        Scanner scan = new Scanner(System.in);
char action = 'x';

        System.out.println("Enter the stock ticket symbol:");
        stock.setStockSymbol(scan.nextLine());
        System.out.println("Enter the amount of shares in whole numbers:");
        stock.setShares(scan.nextInt());
        System.out.println("Enter the price:");
        stock.setPrice(scan.nextDouble());
        System.out.println("Is this a dividend stock? (y/n)");
        action = scan.next().charAt(0);
        switch(action){
            case 'y':
                System.out.println("enter the dividend amount");
                double dividendAmt = scan.nextDouble();
                DividendStock dividend = new DividendStock(stock.getStockSymbol(),stock.getShares()
                        ,stock.getPrice(),dividendAmt);
                dividend.setDividend(dividendAmt);

                return dividend;
            default:
                System.out.println("You've entered:  " + stock.getStockSymbol() + " :"+stock.getShares() +
                        " shares at $" + stock.getPrice() );
                return stock;
        }

    }
    private static void printIntro() {
        System.out.println("This is my stock tracker");
    }
}
