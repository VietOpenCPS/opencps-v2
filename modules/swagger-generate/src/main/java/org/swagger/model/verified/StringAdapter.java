package org.swagger.model.verified;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.liferay.portal.kernel.util.Validator;

public class StringAdapter extends XmlAdapter<String, String> {

	@Override
	public String unmarshal(String v) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("StringAdapter.unmarshal()");
		if (Validator.isNumber(v)) {
			return v + " ";
		} else {
			return v;
		}
	}

	@Override
	public String marshal(String v) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("StringAdapter.marshal()");
		if (Validator.isNumber(v)) {
			return v + " ";
		} else {
			return v;
		}
	}
	
	    public static void main(String[] args) {

	        //Asia/Kuala_Lumpur +8
	        ZoneId defaultZoneId = ZoneId.systemDefault();
	        System.out.println("System Default TimeZone : " + defaultZoneId);

	        //toString() append +8 automatically.
	        Date date = new Date();
	        System.out.println("date : " + date);

	        //1. Convert Date -> Instant
	        Instant instant = date.toInstant();
	        System.out.println("instant : " + instant); //Zone : UTC+0

	        //2. Instant + system default time zone + toLocalDate() = LocalDate
	        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
	        System.out.println("localDate : " + localDate);

	        //3. Instant + system default time zone + toLocalDateTime() = LocalDateTime
	        LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
	        System.out.println("localDateTime : " + localDateTime);

	        //4. Instant + system default time zone = ZonedDateTime
	        ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
	        System.out.println("zonedDateTime : " + zonedDateTime);
	        
	        

	    }


}
