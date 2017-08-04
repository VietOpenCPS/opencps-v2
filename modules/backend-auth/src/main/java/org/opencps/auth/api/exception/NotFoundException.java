package org.opencps.auth.api.exception;

import com.liferay.portal.kernel.exception.PortalException;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class NotFoundException extends PortalException{

	public NotFoundException() {
	}

	public NotFoundException(String msg) {
		super(msg);
	}

	public NotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
