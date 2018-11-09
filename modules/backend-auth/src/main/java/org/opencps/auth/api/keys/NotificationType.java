package org.opencps.auth.api.keys;

public class NotificationType {
	
	/*
	03/01/2018-ThanhNv: update notificationType
	
	public static final String DOSSIER_01 = "DOSSIER-01";
	public static final String DOSSIER_02 = "DOSSIER-02";
	public static final String DOSSIER_03 = "DOSSIER-03";
	public static final String DOSSIER_04 = "DOSSIER-04";
	public static final String DOSSIER_05 = "DOSSIER-05";
	public static final String DOSSIER_06 = "DOSSIER-06";
	public static final String DOSSIER_07 = "DOSSIER-07";
	public static final String DOSSIER_08 = "DOSSIER-08";
	public static final String DOSSIER_09 = "DOSSIER-09";
	public static final String DOSSIER_10 = "DOSSIER-10";
	public static final String APPLICANT_01 = "APPLICANT-01";
	
	*/
	
	public static final String DOSSIER_01 = "DOSSIER-01";
	public static final String DOSSIER_02 = "DOSSIER-02";
	public static final String DOSSIER_03 = "DOSSIER-03";
	public static final String DOSSIER_04 = "DOSSIER-04";
	public static final String DOSSIER_05 = "DOSSIER-05";
	public static final String DOSSIER_06 = "DOSSIER-06";
	public static final String DOSSIER_07 = "DOSSIER-07";
	public static final String DOSSIER_08 = "DOSSIER-08";
	public static final String DOSSIER_09 = "DOSSIER-09";
	public static final String DOSSIER_10 = "DOSSIER-10";
	public static final String APPLICANT_01 = "APPLICANT-01";
	public static final String APPLICANT_02 = "APPLICANT-02";
	public static final String APPLICANT_03 = "APPLICANT-03";
	public static final String APPLICANT_04 = "APPLICANT-04";
	public static final String APPLICANT_05 = "APPLICANT-05";
	public static final String REGISTRATION_01 = "REGISTRATION-01";
	public static final String REGISTRATION_02 = "REGISTRATION-02";
	public static final String USER_01 = "USER-01";
	public static final String USER_02 = "USER-02";
	public static final String USER_03 = "USER-03";
	public static final String USER_04 = "USER-04";
	public static final String USER_05 = "USER-05";
	//Value
	public static final String VALUE_DOSSIER_01 = "Y\u00EAu c\u1EA7u b\u1ED5 sung h\u1ED3 s\u01A1";
	public static final String VALUE_DOSSIER_02 = "Y\u00EAu c\u1EA7u thanh to\u00E1n";
	public static final String VALUE_DOSSIER_03 = "Chuy\u1EC3n ti\u1EBFp ch\u1EDD x\u1EED l\u00FD h\u1ED3 s\u01A1";
	public static final String VALUE_DOSSIER_04 = "H\u1ED3 s\u01A1 \u0111\u00E3 \u0111\u01B0\u1EE3c b\u1ED5 sung";
	public static final String VALUE_DOSSIER_05 = "H\u1ED3 s\u01A1 c\u00F3 y\u00EAu c\u1EA7u h\u1EE7y";
	public static final String VALUE_DOSSIER_06 = "H\u1ED3 s\u01A1 c\u00F3 y\u00EAu c\u1EA7u ch\u1EC9nh s\u1EEDa k\u1EBFt qu\u1EA3";
	public static final String VALUE_DOSSIER_07 = "H\u1ED3 s\u01A1 b\u00E1o \u0111\u00E3 \u0111\u01B0\u1EE3c thanh to\u00E1n";
	public static final String VALUE_DOSSIER_08 = "X\u00E1c nh\u1EADn h\u1ED3 \u0111\u00E3 \u0111\u01B0\u1EE3c thanh to\u00E1n";
	public static final String VALUE_DOSSIER_09 = "C\u1EA3nh b\u00E1o qu\u00E1 h\u1EA1n x\u1EED l\u00FD h\u1ED3 s\u01A1";
	public static final String VALUE_DOSSIER_10 = "H\u1ED3 s\u01A1 \u0111\u00E3 c\u00F3 k\u1EBFt qu\u1EA3 x\u1EED l\u00FD";
	public static final String VALUE_APPLICANT_01 = "Th\u00F4ng b\u00E1o m\u1EDF m\u1EDBi t\u00E0i kho\u1EA3n ng\u01B0\u1EDDi l\u00E0m th\u1EE7 t\u1EE5c";
	public static final String VALUE_APPLICANT_02 = "Th\u00F4ng b\u00E1o \u0111\u00E3 ti\u1EBFp nh\u1EADn h\u1ED3 s\u01A1 t\u1EA1i b\u1ED9 ph\u1EADn m\u1ED9t c\u1EEDa \u0111i\u1EC7n t\u1EED";
	public static final String VALUE_APPLICANT_03 = "Th\u00F4ng b\u00E1o y\u00EAu c\u1EA7u ch\u1EC9nh s\u1EEDa h\u1ED3 s\u01A1 t\u1EA1i b\u1ED9 ph\u1EADn m\u1ED9t c\u1EEDa";
	public static final String VALUE_APPLICANT_04 = "Th\u00F4ng b\u00E1o l\u1EA5y k\u1EBFt qu\u1EA3 h\u1ED3 s\u01A1 t\u1EA1i b\u1ED9 ph\u1EADn m\u1ED9t c\u1EEDa";
	public static final String VALUE_APPLICANT_05 = "Th\u00F4ng b\u00E1o \u0111\u00E3 ho\u00E0n th\u00E0nh tr\u1EA3 k\u1EBFt qu\u1EA3 h\u1ED3 s\u01A1";
	public static final String VALUE_REGISTRATION_01 = "C\u00F3 \u0111\u0103ng k\u00ED h\u1ED3 s\u01A1 th\u01B0\u01A1ng nh\u00E2n m\u1EDBi";
	public static final String VALUE_REGISTRATION_02 = "Thay \u0111\u1ED5i th\u00F4ng tin \u0111\u0103ng k\u00ED h\u1ED3 s\u01A1 th\u01B0\u01A1ng nh\u00E2n";
	
}
