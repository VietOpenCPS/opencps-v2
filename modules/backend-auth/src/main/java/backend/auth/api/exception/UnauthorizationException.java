package backend.auth.api.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class UnauthorizationException extends PortalException {

	private static final long serialVersionUID = 8938586786236040782L;

	public UnauthorizationException() {
	}

	public UnauthorizationException(String msg) {
		super(msg);
	}

	public UnauthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UnauthorizationException(Throwable cause) {
		super(cause);
	}
}
