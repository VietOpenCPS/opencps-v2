package org.swagger.model.verified;

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

}
