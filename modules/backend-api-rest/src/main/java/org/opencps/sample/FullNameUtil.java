package org.opencps.sample;

public class FullNameUtil {

	public FullNameUtil(String firstName, String middleName, String lastName) {
		_firstName = firstName;
		_middleName = middleName;
		_lastName = lastName;
	}

	public int fullNameLength() {
		return _firstName.length() + _middleName.length() + _lastName.length();
	}

	public String getMiddleInitial() {
		return _middleName.charAt(0) + ".";
	}

	@Override
	public String toString() {
		return _firstName + " " + getMiddleInitial() + " " + _lastName;
	}

	private String _firstName;
	private String _middleName;
	private String _lastName;

}
