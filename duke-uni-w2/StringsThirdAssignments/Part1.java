import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex,String stopCodon){
        int stop = dna.indexOf(stopCodon,startIndex);
        if (stop == -1) {
            return dna.length();
        }else{
            if ((stop - startIndex) % 3 == 0) {
                return stop;
            }else {
                return dna.length();
            }
        }
        
    }
    
    public String findGene(String dna){
        String dna1 = dna.toLowerCase();
        int start = dna1.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int taaStop = findStopCodon(dna1,start,"taa");
        int tagStop = findStopCodon(dna1,start,"tag");
        int tgaStop = findStopCodon(dna1,start,"tga");
        
        if(taaStop==tagStop && tgaStop==dna.length() ){
           return ""; 
        }
        int minStop = Math.min(Math.min(taaStop, tagStop), tgaStop);
        return dna.substring(start, minStop+3);
        
    }
    
    public void testFindGene(){
        String[] arrStr = {"AATGCTAACTAGCTGACTAAT","AATT","AATGCTAATATAGGACTAAT","AATGCTATAAAGCTGATGAAT"};
        for(String str: arrStr){
            System.out.println("String: "+ str);
            System.out.println("smallGene: "+ findGene(str) );
        }
        System.out.println("---");
        
        printAllGenes("acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaa");
        System.out.println("---");
                printAllGenes("ctgaggaaatgttctagaaatgaaacatgttctaataatacagtaatctctcaggatctt");
        System.out.println("---");
                printAllGenes("gttgaattaaccaaaaatattcccatggaaaagaatcaagatgtatgtgctttaaatgaa");
        System.out.println("---");
                printAllGenes("AAACATTTACCCACCTATTAAAAGTATAGGAGATAGAAATTGAACTCAGG");
        System.out.println("---");
                printAllGenes("AGATACCCCACTATGCCTAGCCCTAAACCTCAATGGCTGCTAACAAAGCC");
        System.out.println("---");
        
        
        
        
    }
    
    public StorageResource getAllGenes(String dna){
        String dna1 = dna.toLowerCase();
             StorageResource store = new StorageResource();   
        int fromx = 0;
        while(true){
            int start = dna1.indexOf("atg",fromx);
            if (start == -1) {
                return store;
            }
            int taaStop = findStopCodon(dna1,start,"taa");
            if(taaStop != dna.length()){store.add(dna.substring(start, taaStop+3) );}
            int tagStop = findStopCodon(dna1,start,"tag");
            if(tagStop != dna.length()){store.add(dna.substring(start, tagStop+3) );}
            int tgaStop = findStopCodon(dna1,start,"tga");
            if(tgaStop != dna.length()){store.add(dna.substring(start, tgaStop+3) );}
            fromx = Math.min(taaStop, Math.min(tagStop, tgaStop)) + 1;
        
        }
    }
    
    public void printAllGenes(String dna){
        String dna1 = dna.toLowerCase();
        
        int fromx = 0;
        while(true){
            int start = dna1.indexOf("atg",fromx);
            if (start == -1) {
                break;
            }
            int taaStop = findStopCodon(dna1,start,"taa");
            if(taaStop != dna.length()){System.out.println(dna.substring(start, taaStop+3) );}
            int tagStop = findStopCodon(dna1,start,"tag");
            if(tagStop != dna.length()){System.out.println(dna.substring(start, tagStop+3) );}
            int tgaStop = findStopCodon(dna1,start,"tga");
            if(tgaStop != dna.length()){System.out.println(dna.substring(start, tgaStop+3) );}
            fromx = start+1;
        
        }
    }
    
    public float cgRatio(String dna){
        String dna1 = dna.toLowerCase();
        float count = 0;
        for(int i = 0; i < dna1.length(); i++) {    
            if(dna1.charAt(i) == 'c' || dna1.charAt(i) == 'g')    
                count+=1.0;    
        }  
        
        return count/dna1.length() ;
    }
    
    public void testcgRatio(){
        System.out.println("cgRatio: "+ cgRatio("ATGCCATAG") );
    }
    
    public void processGenes(StorageResource sr){
        int countAll = 0;
        int count9 = 0;
        int count60 = 0;
        int countCG = 0;
        int longest = 0;
    for (String s : sr.data()) {
        countAll++;
        if (s.length() > 9){ System.out.println("LongerThan9: "+s); count9++;}
        if (s.length() > 60){ System.out.println("LongerThan60: "+s); count60++;}
        if (cgRatio(s) > 0.35){ System.out.println("C-G-Ratio: "+s); countCG++; }
        if (s.length() > longest){ longest = s.length(); }
    }
    System.out.println("Count All: "+countAll);
     System.out.println("Count LongerThan9: "+count9);
     System.out.println("Count LongerThan60: "+count60);
      System.out.println("C-G-Ratio over 0.35: "+countCG);
       System.out.println("Longest length: "+longest);
        
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource store = getAllGenes(dna);
     processGenes(store);
      System.out.println("-----------");
//      StorageResource store2 = new StorageResource();
// for (String s : fr.words()) {
 //    store2.add(s);
 //}
// processGenes(store2);
 
    }
    
}
