package com.eliblaney.proteins;

public class Proteins {
	
	public static void main(String... args) throws NonsenseException {
		// Example usage

		DNA dna = new DNA("ATTAGCTATGTATATTTCGGAGGATCATG");
		RNA rna = dna.createRNA(RNAType.mRNA);
		
		display(dna, rna);
		System.out.println("--------------------------------------------------------\n");
		
		DNA dna2 = dna.mutate(10, 50);
		RNA rna2 = dna2.createRNA(RNAType.mRNA);

		if(display(dna2, rna2)) {
			System.out.println("RNA Equivalent? " + rna.isEquivalent(rna2));
			System.out.println("DNA Equal? " + dna.equals(dna2));
		}
		
	}
	
	private static boolean display(DNA dna, RNA rna) {
		System.out.println("tDNA: " + dna);
		System.out.println("cDNA: " + dna.complementary());
		System.out.println();
		System.out.println(rna.getType().name() + ": " + rna);
		System.out.println();

		try {
			String str = "";
			Codon[] codons = rna.toCodons();
			for(Codon c : codons)
				str += " - " + c.toString();
			System.out.println(str.substring(3));
			
			str = "";
			AminoAcid[] acids = rna.toAminoAcids();
			for(AminoAcid a : acids)
				str += " - " + a.shortName();
			System.out.println(str.substring(3));
			
			System.out.println();
			return true;
		} catch(NonsenseException e)  {
			System.out.println("Could not convert to amino acids: " + e.getMessage());
			return false;
		}
	}
}
