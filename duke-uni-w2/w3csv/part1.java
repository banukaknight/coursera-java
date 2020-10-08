import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.Arrays;
import java.util.Scanner;

public class part1 {
  
    
    public void tester(){
          FileResource fr = new FileResource();
          CSVParser parser = fr.getCSVParser();
          Scanner myObj = new Scanner(System.in);
          //printAll(parser);
          parser = fr.getCSVParser(); //reset and get new parser
          String st1 = countryInfo(parser,"Nauru");
          System.out.println(st1);
          parser = fr.getCSVParser(); //reset and get new parser
          String st2 = countryInfo(parser,"Malawi");
         
          System.out.println(st2);
          System.out.println("---");
          parser = fr.getCSVParser(); //reset and get new parser
          listExportersTwoProducts(parser,"cotton","flowers");
          System.out.println("---");
 
          System.out.println("---");
          parser = fr.getCSVParser(); //reset and get new parser
          int int1 = numberOfExporters(parser,"cocoa");
          System.out.println("cocoa count: "+ int1);
           System.out.println("++---");
          parser = fr.getCSVParser(); //reset and get new parser
          bigExporters(parser,"$999,999,999,999");
    }
    
    public void printAll(CSVParser parser){
        for (CSVRecord csvRecord : parser) {
            // Accessing Values by Column Index
            String Country = csvRecord.get(0);
            String Exports[] = csvRecord.get(1).split(", ");
            String Value = csvRecord.get(2);
            
            System.out.println("Country :"+ Country+ "\t_Exports: "+ Arrays.toString(Exports));
        }
        
    }
    
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord csvRecord : parser) {
            if(country.equals(csvRecord.get(0))){
                String st1 = csvRecord.get(0)+" :"+ csvRecord.get(1)+ ": "+csvRecord.get(2);
                return st1;
            }
        }    
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord csvRecord : parser) {
            //String Exports[] = csvRecord.get(1).split(", ");
            if( csvRecord.get(1).contains(exportItem1) && csvRecord.get(1).contains(exportItem2) ){ 
                System.out.println( csvRecord.get(0) );
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord csvRecord : parser) {
            //String Exports[] = csvRecord.get(1).split(", ");
            //if( Arrays.asList(Exports).contains(exportItem) ){
            if( csvRecord.get(1).contains(exportItem) ){
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord csvRecord : parser) {
            if( amount.length() < csvRecord.get(2).length() ){
                 System.out.println( csvRecord.get(0) + csvRecord.get(2) );
            }
        }
    }
}
