package kg.demirbank.model;

public class AddressType {
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


	public AddressType() {
		super();
	}

	public AddressType(String code, String value) {
		super();
		this.code = code;
		this.value = value;
	}
}
