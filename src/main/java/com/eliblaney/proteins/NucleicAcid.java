package com.eliblaney.proteins;

import java.util.LinkedList;
import java.util.List;

public abstract class NucleicAcid {
	
	protected final List<Base> bases = new LinkedList<>();
	
	public NucleicAcid(Base... bases) {
		for(Base e : bases)
			this.bases.add(e);
	}
	
	public String getBases() {
		String str = "";
		for(Base b : bases)
			str += b.getBase();
		return str;
	}
	
	public abstract NucleicAcid complementary();
	
	public abstract NucleicAcid mutate(long point, long frameshift);
	
	public List<Base> getStrand() {
		LinkedList<Base> strand = new LinkedList<>();
		for(Base b : bases)
			strand.add(b);
		return strand;
	}
	
	@Override
	public String toString() {
		return "5' - " + getBases() + " - 3'";
	}
	
	public boolean equals(NucleicAcid a) {
		if(!this.getClass().getName().equals(a.getClass().getName()))
			return false;
		List<Base> sa = this.getStrand();
		List<Base> sb = a.getStrand();
		if(sa.size() != sb.size())
			return false;
		for(int i = 0; i < sa.size(); i++) {
			if(!sa.get(i).equals(sb.get(i)))
				return false;
		}
		return true;
	}
	
}
