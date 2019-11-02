package com.eliblaney.proteins;


public class Codon {
	
	private final Base[] c;
	
	public Codon(Base... bases) {
		if(bases.length != 3)
			throw new IllegalArgumentException("Codon must be 3 bases in length");
		for(char c : Base.toString(bases).toCharArray()) {
			if(c != 'A' && c != 'U' && c != 'G' && c != 'C')
				throw new IllegalArgumentException("Base received was " + c + ". Expected AUGC");
		}
		this.c = bases;
	}
	
	@Override
	public String toString() {
		String str = "";
		for(Base b : c)
			str += b.getBase();
		return str;
	}
	
	public AminoAcid toAminoAcid() {
		return AminoAcid.encode(this);
	}
	
}
