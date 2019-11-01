package com.eliblaney.proteins;


public enum AminoAcid {
	
	PHE("Phenylalanine", "UUU", "UUC"),
	LEU("Leucine", "UUA", "UUG", "CUU", "CUC", "CUA", "CUG"),
	ILE("Isoleucine", "AUU", "AUC", "AUA"),
	MET("Methionine", "AUG"),
	VAL("Valine", "GUU", "GUC", "GUA", "GUG"),
	SER("Serine", "UCU", "UCC", "UCA", "UCG", "AGU", "AGC"),
	PRO("Proline", "CCU", "CCC", "CCA", "CCG"),
	THR("Threonine", "ACU", "ACC", "ACA", "ACG"),
	ALA("Alanine", "GCU", "GCC", "GCA", "GCG"),
	TYR("Tyrosine", "UAU", "UAC"),
	HIS("Histidine", "CAU", "CAC"),
	GLN("Glutamine", "CAA", "CAG"),
	ASN("Asparagine", "AAU", "AAC"),
	LYS("Lysine", "AAA", "AAG"),
	ASP("Aspartate", "GAU", "GAC"),
	GLU("Glutamate", "GAA", "GAG"),
	CYS("Cysteine", "UGU", "UGC"),
	TRP("Tryptophan", "UGG"),
	ARG("Arginine", "CGU", "CGC", "CGA", "CGG", "AGA", "AGG"),
	GLY("Glycine", "GGU", "GGC", "GGA", "GGG"),
	STOP("STOP", "UAA", "UAG", "UGA");
	
	
	private final String longName;
	private final String[] codes;
	
	private AminoAcid(String longName, String... codes) {
		this.longName = longName;
		this.codes = codes;
	}
	
	@Override
	public String toString() {
		return longName;
	}
	
	public String longName() {
		return longName;
	}
	
	public String shortName() {
		return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
	}
	
	public static AminoAcid encode(Codon c) {
		String code = c.toString();
		for(AminoAcid a : AminoAcid.values()) {
			for(String bases : a.codes) {
				if(bases.equals(code))
					return a;
			}
		}
		return null;
	}
	
}
