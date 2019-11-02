package com.eliblaney.proteins;


public class NonsenseException extends Exception {

	private static final long serialVersionUID = 2384131797880739137L;
	
	public NonsenseException() {
		super();
	}
	
	public NonsenseException(String e) {
		super(e);
	}
}
