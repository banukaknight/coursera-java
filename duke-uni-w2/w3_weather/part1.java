import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;
import java.util.Scanner;

public class part1 {

    
    public void testcoldestHourInFile(){
      FileResource fr = new FileResource();
      CSVParser parser = fr.getCSVParser();
      
      CSVRecord coldhr = coldestHourInFile(parser);
      System.out.println("Coldest hour: "+ coldhr.get("TimeEST") +" temp: "+  coldhr.get("TemperatureF")  );
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestrec = null;
        String faultytemp = "-9999";
        double lowesttemp = 999.9;
        for (CSVRecord record : parser) {
            double thistemp = Double.parseDouble(record.get("TemperatureF"));
            if(thistemp<lowesttemp && !faultytemp.equals(record.get("TemperatureF") )){
                lowesttemp = Double.parseDouble(record.get("TemperatureF"));
                coldestrec = record;
            }
            
        }
        return coldestrec;
    }
    
    public void testFileWithColdestTemperature(){
        String coldetstf = fileWithColdestTemperature(); //get file name of coldest
        FileResource fr = new FileResource(".//2013//"+ coldetstf);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestrec = coldestHourInFile(parser);
        
        System.out.println("Coldest day was in file "+ coldetstf);
        System.out.println("Coldest temp on tha day was "+ coldestrec.get("TemperatureF") );
        System.out.println("All the temp on the coldest day were: ");
        parser = fr.getCSVParser(); //reset parser
        for (CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + ": "+ record.get("TemperatureF") );
        }
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestrec = null;
        double lowesttemp = 999.9;
        File coldfile = null;
        
        for (File f : dr.selectedFiles()) {
            //process each file
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord temprec = coldestHourInFile(parser);
            double thistemp = Double.parseDouble(temprec.get("TemperatureF"));
            if(thistemp<lowesttemp && !"-9999".equals(temprec.get("TemperatureF") )){
                lowesttemp = Double.parseDouble(temprec.get("TemperatureF"));
                coldestrec = temprec;
                coldfile = f;
            }
            
        }
        System.out.println("colderst file"+ coldfile.toString() );
        return coldfile.getName();
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was "+ csv.get("Humidity") + " at "+ csv.get("DateUTC") );
        
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser){
        CSVRecord humidityrec = null;
        double lowesthum = 999.9;
        
    for (CSVRecord record : parser) {
            if(!"N/A".equals(record.get("Humidity"))){
                double thishum = Double.parseDouble(record.get("Humidity"));
                if(thishum<lowesthum){
                    lowesthum = thishum;
                    humidityrec = record;
                }   
        }
        }
        return humidityrec;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord humrec = null;
        double lowesthum = 999.9;
        File humfile = null;
        
        for (File f : dr.selectedFiles()) {
            //process each file
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord temphum = lowestHumidityInFile(parser); //get lowest in each file
            double thishum = Double.parseDouble(temphum.get("Humidity"));
            if( thishum<lowesthum ){
                lowesthum =thishum;
                humrec = temphum;
                humfile = f;
            }
            
        }
        
        return humrec;
        
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowhumrec = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowhumrec.get("Humidity") + " at "+ lowhumrec.get("DateUTC") );
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        int count = 0;
        double totTemp = 0.0;
        for(CSVRecord record: parser){
            if(!"-9999".equals(record.get("TemperatureF") )){
                totTemp += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        return totTemp/count;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgtemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+ avgtemp);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        int count = 0;
        double totTemp = 0.0;
        for(CSVRecord record: parser){
            if(!"-9999".equals(record.get("TemperatureF") )){
                if(!"N/A".equals(record.get("Humidity"))){
                    if(Double.parseDouble(record.get("Humidity")) >= (double)value ) {
                        totTemp += Double.parseDouble(record.get("TemperatureF"));
                        count++;
                    }
                }
            }
        }
        if(count != 0){
        return totTemp/count;
        }else{
         return 999.9;   
        }
      
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter min-humidity to check: ");
        int humid = Integer.parseInt(myObj.nextLine());
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        double avgtemp = averageTemperatureWithHighHumidityInFile(parser, humid);
        if(avgtemp == 999.9){
             System.out.println("No temperatures with that humidity");
            }else{
                System.out.println("Average Temp when high Humidity is "+avgtemp);
            }
            
    }
    
    
}
