package opencps.statistic.common.webservice.exception;

public class OpencpsApiErrorResponse {
	public OpencpsApiErrorResponse(OpencpsApiError error) {
		super();
		this.error = error;
	}

	public OpencpsApiError getError() {
		return error;
	}

	public void setError(OpencpsApiError error) {
		this.error = error;
	}
	
	public OpencpsApiErrorResponse() {
		// TODO Auto-generated constructor stub
	}

	OpencpsApiError error;
}
