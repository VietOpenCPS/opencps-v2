package org.opencps.api.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.security.Key;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;

import io.jsonwebtoken.Jwts;

@Provider
@JWTOpenCPSToken
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class JWSOpenCPSTokenFilter implements ContainerRequestFilter  {
	private KeyGenerator keyGenerator = new OpenCPSKeyGenerator();
	static Log _log = LogFactoryUtil.getLog(JWSOpenCPSTokenFilter.class);
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith(ConstantUtils.BEARER)) {
            throw new NotAuthorizedException(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_INTERNAL_SERVER));
        }

        String token = authorizationHeader.substring(ConstantUtils.BEARER.trim().length()).trim();

        try {

            // Validate the token
            Key key = keyGenerator.generateKey();
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
 
        } catch (Exception e) {
        	_log.debug(e);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
