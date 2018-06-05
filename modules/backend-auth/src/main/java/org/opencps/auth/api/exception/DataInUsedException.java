package org.opencps.auth.api.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class DataInUsedException extends PortalException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8938586786236040782L;

	public DataInUsedException() {
	}

	public DataInUsedException(String msg) {
		super(msg);
	}

	public DataInUsedException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DataInUsedException(Throwable cause) {
		super(cause);
	}
}
