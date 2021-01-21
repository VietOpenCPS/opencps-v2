package org.opencps.jwt.provider.factory;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.opencps.jwt.provider.IOJwtTokenProvider;
import org.opencps.jwt.provider.util.JwtTokenProviderUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author trungnt
 *
 */
public class IOJwtTokenProviderFactory implements IOJwtTokenProvider {

	public Log _log = LogFactoryUtil.getLog(IOJwtTokenProviderFactory.class);

	@Override
	public String generateToken(JSONObject data, int exprireIn, String secret) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		if (exprireIn <= 0) {
			// defaul 3600s
			exprireIn = 3600;
		}

		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + exprireIn * 1000;
		Date exp = new Date(expMillis);

		JwtBuilder jwtBuilder = Jwts.builder();

		if (data != null) {
			Iterator<String> keys = data.keys();
			Map<String, Object> claims = new HashMap<String, Object>();
			while (keys.hasNext()) {
				String key = keys.next();
				Object obj = data.get(key);

				if (obj instanceof JSONArray) {
					claims.put(key, data.getJSONArray(key).toJSONString());
				} else if (obj instanceof JSONObject) {
					claims.put(key, data.getJSONObject(key).toJSONString());
				} else {
					claims.put(key, data.getString(key));
				}
			}

			jwtBuilder.addClaims(claims);
		}

		return jwtBuilder.setExpiration(exp).signWith(SignatureAlgorithm.HS512, signingKey).compact();
	}

	@Override
	public JSONObject getBodyFromToken(String token, String secret) {
		return JwtTokenProviderUtil.getBody(token);
	}

	@Override
	public Date getExpirationDateFromToken(String token, String secret) {
		return getClaimFromToken(token, Claims::getExpiration, secret);
	}

	@Override
	public boolean validateToken(String token, String secret) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			_log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			_log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			_log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			_log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			_log.error("JWT claims string is empty.");
		}
		return false;
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver, String secret) {
		final Claims claims = getAllClaimsFromToken(token, secret);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token, String secret) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
}
