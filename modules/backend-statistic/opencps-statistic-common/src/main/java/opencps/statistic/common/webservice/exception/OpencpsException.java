package opencps.statistic.common.webservice.exception;

public class OpencpsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OpencpsError error;

	public OpencpsException(OpencpsError error) {
		super();
		this.error = error;
	}

	public OpencpsException(OpencpsError error, Throwable throwable) {
		super(throwable);
		this.error = error;
	}

	public OpencpsException(OpencpsError error, Throwable throwable, String message) {
		super(message, throwable);
		this.error = error;
	}

	public OpencpsError getError() {
		return this.error;
	}
}
