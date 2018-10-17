package org.opencps.api.errors;

public class OpenCPSNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7758296506673147856L;
	public final static String NOT_FOUND = " Not found!.";
	
	public OpenCPSNotFoundException(String exception) {
		super(exception + NOT_FOUND);
	}

}
