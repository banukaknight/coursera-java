
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon) {
        String dna1 = dna.toLowerCase();
        int start = dna1.indexOf(startCodon.toLowerCase());
        if (start == -1) {
            return "";
        }
        int stop = dna1.indexOf(stopCodon.toLowerCase(), start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }else {
            return "";
        }
    }

    public void testSimpleGene() {
        String gene1 = "gggattereeftaaggggg";
        String gene2 = "ggatgereeftaggggg";
        String gene3 = "attereeftagggggg";
        String gene4 = "ggatgaaaaataaggggg";
        String gene5 = "ggatgaaaaaataaggggg";
        String gene6 = "ggATGAAATAAGGggg";
        String[] arrGenes = {gene1,gene2,gene3,gene4,gene5,gene6};
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        for (String gene : arrGenes) {
            System.out.println("dana string: "+ gene);
            String generesult = findSimpleGene(gene, startCodon, stopCodon);
            System.out.println(generesult);
        }
    }
}
