package opencps.statistic.common.webservice.exception;

public class OpencpsApiError {
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getSubCodeDescription() {
		return subCodeDescription;
	}
	public void setSubCodeDescription(String subCodeDescription) {
		this.subCodeDescription = subCodeDescription;
	}
	private String code;
	private String description;
	private String subCode;
	private String subCodeDescription;
}
