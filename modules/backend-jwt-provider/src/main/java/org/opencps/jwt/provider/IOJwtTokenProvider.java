package org.opencps.jwt.provider;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.Date;


/**
 * @author trungnt
 *
 */
public interface IOJwtTokenProvider {

	public String generateToken(JSONObject data, int exprireIn, String secret);

	public JSONObject getBodyFromToken(String token, String secret);

	public Date getExpirationDateFromToken(String token, String secret);
	
	public boolean validateToken(String token, String secret);

}
