package backend.admin.config.whiteboard;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Date;

public class OpenCPSErrorDetails {

	private Date timestamp;
	private String message;
	private String rest_api;
	private static final String TIME_STAMP = "timestamp";
	private static final String MESSAGE = "message";
	private static final String REST_API = "rest_api";

	public OpenCPSErrorDetails(Date timestamp, String message, String rest_api) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.rest_api = rest_api;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getrest_api() {
		return rest_api;
	}

	public void setrest_api(String rest_api) {
		this.rest_api = rest_api;
	}

	@Override
	public String toString() {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put(TIME_STAMP, new Date().getTime());
		result.put(MESSAGE, this.message);
		result.put(REST_API, this.rest_api);
		return result.toJSONString();
	}
}
