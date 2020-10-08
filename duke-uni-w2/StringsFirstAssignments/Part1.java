
public class Part1 {
    public String findSimpleGene (String dna) {
        int start = dna.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("taa", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }

    public void testSimpleGene() {
        String gene1 = "gggattereeftaaggggg";
        String gene2 = "ggatgereeftaggggg";
        String gene3 = "attereeftagggggg";
        String gene4 = "ggatgaaaaataaggggg";
        String gene5 = "ggatgaaaaaataaggggg";
        String[] arrGenes = {gene1,gene2,gene3,gene4,gene5};
        for (String gene : arrGenes) {
            System.out.println("dana string: "+ gene);
            String generesult = findSimpleGene(gene);
            System.out.println(generesult);
        }
    }
}
