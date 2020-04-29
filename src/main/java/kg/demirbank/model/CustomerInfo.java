package kg.demirbank.model;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomerInfo {

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private String riskLevelCode;
	private String riskLevelName;
	private String retailCustomerType;
	private String reportCustomerType;
	private String glCode;
	private String lokalUnvan;
	private String vergiZorunlumu;

	private String txNo;
	private String customerNo;
	private String customerType;
	private String comission;
	private String openedByThirdPerson;

	private String name;
	private String surname;
	private String secondname;
	private String nameEng;
	private String surnameEng;
	private String secondnameEng;
	private String taxNumber;
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private String dateOfBirth;
	private String placeOfBirth;
	private String nationality;
	private String sexCode;
	private String sexName;
	private String statusResidence;
	private String citizenship;
	private String citizenshipName;
	private String statusMarital;
	private String statusMaritalName;
	private String identificationDoc;
	private String identificationDocName;
	private String seriesNumber;
	private String issuer;
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private String dateIssue;
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private String expireDate;
	private String visaDetails;
	private String visaValidPeriod;
	private String regDetails;
	private String regValidPeriod;

	private Integer addressCode;
	private String country;
	private String countryName;
	private String district;
	private String city;
	private String cityName;
	private String street;
	private String house;
	private String flat;
	private String postIndex;
	private String villageName;

	private String sameRegAddress;

	private String countryLiving;
	private String countryLivingName;
	private String districtLiving;
	private String cityLiving;
	private String cityLivingName;
	private String streetLiving;
	private String houseLiving;
	private String flatLiving;
	private String postIndexLiving;
	private String villageNameLiving;
	private String employmentStatus1;
	private String employmentStatus2;
	private String employmentStatus3;
	private String employmentStatus4;

	private String income1;
	private String income2;
	private String income3;
	private String income4;
	private String income5;
	private String income6;

	private String workIncome;
	private String additionalIncome;
	private String workIncomeCurrency;
	private String additionalIncomeCurrency;
	private String educationIncome;
	private String educationIncomeCurrency;

	private String placeOfWork;
	private String occupationCode;
	private String occupationName;
	private String companyOfTheStaff;
	private String companyOfTheStaffName;

	private String universityCode;
	private String universityName;
	private String campusID;
	private String statusEducation;
	private String statusEducationName;

	private String ulke_gsmkod_1; // mob1
	private String ulke_gsmkod_2; // mob2
	private String ulke_gsmkod_3; // mob3

	private String gsmkod_1;
	private String gsmkod_2;
	private String gsmkod_3;

	private String gsmtel_1;
	private String gsmtel_2;
	private String gsmtel_3;

	private String ulke_telkod_1; // hometel
	private String ulke_telkod_2; // worktel

	private String telkod_1;
	private String telkod_2;

	private String tel_1;
	private String tel_2;

	private String email1;
	private String email2;

	private String purpose;
	private String purposeOther;
	private String autoConverter;
	private String secretWord;
	private String gdbsNumber;

	private String highRisk1;
	private String highRisk2;
	private String highRisk3;
	private String highRisk4;

	private String organizationNameRisk;

	private String publicCode;
	private String publicName;
	private String publicPosition;
	private String publicIssueDate;
	private String publicExpireDate;
	private String publicPermission;

	private String publicRelatedPersonFullName;
	private String publicRelatedCode;
	private String publicRelatedName;
	private String publicRelatedPosition;
	private String publicRelatedIssueDate;
	private String publicRelatedExpireDate;

	public CustomerInfo() {
		this.setUlke_gsmkod_1("996");
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getGdbsNumber() {
		return gdbsNumber;
	}

	public void setGdbsNumber(String gdbsNumber) {
		this.gdbsNumber = gdbsNumber;
	}

	public String getHighRisk1() {
		return highRisk1;
	}

	public void setHighRisk1(String highRisk1) {
		this.highRisk1 = highRisk1;
	}

	public String getHighRisk2() {
		return highRisk2;
	}

	public void setHighRisk2(String highRisk2) {
		this.highRisk2 = highRisk2;
	}

	public String getHighRisk3() {
		return highRisk3;
	}

	public void setHighRisk3(String highRisk3) {
		this.highRisk3 = highRisk3;
	}

	public String getHighRisk4() {
		return highRisk4;
	}

	public void setHighRisk4(String highRisk4) {
		this.highRisk4 = highRisk4;
	}

	public String getOrganizationNameRisk() {
		return organizationNameRisk;
	}

	public void setOrganizationNameRisk(String organizationNameRisk) {
		this.organizationNameRisk = organizationNameRisk;
	}

	public String getAutoConverter() {
		return autoConverter;
	}

	public void setAutoConverter(String autoConverter) {
		this.autoConverter = autoConverter;
	}

	public String getPublicCode() {
		return publicCode;
	}

	public void setPublicCode(String publicCode) {
		this.publicCode = publicCode;
	}

	public String getPublicName() {
		return publicName;
	}

	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}

	public String getPublicPosition() {
		return publicPosition;
	}

	public void setPublicPosition(String publicPosition) {
		this.publicPosition = publicPosition;
	}

	public String getPublicIssueDate() {
		return publicIssueDate;
	}

	public void setPublicIssueDate(String publicIssueDate) {
		this.publicIssueDate = publicIssueDate;
	}

	public String getPublicExpireDate() {
		return publicExpireDate;
	}

	public void setPublicExpireDate(String publicExpireDate) {
		this.publicExpireDate = publicExpireDate;
	}

	public String getPublicPermission() {
		return publicPermission;
	}

	public void setPublicPermission(String publicPermission) {
		this.publicPermission = publicPermission;
	}

	public String getPublicRelatedPersonFullName() {
		return publicRelatedPersonFullName;
	}

	public void setPublicRelatedPersonFullName(String publicRelatedPersonFullName) {
		this.publicRelatedPersonFullName = publicRelatedPersonFullName;
	}

	public String getPublicRelatedCode() {
		return publicRelatedCode;
	}

	public void setPublicRelatedCode(String publicRelatedCode) {
		this.publicRelatedCode = publicRelatedCode;
	}

	public String getPublicRelatedName() {
		return publicRelatedName;
	}

	public void setPublicRelatedName(String publicRelatedName) {
		this.publicRelatedName = publicRelatedName;
	}

	public String getPublicRelatedPosition() {
		return publicRelatedPosition;
	}

	public void setPublicRelatedPosition(String publicRelatedPosition) {
		this.publicRelatedPosition = publicRelatedPosition;
	}

	public String getPublicRelatedIssueDate() {
		return publicRelatedIssueDate;
	}

	public void setPublicRelatedIssueDate(String publicRelatedIssueDate) {
		this.publicRelatedIssueDate = publicRelatedIssueDate;
	}

	public String getPublicRelatedExpireDate() {
		return publicRelatedExpireDate;
	}

	public void setPublicRelatedExpireDate(String publicRelatedExpireDate) {
		this.publicRelatedExpireDate = publicRelatedExpireDate;
	}

	public String getTxNo() {
		return txNo;
	}

	public void setTxNo(String txNo) {
		this.txNo = txNo;
	}

	public String getGlCode() {
		return glCode;
	}

	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}

	public String getLokalUnvan() {
		return lokalUnvan;
	}

	public void setLokalUnvan(String lokalUnvan) {
		this.lokalUnvan = lokalUnvan;
	}

	public String getVergiZorunlumu() {
		return vergiZorunlumu;
	}

	public void setVergiZorunlumu(String vergiZorunlumu) {
		this.vergiZorunlumu = vergiZorunlumu;
	}

	public String getReportCustomerType() {
		return reportCustomerType;
	}

	public void setReportCustomerType(String reportCustomerType) {
		this.reportCustomerType = reportCustomerType;
	}

	public String getRetailCustomerType() {
		return retailCustomerType;
	}

	public void setRetailCustomerType(String retailCustomerType) {
		this.retailCustomerType = retailCustomerType;
	}

	public String getRiskLevelCode() {
		return riskLevelCode;
	}

	public void setRiskLevelCode(String riskLevelCode) {
		this.riskLevelCode = riskLevelCode;
	}

	public String getRiskLevelName() {
		return riskLevelName;
	}

	public void setRiskLevelName(String riskLevelName) {
		this.riskLevelName = riskLevelName;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getComission() {
		return comission;
	}

	public void setComission(String comission) {
		this.comission = comission;
	}

	public String getOpenedByThirdPerson() {
		return openedByThirdPerson;
	}

	public void setOpenedByThirdPerson(String openedByThirdPerson) {
		this.openedByThirdPerson = openedByThirdPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getSurnameEng() {
		return surnameEng;
	}

	public void setSurnameEng(String surnameEng) {
		this.surnameEng = surnameEng;
	}

	public String getSecondnameEng() {
		return secondnameEng;
	}

	public void setSecondnameEng(String secondnameEng) {
		this.secondnameEng = secondnameEng;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getStatusMaritalName() {
		return statusMaritalName;
	}

	public void setStatusMaritalName(String statusMaritalName) {
		this.statusMaritalName = statusMaritalName;
	}

	public String getStatusResidence() {
		return statusResidence;
	}

	public void setStatusResidence(String statusResidence) {
		this.statusResidence = statusResidence;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getCitizenshipName() {
		return citizenshipName;
	}

	public void setCitizenshipName(String citizenshipName) {
		this.citizenshipName = citizenshipName;
	}

	public String getStatusMarital() {
		return statusMarital;
	}

	public void setStatusMarital(String statusMarital) {
		this.statusMarital = statusMarital;
	}

	public String getIdentificationDoc() {
		return identificationDoc;
	}

	public void setIdentificationDoc(String identificationDoc) {
		this.identificationDoc = identificationDoc;
	}

	public String getIdentificationDocName() {
		return identificationDocName;
	}

	public void setIdentificationDocName(String identificationDocName) {
		this.identificationDocName = identificationDocName;
	}

	public String getSeriesNumber() {
		return seriesNumber;
	}

	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getDateIssue() {
		return dateIssue;
	}

	public void setDateIssue(String dateIssue) {
		this.dateIssue = dateIssue;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getVisaDetails() {
		return visaDetails;
	}

	public void setVisaDetails(String visaDetails) {
		this.visaDetails = visaDetails;
	}

	public String getVisaValidPeriod() {
		return visaValidPeriod;
	}

	public void setVisaValidPeriod(String visaValidPeriod) {
		this.visaValidPeriod = visaValidPeriod;
	}

	public String getRegDetails() {
		return regDetails;
	}

	public void setRegDetails(String regDetails) {
		this.regDetails = regDetails;
	}

	public String getRegValidPeriod() {
		return regValidPeriod;
	}

	public void setRegValidPeriod(String regValidPeriod) {
		this.regValidPeriod = regValidPeriod;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public String getPostIndex() {
		return postIndex;
	}

	public void setPostIndex(String postIndex) {
		this.postIndex = postIndex;
	}

	public String getSameRegAddress() {
		return sameRegAddress;
	}

	public void setSameRegAddress(String sameRegAddress) {
		this.sameRegAddress = sameRegAddress;
	}

	public String getCountryLiving() {
		return countryLiving;
	}

	public void setCountryLiving(String countryLiving) {
		this.countryLiving = countryLiving;
	}

	public String getCountryLivingName() {
		return countryLivingName;
	}

	public void setCountryLivingName(String countryLivingName) {
		this.countryLivingName = countryLivingName;
	}

	public String getDistrictLiving() {
		return districtLiving;
	}

	public void setDistrictLiving(String districtLiving) {
		this.districtLiving = districtLiving;
	}

	public String getCityLiving() {
		return cityLiving;
	}

	public void setCityLiving(String cityLiving) {
		this.cityLiving = cityLiving;
	}

	public String getCityLivingName() {
		return cityLivingName;
	}

	public void setCityLivingName(String cityLivingName) {
		this.cityLivingName = cityLivingName;
	}

	public String getStreetLiving() {
		return streetLiving;
	}

	public void setStreetLiving(String streetLiving) {
		this.streetLiving = streetLiving;
	}

	public String getHouseLiving() {
		return houseLiving;
	}

	public void setHouseLiving(String houseLiving) {
		this.houseLiving = houseLiving;
	}

	public String getFlatLiving() {
		return flatLiving;
	}

	public void setFlatLiving(String flatLiving) {
		this.flatLiving = flatLiving;
	}

	public String getPostIndexLiving() {
		return postIndexLiving;
	}

	public void setPostIndexLiving(String postIndexLiving) {
		this.postIndexLiving = postIndexLiving;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getVillageNameLiving() {
		return villageNameLiving;
	}

	public void setVillageNameLiving(String villageNameLiving) {
		this.villageNameLiving = villageNameLiving;
	}

	public String getEmploymentStatus1() {
		return employmentStatus1;
	}

	public void setEmploymentStatus1(String employmentStatus1) {
		this.employmentStatus1 = employmentStatus1;
	}

	public String getEmploymentStatus2() {
		return employmentStatus2;
	}

	public void setEmploymentStatus2(String employmentStatus2) {
		this.employmentStatus2 = employmentStatus2;
	}

	public String getEmploymentStatus3() {
		return employmentStatus3;
	}

	public void setEmploymentStatus3(String employmentStatus3) {
		this.employmentStatus3 = employmentStatus3;
	}

	public String getEmploymentStatus4() {
		return employmentStatus4;
	}

	public void setEmploymentStatus4(String employmentStatus4) {
		this.employmentStatus4 = employmentStatus4;
	}

	public String getIncome1() {
		return income1;
	}

	public void setIncome1(String income1) {
		this.income1 = income1;
	}

	public String getIncome2() {
		return income2;
	}

	public void setIncome2(String income2) {
		this.income2 = income2;
	}

	public String getIncome3() {
		return income3;
	}

	public void setIncome3(String income3) {
		this.income3 = income3;
	}

	public String getIncome4() {
		return income4;
	}

	public void setIncome4(String income4) {
		this.income4 = income4;
	}

	public String getIncome5() {
		return income5;
	}

	public void setIncome5(String income5) {
		this.income5 = income5;
	}

	public String getIncome6() {
		return income6;
	}

	public void setIncome6(String income6) {
		this.income6 = income6;
	}

	public String getWorkIncome() {
		return workIncome;
	}

	public void setWorkIncome(String workIncome) {
		this.workIncome = workIncome;
	}

	public String getAdditionalIncome() {
		return additionalIncome;
	}

	public void setAdditionalIncome(String additionalIncome) {
		this.additionalIncome = additionalIncome;
	}

	public String getAdditionalIncomeCurrency() {
		return additionalIncomeCurrency;
	}

	public void setAdditionalIncomeCurrency(String additionalIncomeCurrency) {
		this.additionalIncomeCurrency = additionalIncomeCurrency;
	}

	public String getWorkIncomeCurrency() {
		return workIncomeCurrency;
	}

	public void setWorkIncomeCurrency(String workIncomeCurrency) {
		this.workIncomeCurrency = workIncomeCurrency;
	}

	public String getEducationIncome() {
		return educationIncome;
	}

	public void setEducationIncome(String educationIncome) {
		this.educationIncome = educationIncome;
	}

	public String getEducationIncomeCurrency() {
		return educationIncomeCurrency;
	}

	public void setEducationIncomeCurrency(String educationIncomeCurrency) {
		this.educationIncomeCurrency = educationIncomeCurrency;
	}

	public Number getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(Integer addressCode) {
		this.addressCode = addressCode;
	}

	public String getOccupationCode() {
		return occupationCode;
	}

	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	public String getUniversityCode() {
		return universityCode;
	}

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}

	public String getSecretWord() {
		return secretWord;
	}

	public void setSecretWord(String secretWord) {
		this.secretWord = secretWord;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getCampusID() {
		return campusID;
	}

	public void setCampusID(String campusID) {
		this.campusID = campusID;
	}

	public String getCompanyOfTheStaff() {
		return companyOfTheStaff;
	}

	public void setCompanyOfTheStaff(String companyOfTheStaff) {
		this.companyOfTheStaff = companyOfTheStaff;
	}

	public String getPlaceOfWork() {
		return placeOfWork;
	}

	public void setPlaceOfWork(String placeOfWork) {
		this.placeOfWork = placeOfWork;
	}

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}

	public String getCompanyOfTheStaffName() {
		return companyOfTheStaffName;
	}

	public void setCompanyOfTheStaffName(String companyOfTheStaffName) {
		this.companyOfTheStaffName = companyOfTheStaffName;
	}

	public String getStatusEducation() {
		return statusEducation;
	}

	public void setStatusEducation(String statusEducation) {
		this.statusEducation = statusEducation;
	}

	public String getStatusEducationName() {
		return statusEducationName;
	}

	public void setStatusEducationName(String statusEducationName) {
		this.statusEducationName = statusEducationName;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getUlke_gsmkod_1() {
		return ulke_gsmkod_1;
	}

	public void setUlke_gsmkod_1(String ulke_gsmkod_1) {
		this.ulke_gsmkod_1 = ulke_gsmkod_1;
	}

	public String getUlke_gsmkod_2() {
		return ulke_gsmkod_2;
	}

	public void setUlke_gsmkod_2(String ulke_gsmkod_2) {
		this.ulke_gsmkod_2 = ulke_gsmkod_2;
	}

	public String getUlke_gsmkod_3() {
		return ulke_gsmkod_3;
	}

	public void setUlke_gsmkod_3(String ulke_gsmkod_3) {
		this.ulke_gsmkod_3 = ulke_gsmkod_3;
	}

	public String getGsmkod_1() {
		return gsmkod_1;
	}

	public void setGsmkod_1(String gsmkod_1) {
		this.gsmkod_1 = gsmkod_1;
	}

	public String getGsmkod_2() {
		return gsmkod_2;
	}

	public void setGsmkod_2(String gsmkod_2) {
		this.gsmkod_2 = gsmkod_2;
	}

	public String getGsmkod_3() {
		return gsmkod_3;
	}

	public void setGsmkod_3(String gsmkod_3) {
		this.gsmkod_3 = gsmkod_3;
	}

	public String getGsmtel_1() {
		return gsmtel_1;
	}

	public void setGsmtel_1(String gsmtel_1) {
		this.gsmtel_1 = gsmtel_1;
	}

	public String getGsmtel_2() {
		return gsmtel_2;
	}

	public void setGsmtel_2(String gsmtel_2) {
		this.gsmtel_2 = gsmtel_2;
	}

	public String getGsmtel_3() {
		return gsmtel_3;
	}

	public void setGsmtel_3(String gsmtel_3) {
		this.gsmtel_3 = gsmtel_3;
	}

	public String getUlke_telkod_1() {
		return ulke_telkod_1;
	}

	public void setUlke_telkod_1(String ulke_telkod_1) {
		this.ulke_telkod_1 = ulke_telkod_1;
	}

	public String getUlke_telkod_2() {
		return ulke_telkod_2;
	}

	public void setUlke_telkod_2(String ulke_telkod_2) {
		this.ulke_telkod_2 = ulke_telkod_2;
	}

	public String getTelkod_1() {
		return telkod_1;
	}

	public void setTelkod_1(String telkod_1) {
		this.telkod_1 = telkod_1;
	}

	public String getTelkod_2() {
		return telkod_2;
	}

	public void setTelkod_2(String telkod_2) {
		this.telkod_2 = telkod_2;
	}

	public String getTel_1() {
		return tel_1;
	}

	public void setTel_1(String tel_1) {
		this.tel_1 = tel_1;
	}

	public String getTel_2() {
		return tel_2;
	}

	public void setTel_2(String tel_2) {
		this.tel_2 = tel_2;
	}

	public String getPurposeOther() {
		return purposeOther;
	}

	public void setPurposeOther(String purposeOther) {
		this.purposeOther = purposeOther;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String fullNameRus() {
		return (this.surname != null ? this.surname : "") + " " + (this.name != null ? this.name : "") + " "
				+ (this.secondname != null ? this.secondname : "").trim();
	}

	public String fullNameEng() {
		return (this.surnameEng != null ? this.surnameEng : "") + " " + (this.nameEng != null ? this.nameEng : "") + " "
				+ (this.secondnameEng != null ? this.secondnameEng : "").trim();
	}

}
