package org.opencps.api.controller.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadNumber {

	public static String numberToString(BigDecimal number) {
		String sNumber = number.toString();
		// Tao mot bien tra ve
		String sReturn = "";
		// Tim chieu dai cua chuoi
		int iLen = sNumber.length();
		// Lat nguoc chuoi nay lai
		String sNumber1 = "";
		for (int i = iLen - 1; i >= 0; i--) {
			sNumber1 += sNumber.charAt(i);
		}
		// Tao mot vong lap de doc so
		// Tao mot bien nho vi tri cua sNumber
		int iRe = 0;
		do {
			// Tao mot bien cat tam
			String sCut = "";
			if (iLen > 3) {
				sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
				sReturn = Read(sCut, iRe) + sReturn;
				iRe++;
				iLen -= 3;
			} else {
				sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
				sReturn = Read(sCut, iRe) + sReturn;
				break;
			}
		} while (true);
		if(sReturn.length() > 1){
			sReturn = sReturn.substring(0,1).toUpperCase() + sReturn.substring(1);
		}
		sReturn = sReturn + "đồng";

		// xu ly lan cuoi voi 220 000 tỷ 000 000 000 000 000 HUTTV ADDED 3 OCT
		if(sNumber.length()>12)
		{
			// tren gia tri can xu ly, kiem tra xem don vi TY co = 000 khong
			int begin = sNumber.length()-9;
			String donviTy = sNumber.substring(begin-3, (begin-3)+3);
			if(donviTy.equals("000"))
			{
				sReturn = sReturn.replaceFirst("ngàn", "ngàn tỷ");
			}
		}


		return sReturn;
	}
	
	// Khoi tao ham Read
		public static String Read(String sNumber, int iPo) {
			// Tao mot bien tra ve
			String sReturn = "";
			// Tao mot bien so
			String sPo[] = { "", "ngàn" + " ",
					"triệu" + " ", "tỷ" + " ",  "ngàn" + " "};
			String sSo[] = { "không" + " ", "một" + " ",
					"hai" + " ", "ba" + " ",
					"bốn" + " ", "năm" + " ",
					"sáu" + " ", "bảy" + " ",
					"tám" + " ", "chín" + " " };
			String sDonvi[] = { "", "mươi" + " ",
					"trăm" + " " };
			// Tim chieu dai cua chuoi
			int iLen = sNumber.length();
			// Tao mot bien nho vi tri doc
			int iRe = 0;
			// Tao mot vong lap de doc so
			for (int i = 0; i < iLen; i++) {
				String sTemp = "" + sNumber.charAt(i);
				int iTemp = Integer.parseInt(sTemp);
				// Tao mot bien ket qua
				String sRead = "";
				// Kiem tra xem so nhan vao co = 0 hay ko
				if (iTemp == 0) {
					switch (iRe) {
						case 0:
							break;// Khong lam gi ca
						case 1: {
							if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
								sRead = "lẻ" + " ";
							}
							break;
						}
						case 2: {
							if (Integer.parseInt("" + sNumber.charAt(0)) != 0
									&& Integer.parseInt("" + sNumber.charAt(1)) != 0) {
								sRead = "không trăm" + " ";
							}
							break;
						}
					}
				} else if (iTemp == 1) {
					switch (iRe) {
						case 1:
							sRead = "mười" + " ";
							break;
						default:
							sRead = "một" + " " + sDonvi[iRe];
							break;
					}
				} else if (iTemp == 5) {
					switch (iRe) {
						case 0: {
							if(sNumber.length() <= 1){
								sRead = "năm" + " ";
							}
							else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
								sRead = "lăm" + " ";
							} else
								sRead = "năm" + " ";
							break;
						}
						default:
							sRead = sSo[iTemp] + sDonvi[iRe];
					}
				} else {
					sRead = sSo[iTemp] + sDonvi[iRe];
				}

				sReturn = sRead + sReturn;
				iRe++;
			}
			if (sReturn.length() > 0) {
				sReturn += sPo[iPo];
			}
			// xu ly lan cuoi voi 220 000 tỷ 000 000 000 000 000
			if(sNumber.length()>12)
			{
				// tren gia tri can xu ly, kiem tra xem don vi TY co = 000 khong
				System.out.println(sNumber.substring(11, 8));
			}
			return sReturn;
		}

		public static String getRomanNumerals(int Int) {
			LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
			roman_numerals.put("M", 1000);
			roman_numerals.put("CM", 900);
			roman_numerals.put("D", 500);
			roman_numerals.put("CD", 400);
			roman_numerals.put("C", 100);
			roman_numerals.put("XC", 90);
			roman_numerals.put("L", 50);
			roman_numerals.put("XL", 40);
			roman_numerals.put("X", 10);
			roman_numerals.put("IX", 9);
			roman_numerals.put("V", 5);
			roman_numerals.put("IV", 4);
			roman_numerals.put("I", 1);
			String res = "";
			for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
				int matches = Int/entry.getValue();
				res += repeat(entry.getKey(), matches);
				Int = Int % entry.getValue();
			}
			return res;
		}
		
		public static String repeat(String s, int n) {
			if(s == null) {
				return null;
			}
			final StringBuilder sb = new StringBuilder();
			for(int i = 0; i < n; i++) {
				sb.append(s);
			}
			return sb.toString();
		}
		
		public static String formatNumberForRead(double number) {
			NumberFormat nf = NumberFormat.getInstance();
			String temp = nf.format(number);
			String strReturn = "";
			int slen = temp.length();
			for (int i = 0; i < slen; i++) {
				if (String.valueOf(temp.charAt(i)).equals("."))
					break;
				else if (Character.isDigit(temp.charAt(i))) {
					strReturn += String.valueOf(temp.charAt(i));
				}
			}
			return strReturn;

		}
}
