package com.eliblaney.proteins;

import java.util.ArrayList;

public class Base {
	
	private final char base;
	
	public Base(char c) {
		c = Character.toUpperCase(c);
		if(c != 'A' && c != 'T' && c != 'U' && c != 'G' && c != 'C')
			throw new IllegalArgumentException("Character received was " + c + ". Expected AUGC");
		this.base = c;
	}
	
	@Override
	public String toString() {
		return "" + base;
	}
	
	public static String toString(Base... bases) {
		String str = "";
		for(Base b : bases)
			str += b.toString();
		return str;
	}
	
	public char getBase() {
		return base;
	}
	
	public static Base[] fromString(String bases) {
		ArrayList<Base> b = new ArrayList<Base>();
		for(char c : bases.toCharArray())
			b.add(new Base(c));
		Base[] ba = new Base[b.size()];
		return b.toArray(ba);
	}
	
	public Base createComplement(boolean dna) {
		if(dna && base == 'A')
			return new Base('T');
		if(!dna && base == 'A')
			return new Base('U');
		if(base == 'U')
			return new Base('A');
		if(base == 'T')
			return new Base('A');
		if(base == 'C')
			return new Base('G');
		if(base == 'G')
			return new Base('C');
		return null;
	}
	
	public boolean equals(Base b) {
		return this.getBase() == b.getBase();
	}
	
	public static Base random(boolean dna) {
		char[] choices = "AUGC".toCharArray();
		if(dna)
			choices = "ATGC".toCharArray();
		return new Base(choices[(int) (Math.random() * choices.length)]);
	}
}
