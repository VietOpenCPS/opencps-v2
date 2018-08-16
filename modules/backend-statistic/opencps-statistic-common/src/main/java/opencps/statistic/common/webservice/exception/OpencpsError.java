package opencps.statistic.common.webservice.exception;

import org.springframework.http.HttpStatus;

public class OpencpsError {
	protected OpencpsError() {
		throw new UnsupportedOperationException(
				"Can not be instantiated directly; must utilize protected factory method.");
	}

	private OpencpsError(String code, String description, HttpStatus httpStatus) {
		this.code = code;
		this.description = description;
		this.httpStatus = httpStatus;
	}

	private String code;
	private String description;
	private HttpStatus httpStatus;

	public final String getCode() {
		return this.code;
	}

	public final String getDescription() {
		return this.description;
	}

	public final HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	protected static OpencpsError newInstance(String code, String description, HttpStatus httpStatus) {
		return new OpencpsError(code, description, httpStatus);
	}
}
