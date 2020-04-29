package kg.demirbank.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomerSearch {

	public CustomerSearch() {
		this.pageNumber = 0;
		this.recordsPerPage = 10;
	}

	private String customerNumber;
	private String surname;
	private String name;
	private String secondName;
	private String taxNumber;
	private String staffCompanyNumber;
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date birthDate;
	private String passportNumber;

	public CustomerSearch(String customerNumber, String surname, String name, String secondName, String taxNumber,
			String staffCompanyNumber, Date birthDate, String passportNumber) {
		super();
		this.customerNumber = customerNumber;
		this.surname = surname;
		this.name = name;
		this.secondName = secondName;
		this.taxNumber = taxNumber;
		this.staffCompanyNumber = staffCompanyNumber;
		this.birthDate = birthDate;
		this.passportNumber = passportNumber;
	}

	private Integer pageNumber;
	private Integer recordsPerPage;

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getStaffCompanyNumber() {
		return staffCompanyNumber;
	}

	public void setStaffCompanyNumber(String staffCompanyNumber) {
		this.staffCompanyNumber = staffCompanyNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(Integer recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
}
