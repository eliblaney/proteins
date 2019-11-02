package com.eliblaney.proteins;

import java.util.ArrayList;
import java.util.List;

public class RNA extends NucleicAcid {
	
	private RNAType type;
	
	public RNA(RNAType type, Base... bases) {
		super(bases);
		for(Base e : bases) {
			if(e.getBase() == 'T')
				throw new IllegalArgumentException("T bases are not valid");
		}
		this.type = type;
	}
	
	public RNA(RNAType type, String bases) {
		this(type, Base.fromString(bases));
	}
	
	public RNAType getType() {
		return type;
	}
	
	public void setType(RNAType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "5' - " + getBases() + " - 3'";
	}
	
	public int findStartCodon() {
		return findStartCodon(0);
	}
	
	public int findStartCodon(int offset) {
		String str = this.getBases().substring(offset);
		return str.indexOf("AUG");
	}
	
	public Codon[] toCodons() throws NonsenseException {
		return toCodons(0);
	}
	
	public Codon[] toCodons(int start) throws NonsenseException {
		int startIndex = findStartCodon(start);
		if(startIndex == -1)
			throw new NonsenseException("No start codon found");
		String str = getBases().substring(startIndex);
		ArrayList<Codon> codons = new ArrayList<>();
		boolean stop = false;
		for(int i = 0; i < (str.length() - (str.length() % 3)) / 3; i++) {
			char[] c = str.substring(i * 3, i * 3 + 3).toCharArray();
			Codon codon = new Codon(new Base(c[0]), new Base(c[1]), new Base(c[2]));
			codons.add(codon);
			if(codon.toAminoAcid() == AminoAcid.STOP) {
				stop = true;
				break;
			}
		}
		if(!stop)
			throw new NonsenseException("No stop codon found");
		Codon[] ca = new Codon[codons.size()];
		return codons.toArray(ca);
	}
	
	/*public String highlightCodons(int offset) {
		int startIndex = findStartCodon(offset);
		//String str = 
		boolean stop = false;
		for(int i = 0; i < (str.length() - (str.length() % 3)) / 3; i++) {
			char[] c = str.substring(i * 3, i * 3 + 3).toCharArray();
			Codon codon = new Codon(new Base(c[0]), new Base(c[1]), new Base(c[2]));
	}*/
	
	public AminoAcid[] toAminoAcids() throws NonsenseException {
		return toAminoAcids(0);
	}
	
	public AminoAcid[] toAminoAcids(int start) throws NonsenseException {
		Codon[] codons = toCodons(start);
		AminoAcid[] acids = new AminoAcid[codons.length - 1];
		for(int i = 0; i < codons.length - 1; i++)
			acids[i] = codons[i].toAminoAcid();
		return acids;
	}
	
	public DNA createDNA() {
		int size = bases.size();
		Base[] complementaryStrand = new Base[size];
		for(int i = 0; i < size; i++) {
			Base b = bases.get(i);
			complementaryStrand[i] = b.createComplement(true);
		}
		return new DNA(complementaryStrand);
	}
	
	@Override
	public RNA complementary() {
		List<Base> strand = getStrand();
		int size = strand.size();
		Base[] complementaryStrand = new Base[size];
		for(int i = 0; i < size; i++) {
			Base b = strand.get(size - i - 1);
			complementaryStrand[i] = b.createComplement(false);
		}
		return new RNA(type, complementaryStrand);
	}

	@Override
	public NucleicAcid mutate(long point, long frameshift) {
		List<Base> strand = getStrand();
		for(int i = 0; i < strand.size(); i++) {
			if(point != 0 && Math.random() < (double) 1 / point) {
				strand.remove(i);
				strand.add(i, Base.random(false));
			}
			if(frameshift != 0 && Math.random() < (double) 1 / frameshift) {
				if(Math.random() < 0.5)
					strand.remove(i);
				else
					strand.add(i, Base.random(false));
			}
		}
		Base[] stranda = new Base[strand.size()];
		return new RNA(type, strand.toArray(stranda));
	}
	
	public boolean isEquivalent(RNA rna) throws NonsenseException {
		AminoAcid[] aa = this.toAminoAcids();
		AminoAcid[] ab = rna.toAminoAcids();
		if(aa.length != ab.length)
			return false;
		for(int i = 0; i < aa.length; i++) {
			if(!aa[i].equals(ab[i]))
				return false;
		}
		return true;
	}

	public static RNA random(RNAType type, int length) {
		Base[] bases = new Base[length];
		for(int i = 0; i < length; i ++)
			bases[i] = Base.random(false);
		return new RNA(type, bases);
	}
	
}
