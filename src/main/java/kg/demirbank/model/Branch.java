package kg.demirbank.model;

public class Branch {
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

	public Branch() {
		super();
	}

	public Branch(String code, String value) {
		super();
		this.code = code;
		this.value = value;
	}
}
