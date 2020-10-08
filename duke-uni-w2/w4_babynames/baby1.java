import edu.duke.*;
import java.io.*; //File
import org.apache.commons.csv.*; //CSVParser & CSVRecord
import java.util.Scanner;

public class baby1 {
    public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

    public void totalBirths (FileResource fr) {
    	int totalBirths = 0;
    	int totalBoys = 0;
    	int totalGirls = 0;
    	int totalNames = 0;
    	int totalGNames = 0;
    	int totalBNames = 0;
    	
    	for (CSVRecord rec : fr.getCSVParser(false)) {
    		int numBorn = Integer.parseInt(rec.get(2));
    		totalBirths += numBorn;
    		totalNames++;
    		if (rec.get(1).equals("M")) {
    			totalBoys += numBorn;
    			totalBNames++;
    		}
    		else {
    			totalGirls += numBorn;
    			totalGNames++;
    		}
    	}
    	System.out.println("total births = " + totalBirths);
    	System.out.println("female girls = " + totalGirls);
    	System.out.println("male boys = " + totalBoys);
    	System.out.println("Total male names = " + totalBNames);
    	System.out.println("Total female names = " + totalGNames);
    	System.out.println("Total baby names = " + totalNames);
    	
    	
    }
    
    public void TESTTotalBirths () {
    	//FileResource fr = new FileResource();
    	FileResource fr = new FileResource();
    	totalBirths(fr);
    }
    
    public int getRank (int year, String name, String gender){
        //String fname = ".//testing//yob"+ year +"short.csv";
        String fname = ".//us_babynames_by_year//yob"+ year +".csv";
        
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        
        int rankCount = 0;
        
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                rankCount++;
                if(rec.get(0).equals(name)) {
                    return rankCount;
                   }
               }
           }
            
        return -1;
    }
    
    public void TESTgetRank_getName_whatIsNameInYear(){
        System.out.println("emily-rank: "+ getRank(1960,"Emily","F") );
        System.out.println("frank_m-rank: "+ getRank(1971,"Frank","M") );
        //System.out.println("2012-Mason-F-rank: "+ getRank(2012,"Mason","F") );
        
        System.out.println("1980-350: "+getName(1980,350,"F") );
        System.out.println("1982-450: "+getName(1982,450,"M") );
        
        //System.out.println("2014Isabella-rank: "+getName(2014,3, "M" ));
        //System.out.println("2012nonexist rank: "+getName(2014,7, "M" ));
        
        whatIsNameInYear("Susan",1972,2014,"F");
        whatIsNameInYear("Owen",1974,2014,"M");
        
        //whatIsNameInYear("Isabella",2012,2013,"F");
        //whatIsNameInYear("Mason",2012,2014,"M");
    }
    
    public void TESTyearOfHighestRank_getAverageRank_(){    
        //System.out.println("Mason highest rank :"+ yearOfHighestRank("Mich","M") );
        //System.out.println("Mason highest rank :"+ yearOfHighestRank("Genevieve","F") );
        //System.out.println("Isabella highest rank :"+ yearOfHighestRank("Isabella","F") );
        //System.out.println("Sammi highest rank :"+ yearOfHighestRank("Sammi","F") );
        
        //System.out.println("m avg rank :"+ getAverageRank("Robert","M") );
        //System.out.println("f avg rank : "+ getAverageRank("Susan","F") );
        
       System.out.println("F higher count:"+getTotalBirthsRankedHigher(1990,"Emily","F") );
       System.out.println("m higher count:"+getTotalBirthsRankedHigher(1990,"Drew","M") );
       }
       
    public String getName(int year, int rank, String gender){
        //String fname = ".//testing//yob"+ year +"short.csv";
        String fname = ".//us_babynames_by_year//yob"+ year +".csv";
        
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        
        int rankCount = 0;
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                rankCount++;
                if(rankCount==rank) {
                    return rec.get(0);
                   }
               }
           }
        return "NO NAME";        
       }

    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rankName = getRank(year, name, gender);
        String newName = getName(newYear, rankName, gender);
        String genddd = gender.equals("M") ? " he " : " she ";
        if(newName.equals("NO NAME")){
            System.out.println("No such name found");
        }else{
            System.out.println(name+" born in "+year+" would be "+newName+" if"+genddd +"was born in "+newYear);
        }
        
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int hRank = 9999;
        int hYear = -1;
        for (File f : dr.selectedFiles()) {
            int year = Integer.parseInt ( ( f.getName() ).substring(3,7) );
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            int thisRank = getRank(year, name, gender);
            if(thisRank<hRank && thisRank!= -1){
                hRank = thisRank;
                hYear = year;
            }
        }
        
        return hYear;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int count = 0;
        double totRank = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            int year = Integer.parseInt ( ( f.getName() ).substring(3,7) );
            int thisRank = getRank(year, name, gender);
            if(thisRank>0){
                totRank+=thisRank;
                count++;
            }
            
        }
        if(count == 0){ return -1.0; }
        else{ return totRank/count; }
        
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        //String fname = ".//testing//yob"+ year +"short.csv";
        String fname = ".//us_babynames_by_year//yob"+ year +".csv";
        
        int nameRank = getRank(year,name,gender); //get rank from func
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        if (nameRank==-1){return 0;}
        
        int totalBirths = 0;
        int rankCount = 0;
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                rankCount++;
                if (rankCount<nameRank){
                    totalBirths += Integer.parseInt(rec.get(2));
                }else{
                    break;
                }
            }
        }
        return totalBirths;
    }
}
