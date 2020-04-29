package kg.demirbank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import kg.demirbank.model.AddressType;
import kg.demirbank.model.Branch;
import kg.demirbank.model.City;
import kg.demirbank.model.Country;
import kg.demirbank.model.CustomerGroup;
import kg.demirbank.model.CustomerType;
import kg.demirbank.model.IdType;
import kg.demirbank.model.MaritalStatus;
import kg.demirbank.model.Nationality;
import kg.demirbank.model.Profession;
import kg.demirbank.model.Rating;
import kg.demirbank.model.ResidentialStatus;
import kg.demirbank.proxy.ReportApiProxy;

@Component
public class AccountInfoComponent {

	@Autowired
	InfoServiceImpl service;

	@Value("${query.dblink.name}")
	private String dbLinkName;

	@Autowired
	ReportApiProxy reportProxy;
	
	private List<Branch> branchList;

	public List<Branch> getBranchList() {
		return branchList;
	}

	private List<Map<String, Object>> cityList;

	public List<Map<String, Object>> getMaritalStatusList() {
		return maritalStatusList;
	}

	private List<Map<String, Object>> maritalStatusList;

	public List<Map<String, Object>> getCustomerTypeList() {
		return customerTypeList;
	}

	private List<Map<String, Object>> customerTypeList;

	public List<Map<String, Object>> getIdentificationDocList() {
		return identificationDocList;
	}

	private List<Map<String, Object>> identificationDocList;
	private List<Map<String, Object>> countryList;
	private List<Map<String, Object>> citizenshipList;
	private List<Map<String, Object>> nationalityList;
	private List<Map<String, Object>> occupationCodeList;
	private List<Map<String, Object>> companyOfTheStaffList;
	private List<Map<String, Object>> currencyList;
	private List<Map<String, Object>> universityList;
	private List<Map<String, Object>> educationStatusList;
	private List<Map<String, Object>> purposeList;
	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}

	public void setCityList(List<Map<String, Object>> cityList) {
		this.cityList = cityList;
	}

	public void setMaritalStatusList(List<Map<String, Object>> maritalStatusList) {
		this.maritalStatusList = maritalStatusList;
	}

	public void setCustomerTypeList(List<Map<String, Object>> customerTypeList) {
		this.customerTypeList = customerTypeList;
	}

	public void setIdentificationDocList(List<Map<String, Object>> identificationDocList) {
		this.identificationDocList = identificationDocList;
	}

	public void setCountryList(List<Map<String, Object>> countryList) {
		this.countryList = countryList;
	}

	public void setCitizenshipList(List<Map<String, Object>> citizenshipList) {
		this.citizenshipList = citizenshipList;
	}

	public void setNationalityList(List<Map<String, Object>> nationalityList) {
		this.nationalityList = nationalityList;
	}

	public void setOccupationCodeList(List<Map<String, Object>> occupationCodeList) {
		this.occupationCodeList = occupationCodeList;
	}

	public void setCompanyOfTheStaffList(List<Map<String, Object>> companyOfTheStaffList) {
		this.companyOfTheStaffList = companyOfTheStaffList;
	}

	public void setCurrencyList(List<Map<String, Object>> currencyList) {
		this.currencyList = currencyList;
	}

	public void setUniversityList(List<Map<String, Object>> universityList) {
		this.universityList = universityList;
	}

	public void setEducationStatusList(List<Map<String, Object>> educationStatusList) {
		this.educationStatusList = educationStatusList;
	}

	public void setPurposeList(List<Map<String, Object>> purposeList) {
		this.purposeList = purposeList;
	}

	public void setPublicList(List<Map<String, Object>> publicList) {
		this.publicList = publicList;
	}

	public void setPublicRelatedList(List<Map<String, Object>> publicRelatedList) {
		this.publicRelatedList = publicRelatedList;
	}

	public void setRiskLevelCodes(List<Map<String, Object>> riskLevelCodes) {
		this.riskLevelCodes = riskLevelCodes;
	}

	public void setRiskCountryList(List<Map<String, Object>> riskCountryList) {
		this.riskCountryList = riskCountryList;
	}

	public void setCustomerTypes(List<CustomerType> customerTypes) {
		this.customerTypes = customerTypes;
	}

	public void setCustomerGroups(List<CustomerGroup> customerGroups) {
		this.customerGroups = customerGroups;
	}

	public void setIdTypes(List<IdType> idTypes) {
		this.idTypes = idTypes;
	}

	public void setMaritalStatuses(List<MaritalStatus> maritalStatuses) {
		this.maritalStatuses = maritalStatuses;
	}

	public void setNationalities(List<Nationality> nationalities) {
		this.nationalities = nationalities;
	}

	public void setProfessions(List<Profession> professions) {
		this.professions = professions;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public void setResidentialStatuses(List<ResidentialStatus> residentialStatuses) {
		this.residentialStatuses = residentialStatuses;
	}

	public void setAddressTypes(List<AddressType> addressTypes) {
		this.addressTypes = addressTypes;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	private List<Map<String, Object>> publicList;
	private List<Map<String, Object>> publicRelatedList;
	private List<Map<String, Object>> riskLevelCodes;
	private List<Map<String, Object>> riskCountryList;
	private List<CustomerType> customerTypes;
	private List<CustomerGroup> customerGroups;
	private List<IdType> idTypes;
	private List<MaritalStatus> maritalStatuses;
	private List<Nationality> nationalities;
	private List<Profession> professions;
	private List<Rating> ratings;
	private List<ResidentialStatus> residentialStatuses;
	private List<AddressType> addressTypes;
	private List<City> cities;
	private List<Country> countries;

	public List<CustomerType> getCustomerTypes() {
		return customerTypes;
	}

	public List<CustomerGroup> getCustomerGroups() {
		return customerGroups;
	}

	public List<IdType> getIdTypes() {
		return idTypes;
	}

	public List<MaritalStatus> getMaritalStatuses() {
		return maritalStatuses;
	}

	public List<Nationality> getNationalities() {
		return nationalities;
	}

	public List<Profession> getProfessions() {
		return professions;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public List<ResidentialStatus> getResidentialStatuses() {
		return residentialStatuses;
	}

	public List<AddressType> getAddressTypes() {
		return addressTypes;
	}

	public List<City> getCities() {
		return cities;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public List<Map<String, Object>> getCountryList() {
		return countryList;
	}

	public List<Map<String, Object>> getCitizenshipList() {
		return citizenshipList;
	}

	public List<Map<String, Object>> getNationalityList() {
		return nationalityList;
	}

	public List<Map<String, Object>> getOccupationCodeList() {
		return occupationCodeList;
	}

	public List<Map<String, Object>> getCompanyOfTheStaffList() {
		return companyOfTheStaffList;
	}

	public List<Map<String, Object>> getCurrencyList() {
		return currencyList;
	}

	public List<Map<String, Object>> getUniversityList() {
		return universityList;
	}

	public List<Map<String, Object>> getEducationStatusList() {
		return educationStatusList;
	}

	public List<Map<String, Object>> getPurposeList() {
		return purposeList;
	}

	public List<Map<String, Object>> getPublicList() {
		return publicList;
	}

	public List<Map<String, Object>> getPublicRelatedList() {
		return publicRelatedList;
	}

	public List<Map<String, Object>> getRiskLevelCodes() {
		return riskLevelCodes;
	}

	public List<Map<String, Object>> getRiskCountryList() {
		return riskCountryList;
	}

	public List<Map<String, Object>> getCityList() {
		return cityList;
	}

//	@PostConstruct
	private void init() {
		initBranch();
		initCity();
		initMaritalStatus();
		initCustomerType();
		initIdentificationDoc();
		initCountry();
		initCitizenship();
		initNationality();
		initOccupationCode();
		initCompanyOfTheStaff();
		initCurrency();
		initUniversity();
		initEducationStatus();
		initPurpose();
		initPublic();
		initPublicRelated();
		initRiskLevelCode();
		initRiskCountry();

		initCustomerTypes();
		initCustomerGroups();
		initIdTypes();
		initMaritalStatuses();
		initNationalities();
		initProfessions();
		initRatings();
		initResidentialStatuses();
		initAddressTypes();
		initCities();
		initCountries();
	}

	public void initBranch() {
		branchList = new ArrayList<>();
		service.getBranchList().stream()
				.forEach(m -> branchList.add(new Branch(m.get("KODU").toString(), m.get("ADI").toString())));
	}

	public void initCity() {
		cityList = new ArrayList<>();
		service.getCityMaps().forEach(m -> cityList.add(m));
	}

	public void initMaritalStatus() {
		maritalStatusList = new ArrayList<>();
		service.getMaritalStatusMap().forEach(m -> maritalStatusList.add(m));
	}

	public void initCustomerType() {
		customerTypeList = new ArrayList<>();
		service.getCustomerTypeMap().forEach(m -> customerTypeList.add(m));
	}

	public void initIdentificationDoc() {
		identificationDocList = new ArrayList<>();
		service.getIdentificationDocMap().forEach(m -> identificationDocList.add(m));
	}

//-----------------------------------
	public void initCountry() {
		countryList = new ArrayList<>();
		service.getCountryMap().forEach(m -> countryList.add(m));
	}

	public void initCitizenship() {
		citizenshipList = new ArrayList<>();
		service.getCitizenshipMap().forEach(m -> citizenshipList.add(m));
	}

	public void initNationality() {
		nationalityList = new ArrayList<>();
		service.getNationalityMap().forEach(m -> nationalityList.add(m));
	}

	public void initOccupationCode() {
		occupationCodeList = new ArrayList<>();
		service.getOccupationCodeMap().forEach(m -> occupationCodeList.add(m));
	}

	public void initCompanyOfTheStaff() {
		companyOfTheStaffList = new ArrayList<>();
		service.getCompanyOfTheStaffMap().forEach(m -> companyOfTheStaffList.add(m));
	}

	public void initCurrency() {
		currencyList = new ArrayList<>();
		service.getCurrencyMap().forEach(m -> currencyList.add(m));
	}

	public void initUniversity() {
		universityList = new ArrayList<>();
		service.getUniversityMap().forEach(m -> universityList.add(m));
	}

	public void initEducationStatus() {
		educationStatusList = new ArrayList<>();
		service.getEducationStatusMap().forEach(m -> educationStatusList.add(m));
	}

	public void initPurpose() {
		purposeList = new ArrayList<>();
		service.getPurposeMap().forEach(m -> purposeList.add(m));
	}

	public void initPublic() {
		publicList = new ArrayList<>();
		service.getPublicMap().forEach(m -> publicList.add(m));
	}

	public void initPublicRelated() {
		publicRelatedList = new ArrayList<>();
		service.getPublicRelatedMap().forEach(m -> publicRelatedList.add(m));
	}

	public void initRiskLevelCode() {
		riskLevelCodes = new ArrayList<>();
		service.getRiskLevelCodesMap().forEach(m -> riskLevelCodes.add(m));
	}

	public void initRiskCountry() {
		riskCountryList = new ArrayList<>();
		service.getRiskCountryMap().forEach(m -> riskCountryList.add(m));
	}

	public void initCustomerTypes() {
		customerTypes = new ArrayList<>();
		service.getCustomerTypeList().stream().forEach(m -> customerTypes.add(new CustomerType(m.get("MUSTERI_TIPI").toString(), m.get("ACIKLAMA").toString())));
	}
	public void initCustomerGroups() {
		customerGroups = new ArrayList<>();
		service.getCustomerGroupList().stream().forEach(m -> customerGroups.add(new CustomerGroup(m.get("DK_GRUP_KODU").toString(), m.get("ACIKLAMA").toString())));
	}
	public void initIdTypes() {
		idTypes = new ArrayList<>();
		service.getIdTypeList().stream().forEach(m -> idTypes.add(new IdType(m.get("KIMLIK_TIPI").toString(), m.get("ACIKLAMA").toString())));
	}

	public void initMaritalStatuses() {
		maritalStatuses = new ArrayList<>();
		service.getMaritalStatusList().stream().forEach(m -> maritalStatuses.add(new MaritalStatus(m.get("MEDENI_HAL_KODU").toString(), m.get("ACIKLAMA").toString())));
	}

	public void initNationalities() {
		nationalities = new ArrayList<>();
		service.getNationalityList().stream().forEach(m -> nationalities.add(new Nationality(m.get("UYRUK_KODU").toString(), m.get("ACIKLAMA").toString())));
	}
	public void initProfessions() {
		professions = new ArrayList<>();
		service.getProfessionList().stream().forEach(m -> professions.add(new Profession(m.get("MESLEK_KODU").toString(), m.get("ACIKLAMA").toString())));
	}

	public void initRatings() {
		ratings = new ArrayList<>();
		service.getRatingList().stream().forEach(m -> ratings.add(new Rating(m.get("RATING_KODU").toString(), m.get("ACIKLAMA").toString())));
	}
	public void initResidentialStatuses() {
		residentialStatuses = new ArrayList<>();
		service.getResidentialStatusList().stream().forEach(m -> residentialStatuses.add(new ResidentialStatus(m.get("YERLESIM_KODU").toString(), m.get("ACIKLAMA").toString())));
	}
	public void initAddressTypes() {
		addressTypes = new ArrayList<>();
		service.getAddressTypeList().stream().forEach(m -> addressTypes.add(new AddressType(m.get("ADRES_TIPI").toString(), m.get("ACIKLAMA").toString())));
	}
	public void initCities() {
		cities = new ArrayList<>();
		service.getCityList().stream().forEach(m -> cities.add(new City(m.get("IL_KODU").toString(), m.get("IL_ADI").toString())));
	}

	public void initCountries() {
		countries = new ArrayList<>();
		service.getCountryList().stream().forEach(m -> countries.add(new Country(m.get("ULKE_KODU").toString(), m.get("ULKE_ADI").toString())));
	}

}
