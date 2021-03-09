package backend.auth.api.exception;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

public class CommonExceptionImpl {

	private static Log _log = LogFactoryUtil.getLog(CommonExceptionImpl.class);

	public static Response processException(Exception e) {
		_log.debug(e);
		try {
			ErrorMsgModel error = new ErrorMsgModel();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(JSONFactoryUtil.looseSerialize(error)).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(JSONFactoryUtil.looseSerialize(error)).build();

				} else {
					error.setMessage("No Content.");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					_log.info("+++JSONFactoryUtil.looseSerialize(error):::"+JSONFactoryUtil.looseSerialize(error));
					return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(JSONFactoryUtil.looseSerialize(error)).build();
				}
			}
		}catch (Exception exception) {
			return null;
		}
	}
}
