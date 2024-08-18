package ninja.utils;

import java.util.Date;

public class Utilities {

    public static String generateEmailwithTimeStamp(){
        Date date = new Date();
        String generateTimeStamp=  date.toString().replace(" ", "_").replace(":", "_");
       return  "lan123"+generateTimeStamp+"@gmail.com";
    }
}
