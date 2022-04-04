package com.stock;

import java.io.*;

public class StockFileManager {
    private static final String filePath = "C:/Projects/stocks.dat";

    public static void storeAccountInfo(StockAccount stockAccount) throws StockException {
        File f = new File(filePath);
        try {
            if(f.exists()){
                f.delete();
                System.out.println("Deleting existing file...");
            }
            OutputStream os = new FileOutputStream(f);
           try( ObjectOutputStream output = new ObjectOutputStream(os)) {
               output.writeObject(stockAccount);
           }
        }
        catch(IOException io) {
            System.out.println("IO Exception");
            io.printStackTrace();
        }
    }

    public static StockAccount getStoredAccount() throws StockException {
        File f = new File(filePath);

        if(f.exists()){
            try( ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(f)
            )) {
        Object read = null;
        read = input.readObject();
               if(read instanceof  StockAccount){
                   return  (StockAccount) read;
               } else {
                   return null;
               }
            } catch (EOFException eof){
                System.out.println("An eof Exception occurred");
                eof.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
                System.out.println("An IO Exception occurred in getStoredAccount");
            } catch (ClassNotFoundException e) {
                System.out.println("An Class not found Exception occurred");
                e.printStackTrace();
            } catch (NullPointerException npe) {
                System.out.println("A Null pointer Exception occurred");
                npe.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }
}
