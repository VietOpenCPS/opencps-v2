package org.opencps.api.errors;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Log _log = LogFactoryUtil.getLog(CustomizedResponseEntityExceptionHandler.class.getName());

	@ExceptionHandler(OpenCPSNotFoundException.class)
	@ResponseBody
	public final ResponseEntity<OpenCPSErrorDetails> handleUserNotFoundException(OpenCPSNotFoundException ex,
			WebRequest request) {
		System.out.println("CustomizedResponseEntityExceptionHandler.handleUserNotFoundException()");
		OpenCPSErrorDetails errorDetails = new OpenCPSErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
