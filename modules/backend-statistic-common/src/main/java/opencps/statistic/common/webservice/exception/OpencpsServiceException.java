package opencps.statistic.common.webservice.exception;

import java.io.Serializable;

public class OpencpsServiceException extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OpencpsServiceExceptionDetails faultDetails;

	public OpencpsServiceException(OpencpsServiceExceptionDetails faultDetails) {
		this.faultDetails = faultDetails;
	}

	public OpencpsServiceException(String message, OpencpsServiceExceptionDetails faultDetails) {
		super(message);
		this.faultDetails = faultDetails;
	}

	public OpencpsServiceExceptionDetails getFaultDetails() {
		return faultDetails;
	}
}
