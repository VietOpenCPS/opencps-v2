package backend.auth.api.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class UnauthenticationException extends PortalException {

	private static final long serialVersionUID = 8938586786236040782L;

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
