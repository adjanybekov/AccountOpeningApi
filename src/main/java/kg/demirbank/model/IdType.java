package kg.demirbank.model;

public class IdType {
	private String code;
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public IdType() {
		super();
	}

	public IdType(String code, String value) {
		super();
		this.code = code;
		this.value = value;
	}
}
