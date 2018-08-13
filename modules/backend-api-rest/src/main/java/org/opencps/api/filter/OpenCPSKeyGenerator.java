package org.opencps.api.filter;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class OpenCPSKeyGenerator implements KeyGenerator {
	private static Key _key;
	
    @Override
    public Key generateKey() {
    	if (_key == null) {
            _key = Keys.secretKeyFor(SignatureAlgorithm.HS512);;    		
    	}
        return _key;
    }
}
