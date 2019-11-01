package com.eliblaney.proteins;

import java.util.List;

public class DNA extends NucleicAcid {
	
	private boolean complementary = false;
	
	public DNA(Base... templateStrand) {
		super(templateStrand);
		for(Base e : bases) {
			if(e.getBase() == 'U')
				throw new IllegalArgumentException("U bases are not valid");
		}
	}
	
	public DNA(String templateStrand) {
		this(Base.fromString(templateStrand));
	}
	
	@Override
	public DNA complementary() {
		List<Base> templateStrand = getStrand();
		int size = templateStrand.size();
		Base[] complementaryStrand = new Base[size];
		for(int i = 0; i < size; i++) {
			Base b = templateStrand.get(size - i - 1);
			complementaryStrand[i] = b.createComplement(true);
		}
		DNA complement = new DNA(complementaryStrand);
		complement.complementary = true;
		return complement;
	}
	
	@Override
	public String toString() {
		if(complementary)
			return "3' - " + getBases() + " - 5'";
		return "5' - " + getBases() + " - 3'";
	}
	
	public RNA createRNA(RNAType type) {
		List<Base> templateStrand = getStrand();
		int size = templateStrand.size();
		Base[] complementaryStrand = new Base[size];
		for(int i = 0; i < size; i++) {
			Base b = templateStrand.get(size - i - 1);
			complementaryStrand[i] = b.createComplement(false);
		}
		return new RNA(type, complementaryStrand);
	}

	@Override
	public DNA mutate(long point, long frameshift) {
		List<Base> strand = getStrand();
		for(int i = 0; i < strand.size(); i++) {
			if(point != 0 && Math.random() < (double) 1 / point) {
				strand.remove(i);
				strand.add(i, Base.random(true));
			}
			if(frameshift != 0 && Math.random() < (double) 1 / frameshift) {
				if(Math.random() < 0.5)
					strand.remove(i);
				else
					strand.add(i, Base.random(true));
			}
		}
		Base[] stranda = new Base[strand.size()];
		return new DNA(strand.toArray(stranda));
	}

	public static DNA random(int length) {
		Base[] bases = new Base[length];
		for(int i = 0; i < length; i ++)
			bases[i] = Base.random(true);
		return new DNA(bases);
	}
	
}
