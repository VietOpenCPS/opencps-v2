package org.opencps.api.filter;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class OpenCPSKeyGenerator implements KeyGenerator {
	private Key _key = null;
	
    @Override
    public Key generateKey() {
    	if (_key != null) {
    		return _key;   		
    	}
    	_key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    	
    	return _key;
    }
}
