package org.opencps.jwt.provider.factory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import java.util.Date;
import java.util.Iterator;

import org.opencps.jwt.provider.Auth0JwtTokenProvider;
import org.opencps.jwt.provider.util.JwtTokenProviderUtil;

/**
 * @author trungnt
 *
 */
public class Auth0JwtTokenProviderFactory implements Auth0JwtTokenProvider {

	public Log _log = LogFactoryUtil.getLog(Auth0JwtTokenProviderFactory.class);

	@Override
	public String generateToken(JSONObject data, int exprireIn, String secret) {

		if (exprireIn <= 0) {
			// defaul 3600s
			exprireIn = 3600;
		}

		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + exprireIn * 1000;
		Date exp = new Date(expMillis);

		// byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		// Key signingKey = new SecretKeySpec(apiKeySecretBytes, "HmacSHA256");
		Builder builder = JWT.create();

		if (data != null) {
			Iterator<String> keys = data.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				Object obj = data.get(key);
				if (obj instanceof JSONArray) {
					builder.withClaim(key, data.getJSONArray(key).toJSONString());
				} else if (obj instanceof JSONObject) {
					builder.withClaim(key, data.getJSONObject(key).toJSONString());
				} else {
					builder.withClaim(key, data.getString(key));
				}
			}
		}

		String token = builder.withExpiresAt(exp).sign(Algorithm.HMAC256(secret));
		// .sign(Algorithm.RSA256((RSAKeyProvider) signingKey));
		return token;
	}

	@Override
	public JSONObject getBodyFromToken(String token, String secret) {
		return JwtTokenProviderUtil.getBody(token);
	}

	@Override
	public Date getExpirationDateFromToken(String token, String secret) {

		try {
			Algorithm algorithm = getAlgorithm(token, secret);

			if (algorithm == null) {
				_log.error("Can't get algorithm");
				return null;
			}

			JWTVerifier verifier = JWT.require(algorithm).build();

			DecodedJWT decodedJWT = verifier.verify(token);

			return decodedJWT.getExpiresAt();

		} catch (Exception exception) {
			_log.error(exception);
			return null;
		}
	}

	@Override
	public boolean validateToken(String token, String secret) {

		try {
			Algorithm algorithm = getAlgorithm(token, secret);

			if (algorithm == null) {
				_log.error("Can't get algorithm");
				return false;
			}

			JWTVerifier verifier = JWT.require(algorithm).build();

			verifier.verify(token);

			return true;

		} catch (Exception exception) {
			_log.error(exception);
			return false;
		}
	}

	private Algorithm getAlgorithm(String token, String secret) {
		String agl = JwtTokenProviderUtil.getAgl(token);

		Algorithm algorithm = null;

		switch (agl) {

		case "HS256":
			algorithm = Algorithm.HMAC256(secret);
			break;
		case "RS256":
			// TODO get Algorithm
			break;
		default:
			algorithm = Algorithm.HMAC256(secret);
			break;
		}

		return algorithm;
	}

}
