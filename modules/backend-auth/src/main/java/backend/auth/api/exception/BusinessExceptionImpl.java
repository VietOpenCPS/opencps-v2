package backend.auth.api.exception;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.net.HttpURLConnection;

import javax.ws.rs.core.Response;

import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

public class BusinessExceptionImpl {

	private static Log _log = LogFactoryUtil.getLog(BusinessExceptionImpl.class);

	public static Response processException(Exception e) {
		_log.debug(e);
//		_log.error(e);

		ErrorMsgModel error = new ErrorMsgModel();

		if (e instanceof UnauthenticationException) {
			error.setMessage("Non-Authoritative Information.");
			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
			error.setDescription("Non-Authoritative Information.");

			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
		} else {
			if (e instanceof UnauthorizationException) {
				error.setMessage("Unauthorized.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Unauthorized.");

				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

			} else {
				error.setMessage("No Content.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription(e.getMessage());
//				e.printStackTrace();
				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();
			}
		}
	}

}
