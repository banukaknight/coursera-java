
public class Part3 {
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
            System.out.println( countGenes("ctgaggaaatgttctagaaatgaaacatgttctaataatacagtaatctctcaggatctt"));
             printAllGenes("ctgaggaaatgttctagaaatgaaacatgttctaataatacagtaatctctcaggatctt");
        System.out.println("---");
         System.out.println( countGenes("gttgaattaaccaaaaatattcccatggaaaagaatcaagatgtatgtgctttaaatgaa"));
                printAllGenes("gttgaattaaccaaaaatattcccatggaaaagaatcaagatgtatgtgctttaaatgaa");
        System.out.println("---");
        System.out.println( countGenes("AAACATTTACCCACCTATTAAAAGTATAGGAGATAGAAATTGAACTCAGG"));
                printAllGenes("AAACATTTACCCACCTATTAAAAGTATAGGAGATAGAAATTGAACTCAGG");
        System.out.println("---");
        System.out.println( countGenes("ATGTAAGATGCCCTAGT"));
                printAllGenes("ATGTAAGATGCCCTAGT");
        System.out.println("---");
 
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
    
    public int countGenes(String dna){
        String dna1 = dna.toLowerCase();
           int fromx = 0;
           int countgee = 0;
        while(true){
            int start = dna1.indexOf("atg",fromx);
            if (start == -1) {
                return countgee;
            }
            int taaStop = findStopCodon(dna1,start,"taa");
            if(taaStop != dna.length()){countgee+=1;}
            int tagStop = findStopCodon(dna1,start,"tag");
            if(tagStop != dna.length()){countgee+=1;}
            int tgaStop = findStopCodon(dna1,start,"tga");
            if(tgaStop != dna.length()){countgee+=1;}
            fromx = start+1;
        
        }   
        
        
    }
    
    
    
}
