package backend.auth.api.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class UnauthenticationException extends PortalException {
	public UnauthenticationException() {
	}

	public UnauthenticationException(String msg) {
		super(msg);
	}

	public UnauthenticationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UnauthenticationException(Throwable cause) {
		super(cause);
	}
}
