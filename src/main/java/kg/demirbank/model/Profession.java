package kg.demirbank.model;

public class Profession {
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

	public Profession() {
		super();
	}

	public Profession(String code, String value) {
		super();
		this.code = code;
		this.value = value;
	}
}
