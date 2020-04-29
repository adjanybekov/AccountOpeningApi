package kg.demirbank.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kg.demirbank.model.AddressType;
import kg.demirbank.model.Branch;
import kg.demirbank.model.City;
import kg.demirbank.model.Country;
import kg.demirbank.model.CustomerGroup;
import kg.demirbank.model.CustomerType;
import kg.demirbank.model.Gender;
import kg.demirbank.model.IdType;
import kg.demirbank.model.MaritalStatus;
import kg.demirbank.model.Nationality;
import kg.demirbank.model.Profession;
import kg.demirbank.model.Rating;
import kg.demirbank.model.ResidentialStatus;
import kg.demirbank.proxy.ReportApiProxy;
import kg.demirbank.service.AccountInfoComponent;
import kg.demirbank.service.InfoServiceImpl;
import kg.demirbank.utils.Common;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/info")
public class InfoController {

	@Autowired
	InfoServiceImpl service;

	@Value("${query.dblink.name}")
	private String dbLinkName;

	@Autowired
	ReportApiProxy reportProxy;
	
	@Autowired
	AccountInfoComponent staticInfo;

	@PostMapping("/getBranch")
	public ResponseEntity<List<Branch>> getBranch() {
		List<Branch> result = staticInfo.getBranchList();

		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/getCustomerType")
	public ResponseEntity<List<CustomerType>> getCustomerType() {
		List<CustomerType> result = staticInfo.getCustomerTypes();

		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/getCustomerGroup")
	public ResponseEntity<List<CustomerGroup>> getCustomerGroup() {
		List<CustomerGroup> result = staticInfo.getCustomerGroups();
		
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/getGender")
	public ResponseEntity<List<Gender>> getGender() {
		List<Gender> result = new ArrayList<>();

		result.add(new Gender("F","Female"));
		result.add(new Gender("M","Male"));
		
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/getIdType")
	public ResponseEntity<List<IdType>> getIdType() {
		List<IdType> result = staticInfo.getIdTypes();
		
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/getMaritalStatus")
	public ResponseEntity<List<MaritalStatus>> getMaritalStatus() {
		List<MaritalStatus> result = staticInfo.getMaritalStatuses();
		
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/getNationality")
	public ResponseEntity<List<Nationality>> getNationality() {
		List<Nationality> result =staticInfo.getNationalities();
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/getProfession")
	public ResponseEntity<List<Profession>> getProfession() {
		List<Profession> result = staticInfo.getProfessions();
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/getRating")
	public ResponseEntity<List<Rating>> getRating() {
		List<Rating> result = staticInfo.getRatings();
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/getResidentialStatus")
	public ResponseEntity<List<ResidentialStatus>> getResidentialStatus() {
		List<ResidentialStatus> result = staticInfo.getResidentialStatuses();
		
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/getAddressType")
	public ResponseEntity<List<AddressType>> getAddressType() {
		List<AddressType> result=staticInfo.getAddressTypes();
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/getCity")
	public ResponseEntity<List<City>> getCityList() {
		List<City> result = staticInfo.getCities();
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/getCountry")
	public ResponseEntity<List<Country>> getCountry() {
		List<Country> result =staticInfo.getCountries();
		
		if (result == null || result.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(result, HttpStatus.OK);
	}


	@PostMapping("/customer/getCityList")
	public Flux<Map<String, Object>> getCity() {
		return Flux.fromIterable(staticInfo.getCityList());
	}

	@PostMapping("/customer/getMaritalStatusList")
	public Flux<Map<String, Object>> getMaritalStatusList(){
		
		if (staticInfo.getMaritalStatusList() != null && !staticInfo.getMaritalStatusList().isEmpty())
			return Flux.fromIterable(staticInfo.getMaritalStatusList());

		String query = String.format("select MEDENI_HAL_KODU, PKG_SOA_COMMON.text_translation%s(ACIKLAMA,'RUS') as ACIKLAMA"
				+ " from CBS_MEDENI_HAL_KODLARI%s"
				+ " order by sira_no, medeni_hal_kodu", dbLinkName, dbLinkName);

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);
		
		staticInfo.setMaritalStatusList(searchData);

		return Flux.fromIterable(searchData);
	}

	@PostMapping("/customer/getCustomerTypeList")
	public Flux<Map<String, Object>> getCustomerTypeList(){

		if (staticInfo.getCustomerTypeList()!= null && !staticInfo.getCustomerTypeList().isEmpty())
			return Flux.fromIterable(staticInfo.getCustomerTypeList());

		String query = String.format("SELECT MUSTERI_TIPI, PKG_SOA_COMMON.text_translation%s(ACIKLAMA,'RUS') as ACIKLAMA"
				+ " FROM CBS_MUSTERI_TIPI_KODLARI%s"
				+ " ORDER BY SIRA_NO, MUSTERI_TIPI", dbLinkName, dbLinkName);

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);
		
		staticInfo.setCustomerTypeList(searchData);

		return Flux.fromIterable(searchData);
	}

	@PostMapping("/customer/getIdentificationDocList")
	public Flux<Map<String, Object>> getIdentificationDocList(){

		if (staticInfo.getIdentificationDocList()!= null && !staticInfo.getIdentificationDocList().isEmpty())
			return Flux.fromIterable(staticInfo.getIdentificationDocList());

		String query = String.format("SELECT KIMLIK_TIPI, PKG_SOA_COMMON.text_translation%s(ACIKLAMA,'RUS') ACIKLAMA"
				+ " FROM CBS_KIMLIK_KODLARI%s"
				+ " ORDER BY SIRA_NO, KIMLIK_TIPI", dbLinkName, dbLinkName);

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);

		staticInfo.setIdentificationDocList(searchData);
		
		return Flux.fromIterable(searchData);
	}

	@PostMapping("/customer/getCountryList")
	public Flux<Map<String, Object>> getCountryList(){

		if (staticInfo.getCountryList()!= null && !staticInfo.getCountryList().isEmpty())
			return Flux.fromIterable(staticInfo.getCountryList());

		String query = String.format("SELECT ULKE_KODU, ULKE_ADI FROM CBS_ULKE_KODLARI%s"
				+ " ORDER BY case when ulke_kodu='KG' then 'AA' when ulke_kodu='KZ' then 'AB' "
				+ " when ulke_kodu='RU' then 'AC' when ulke_kodu='TR' then 'AD' else ULKE_ADI end", dbLinkName);

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);

		staticInfo.setCountryList(searchData);
		
		return Flux.fromIterable(searchData);
	}
	@PostMapping("/customer/getCitizenshipList")
	public Flux<Map<String, Object>> getCitizenshipList(){

		if (staticInfo.getCitizenshipList()!= null && !staticInfo.getCitizenshipList().isEmpty())
			return Flux.fromIterable(staticInfo.getCitizenshipList());

		String query = String.format("SELECT UYRUK_KODU, PKG_SOA_COMMON.text_translation%s"
				+ " (ACIKLAMA,'RUS') as ACIKLAMA FROM CBS_UYRUK_KODLARI%s ORDER BY case "
				+ " when UYRUK_KODU='KG' then 'AA' when UYRUK_KODU='KZ' then 'AB' "
				+ " when UYRUK_KODU='RU' then 'AC' when UYRUK_KODU='TR' then 'AD' else ACIKLAMA end", dbLinkName, dbLinkName);

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);

		staticInfo.setCitizenshipList(searchData);
		
		return Flux.fromIterable(searchData);
	}
	@PostMapping("/customer/getNationalityList")
	public Flux<Map<String, Object>> getNationalityList(){

		if (staticInfo.getNationalityList()!= null && !staticInfo.getNationalityList().isEmpty())
			return Flux.fromIterable(staticInfo.getNationalityList());

		String query = String.format("SELECT UYRUK_KODU, PKG_SOA_COMMON.text_translation%s"
				+ "(ACIKLAMA,'RUS') as ACIKLAMA FROM CBS_UYRUK_KODLARI%s ORDER BY case "
				+ "when UYRUK_KODU='KG' then 'AA' when UYRUK_KODU='KZ' then 'AB' "
				+ "when UYRUK_KODU='RU' then 'AC' when UYRUK_KODU='TR' then 'AD' else ACIKLAMA end", dbLinkName, dbLinkName);
		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);

		staticInfo.setNationalityList(searchData);
		
		return Flux.fromIterable(searchData);
	}
	@PostMapping("/customer/getOccupationCodeList")
	public Flux<Map<String, Object>> getOccupationCodeList(){

		if (staticInfo.getOccupationCodeList()!= null && !staticInfo.getOccupationCodeList().isEmpty())
			return Flux.fromIterable(staticInfo.getOccupationCodeList());

		String query = String.format("SELECT MESLEK_KODU, PKG_SOA_COMMON.text_translation%s"
				+ "(ACIKLAMA,'RUS') as ACIKLAMA FROM CBS_MESLEK_KODLARI%s"
				+ " ORDER BY SIRA_NO,ACIKLAMA", dbLinkName, dbLinkName);;
		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);

		staticInfo.setOccupationCodeList(searchData);
		
		return Flux.fromIterable(searchData);
	}

	@PostMapping("/customer/getCompanyOfTheStaffList")
	public Flux<Map<String, Object>> getCompanyOfTheStaffList(){

		if (staticInfo.getCompanyOfTheStaffList()!= null && !staticInfo.getCompanyOfTheStaffList().isEmpty())
			return Flux.fromIterable(staticInfo.getCompanyOfTheStaffList());

		String query = String.format("select musteri_no, pkg_musteri.sf_musteri_adi%s(musteri_no) adi"
				+ " from cbs_musteri%s"
				+ " where musteri_tipi_kod in (3) order by adi", dbLinkName, dbLinkName);
		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);

		staticInfo.setCompanyOfTheStaffList(searchData);
		
		return Flux.fromIterable(searchData);
	}

	@PostMapping("/customer/getCityList/{countryId}")
	public Flux<Map<String, Object>> getCityList(@PathVariable("countryId") String countryId){

		if (staticInfo.getCityList()!= null && !staticInfo.getCityList().isEmpty())
			return Flux.fromIterable(staticInfo.getCityList().stream().filter(m -> m.get("ULKE_KODU") != null && m.get("ULKE_KODU").toString().equals(countryId)).collect(Collectors.toList()));

		String query = String.format("SELECT IL_KODU, PKG_SOA_COMMON.text_translation%s(IL_ADI,'RUS') IL_ADI, "
				+ " ULKE_KODU FROM CBS_IL_KODLARI%s"
				//+ " WHERE ULKE_KODU = NVL('%s',ULKE_KODU)"
				+ " ORDER BY SIRA_NO,IL_KODU", dbLinkName, dbLinkName/*, countryId*/);

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);

		staticInfo.setCityList(searchData);
		
		return Flux.fromIterable(staticInfo.getCityList().stream().filter(m -> m.get("ULKE_KODU") != null && m.get("ULKE_KODU").toString().equals(countryId)).collect(Collectors.toList()));
	}

	@PostMapping("/customer/getDistrictList/{countryId}")
	public Flux<Map<String, Object>> getDistrictList(@PathVariable("countryId") String countryId){

		String query = String.format(
				"select IL_ADI, IL_KODU, ULKE_KODU from (select decode(rownum, 1, 'Баткенская', 2, 'Джалал-Абадская', "
						+ "3, 'Иссык-Кульская', 4, 'Нарынская', 5, 'Ошская', 6, 'Таласская', 7, 'Чуйская') "
						+ "as IL_ADI, decode(rownum, 1, 'Баткенская', 2, 'Джалал-Абадская', 3, 'Иссык-Кульская', "
						+ "4, 'Нарынская', 5, 'Ошская', 6, 'Таласская', 7, 'Чуйская') as IL_KODU, "
						+ "decode(rownum, 1, 'KG', 2, 'KG', 3, 'KG', 4, 'KG', 5, 'KG', 6, 'KG', 7, 'KG') as "
						+ "ULKE_KODU from dual connect by level <= 7) WHERE ULKE_KODU = NVL('%s',ULKE_KODU) ",
						countryId);

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);

		return Flux.fromIterable(searchData);
	}

	@PostMapping("/customer/getCurrencyList")
	public Flux<Map<String, Object>> getCurrencyList(){

		if (staticInfo.getCurrencyList()!= null && !staticInfo.getCurrencyList().isEmpty())
			return Flux.fromIterable(staticInfo.getCurrencyList());

		String query = String.format("select dvz from cbs_kur%s"
				+ " where dvz='KGS' or dvz='USD' or dvz='TRY' or dvz='EUR' or dvz='RUB' or dvz='KZT' "
				+ " order by case when dvz='KGS' then 'AA' when dvz='USD' then 'AB'"
				+ " when dvz='EUR' then 'AC' when dvz='RUB' then 'AD'" + "when dvz='RUB' then 'AE' else dvz end", dbLinkName);
		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);

		staticInfo.setCurrencyList(searchData);
		
		return Flux.fromIterable(searchData);
	}

	@PostMapping("/customer/getUniversity")
	public Flux<Map<String, Object>> getUniversity(){

		if (staticInfo.getUniversityList()== null || staticInfo.getUniversityList().isEmpty()) {
			String query = String.format("select P.DEGER from cbs_parametre%s p"
					+ " where P.KOD='STUDENT_CARD_UNIVERSITIES'", dbLinkName);
	
			staticInfo.setUniversityList(reportProxy.getReportSQLResult(query));
		}
		
		List<Map<String, Object>> result = staticInfo.getUniversityList();
		
		Map<String, Object> d = result.get(0);
		if (d != null && d.containsKey("DEGER")) {
			String deger = d.get("DEGER").toString();
			deger = deger.replaceAll("^\"|\"$", "");
			deger = deger.trim();
			String[] list = deger.split(";");
			result.clear();
			for (String item : list) {
				String[] arr = item.split(",");
				Map<String, Object> map = new HashMap<>();
				map.put("kodu", arr[0]);
				map.put("adi", arr[1]);
				result.add(map);
			}
		}

		return Flux.fromIterable(result);
	}

	@PostMapping("/customer/getEducationStatusList")
	public Flux<Map<String, Object>> getEducationStatusList(){

		if (staticInfo.getEducationStatusList()!= null && !staticInfo.getEducationStatusList().isEmpty())
			return Flux.fromIterable(staticInfo.getEducationStatusList());

		String query = String.format(
				"select EDU_STATUS_CODE, EDU_STATUS_NAME from (select decode(rownum, 1, 'Студент', 2, 'Аспирант', 3, 'Школьник') "
						+ "as EDU_STATUS_NAME, decode(rownum, 1, '1', 2, '2', 3, '3') as EDU_STATUS_CODE from dual connect by level <= 3) ");

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);
		
		staticInfo.setEducationStatusList(searchData);

		return Flux.fromIterable(searchData);
	}

	@PostMapping("/customer/getPurposeList")
	public Flux<Map<String, Object>> getPurposeList(){

		if (staticInfo.getPurposeList()!= null && !staticInfo.getPurposeList().isEmpty())
			return Flux.fromIterable(staticInfo.getPurposeList());

		String query = String
				.format("select PURPOSE_CODE, PURPOSE_NAME from (select decode(rownum, 1, 'Получение зарплаты', "
						+ "2, 'Для поручительства', 3, 'Проведение интернет транзакций', 4, 'Иное') "
						+ "as PURPOSE_NAME, decode(rownum, 1, 'Получение зарплаты', 2, 'Для поручительства', 3, "
						+ "'Проведение интернет транзакций', 4, 'other') as PURPOSE_CODE from dual connect by level <= 4) ");

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);
		
		staticInfo.setPurposeList(searchData);

		return Flux.fromIterable(searchData);
	}
	@PostMapping("/customer/getPublicList")
	public Flux<Map<String, Object>> getPublicList(){

		if (staticInfo.getPublicList()!= null && !staticInfo.getPublicList().isEmpty())
			return Flux.fromIterable(staticInfo.getPublicList());

		String query = String.format(
				"select PO_CODE, PO_NAME from (select decode(rownum, 1, 'ИПДЛ', 2, 'НПДЛ', 3, 'ППДЛ МО') as PO_NAME, "
						+ "decode(rownum, 1, '1', 2, '2', 3, '3') as PO_CODE from dual connect by level <= 3)");

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);
		
		staticInfo.setPublicList(searchData);

		return Flux.fromIterable(searchData);
	}
	@PostMapping("/customer/getPublicRelatedList")
	public Flux<Map<String, Object>> getPublicRelatedList(){

		if (staticInfo.getPublicRelatedList()!= null && !staticInfo.getPublicRelatedList().isEmpty())
			return Flux.fromIterable(staticInfo.getPublicRelatedList());

		String query = String.format(
				"select PO_RELATED_CODE, PO_RELATED_NAME from (select decode(rownum, 1, 'Близкий родственник', 2, 'Деловой партнер', "
						+ "3, 'Официальный представитель') as PO_RELATED_NAME, "
						+ "decode(rownum, 1, '1', 2, '2', 3, '3') as PO_RELATED_CODE from dual connect by level <= 3)");

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);
		
		staticInfo.setPublicRelatedList(searchData);

		return Flux.fromIterable(searchData);
	}
	@PostMapping("/customer/getRiskLevelCodes")
	public Flux<Map<String, Object>> getRiskLevelCodes(){

		if (staticInfo.getRiskLevelCodes()!= null && !staticInfo.getRiskLevelCodes().isEmpty())
			return Flux.fromIterable(staticInfo.getRiskLevelCodes());

		String query = String.format("SELECT RISK_LEVEL_CODE, PKG_SOA_COMMON.text_translation%s(EXPLANATION,'RUS') EXPLANATION "
				+ " FROM CBS_RISK_LEVEL_CODES%s", dbLinkName, dbLinkName);

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);
		
		staticInfo.setRiskLevelCodes(searchData);

		return Flux.fromIterable(searchData);
	}
	@PostMapping("/customer/getRiskCountryList")
	public Flux<Map<String, Object>> getRiskCountryList(){

		if (staticInfo.getRiskCountryList()!= null && !staticInfo.getRiskCountryList().isEmpty())
			return Flux.fromIterable(staticInfo.getRiskCountryList());

		String query = String.format("SELECT ULKE_KODU FROM CBS_RISK_ULKE_KODLARI%s", dbLinkName);

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);
		
		staticInfo.setRiskCountryList(searchData);

		return Flux.fromIterable(searchData);
	}

	@PostMapping("/customer/getNewTxNo")
	public Integer getNewTxNo(){

		String query = String.format("select CBS.PKG_WP_MUSTERI.GETNEWTXNO%s TX_NO from dual", dbLinkName);

		List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);

		Map<String, Object> map ;
		if (!result.isEmpty())
			map =  result.get(0);
		else{
			map = new HashMap<>();
		}

		try {
			if (!map.isEmpty() && map.get("TX_NO") != null)
				return Integer.valueOf(map.get("TX_NO").toString());
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	@PostMapping("/customer/findIdentificationDocNameById/{id}")
	public String findIdentificationDocNameById(@PathVariable("id") String id){

		if (id == null || id.isEmpty() || id.matches("([\\s]*)"))
			return "";

		if (staticInfo.getIdentificationDocList() == null || staticInfo.getIdentificationDocList().isEmpty()) {
			String query = String
					.format("SELECT KIMLIK_TIPI, PKG_SOA_COMMON.text_translation%1$s(ACIKLAMA,'RUS') ACIKLAMA "
							+ " FROM CBS_KIMLIK_KODLARI%1$s", dbLinkName);
	
			staticInfo.setIdentificationDocList(reportProxy.getReportSQLResult(query));
		}
		
		
		List<Map<String, Object>> result = staticInfo.getIdentificationDocList().stream().filter(
				m -> m.get("KIMLIK_TIPI") != null && m.get("KIMLIK_TIPI").toString().equals(id)).collect(Collectors.toList());
		
		return result != null && !result.isEmpty() ? result.get(0).get("ACIKLAMA").toString() : "";
	}

	@PostMapping("/customer/findMaritalStatusNameById/{id}")
	public String findMaritalStatusNameById(@PathVariable("id") String id){

		if (id == null || id.isEmpty() || id.matches("([\\s]*)"))
			return "";

		if (staticInfo.getMaritalStatusList() == null || staticInfo.getMaritalStatusList().isEmpty()) {
			String query = String.format("select MEDENI_HAL_KODU, PKG_SOA_COMMON.text_translation%1$s(ACIKLAMA,'RUS') as ACIKLAMA"
					+ " from CBS_MEDENI_HAL_KODLARI%1$s", dbLinkName);
	
			staticInfo.setMaritalStatusList(reportProxy.getReportSQLResult(query));
		}

		List<Map<String, Object>> result = staticInfo.getMaritalStatusList().stream().filter(
				m -> m.get("MEDENI_HAL_KODU") != null && m.get("MEDENI_HAL_KODU").toString().equals(id)).collect(Collectors.toList());
		
		return result != null && !result.isEmpty() ? result.get(0).get("ACIKLAMA").toString() : "";
	}


	@PostMapping("/customer/findCitizenshipNameById/{id}")
	public String findCitizenshipNameById(@PathVariable("id") String id){

		if (id == null || id.isEmpty() || id.matches("([\\s]*)"))
			return "";

		if (staticInfo.getCitizenshipList() == null || staticInfo.getCitizenshipList().isEmpty()) {
			String query = String.format("SELECT UYRUK_KODU, PKG_SOA_COMMON.text_translation%1$s(ACIKLAMA,'RUS') ACIKLAMA "
							+ " FROM CBS_UYRUK_KODLARI%1$s", dbLinkName);
	
			staticInfo.setCitizenshipList(reportProxy.getReportSQLResult(query));
		}

		List<Map<String, Object>> result = staticInfo.getCitizenshipList().stream().filter(
				m -> m.get("UYRUK_KODU") != null && m.get("UYRUK_KODU").toString().equals(id)).collect(Collectors.toList());

		return result != null && !result.isEmpty() ? result.get(0).get("ACIKLAMA").toString() : "";
	}

	@PostMapping("/customer/findCountryNameById/{id}")
	public String findCountryNameById(@PathVariable("id") String id){

		if (staticInfo.getCountryList() == null || staticInfo.getCountryList().isEmpty()) {
			String query = String.format("SELECT ULKE_ADI FROM CBS_ULKE_KODLARI%s"
					, dbLinkName);
	
			staticInfo.setCountryList(reportProxy.getReportSQLResult(query));
		}

		List<Map<String, Object>> result = staticInfo.getCountryList().stream().filter(
				m -> m.get("ULKE_KODU") != null && m.get("ULKE_KODU").toString().equals(id)).collect(Collectors.toList());

		return result != null && !result.isEmpty() ? result.get(0).get("ULKE_ADI").toString() : "";
	}

	@PostMapping("/customer/findCityNameById/{id}")
	public String findCityNameById(@PathVariable("id") String id){

		if (id == null || id.isEmpty() || id.matches("([\\s]*)"))
			return "";

		if (staticInfo.getCityList() == null || staticInfo.getCityList().isEmpty()) {
			String query = String.format("SELECT IL_KODU, PKG_SOA_COMMON.text_translation%1$s(IL_ADI,'RUS') IL_ADI, "
					+ " ULKE_KODU FROM CBS_IL_KODLARI%1$s"
					+ " ORDER BY SIRA_NO,IL_KODU", dbLinkName);

			staticInfo.setCityList(reportProxy.getReportSQLResult(query));
		}
		
		List<Map<String, Object>> result = staticInfo.getCityList().stream().filter(
				m -> m.get("IL_KODU") != null && m.get("IL_KODU").toString().equals(id)).collect(Collectors.toList());
		
		return result != null && !result.isEmpty() ? result.get(0).get("IL_ADI").toString() : "";
	}

	@PostMapping("/customer/findUniversityNameById/{id}")
	public String findUniversityNameById(@PathVariable("id") String id){
		if (id == null || id.isEmpty() || id.matches("([\\s]*)"))
			return "";

		if (staticInfo.getUniversityList() == null || staticInfo.getUniversityList().isEmpty()) {
			String query = String.format("select P.DEGER from cbs_parametre%s p"
					+ " where P.KOD = 'STUDENT_CARD_UNIVERSITIES'", dbLinkName);
	
			staticInfo.setUniversityList(reportProxy.getReportSQLResult(query));
		}

		List<Map<String, Object>> result =  staticInfo.getUniversityList();

		Map<String, Object> d = result.get(0);
		if (d != null && d.containsKey("DEGER")) {
			String deger = d.get("DEGER").toString();
			deger = deger.replaceAll("^\"|\"$", "");
			deger = deger.trim();
			String[] list = deger.split(";");
			result.clear();
			for (String item : list) {
				String[] arr = item.split(",");
				if (arr[0].equals(id)) {
					return Common.getValue(arr[1], "");
				}
			}
		}

		return "";
	}

	@PostMapping("/customer/findStatusEducationNameById/{id}")
	public String findStatusEducationNameById(@PathVariable("id") String id){
		if (id == null || id.isEmpty() || id.matches("([\\s]*)")) {
			return "";
		}
		switch (id) {
			case "1":
				return "Студент";
			case "2":
				return "Аспирант";
			case "3":
				return "Школьник";
		}
		return "";
	}

	@PostMapping("/customer/findCustomerNameById/{id}")
	public String findCustomerNameById(@PathVariable("id") String id){

		if (id == null || id.isEmpty() || id.matches("([\\s]*)"))
			return "";
		
		String query = String.format("select pkg_musteri.sf_musteri_adi%s(%s) ADI from dual", dbLinkName, id);
		
		List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);
		if (result != null && !result.isEmpty()) {
			return Common.getValue(result.get(0).get("ADI"), "");
		}
		
		return "";
	}

	@PostMapping("/customer/findOccupationNameById/{id}")
	public String findOccupationNameById(@PathVariable("id") String id){
		if (id == null || id.isEmpty() || id.matches("([\\s]*)")) {
			return "";
		}
		String query = String.format("SELECT PKG_SOA_COMMON.text_translation%1$s(ACIKLAMA,'RUS') ACIKLAMA"
						+ " FROM CBS_MESLEK_KODLARI%1$s" + " WHERE MESLEK_KODU = '%2$s'", dbLinkName, id);

		List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);
		if (result != null && !result.isEmpty()) {
			return Common.getValue(result.get(0).get("ACIKLAMA"), "");
		}

		return "";
	}

	@PostMapping("/customer/findPublicNameById/{id}")
	public String findPublicNameById(@PathVariable("id") String id){
		if (id == null || id.isEmpty() || id.matches("([\\s]*)")) {
			return "";
		}
		switch (id) {
			case "1":
				return "ИПДЛ";
			case "2":
				return "НПДЛ";
			case "3":
				return "ППДЛ МО";
		}
		return "";
	}
	@PostMapping("/customer/findPublicRelatedNameById/{id}")
	public String findPublicRelatedNameById(@PathVariable("id") String id){
		if (id == null || id.isEmpty() || id.matches("([\\s]*)")) {
			return "";
		}
		switch (id) {
			case "1":
				return "Близкий родственник";
			case "2":
				return "Деловой партнер";
			case "3":
				return "Официальный представитель";
		}
		return "";
	}


	@PostMapping("/customer/findRiskLevelNameById/{id}")
	public String findRiskLevelNameById(@PathVariable("id") String id){
		if (id == null || id.isEmpty() || id.matches("([\\s]*)")) {
			return "";
		}
		String query = String.format("SELECT PKG_SOA_COMMON.text_translation%s(EXPLANATION, 'RUS') EXPLANATION "
				+ " FROM CBS_RISK_LEVEL_CODES%s"
				+ " WHERE RISK_LEVEL_CODE = '%s'", dbLinkName, dbLinkName, id);

		List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);
		if (result != null && !result.isEmpty()) {
			return Common.getValue(result.get(0).get("EXPLANATION"), "");
		}
		return "";
	}

	@PostMapping("/customer/findBranchNameById/{id}")
	public String findBranchNameById(@PathVariable("id") String id){
		if (id == null || id.isEmpty() || id.matches("([\\s]*)")) {
			return "";
		}
		String query = String.format("SELECT PKG_SOA_COMMON.text_translation%s(ADI,'RUS') ACIKLAMA"
				+ " FROM CBS_BOLUM%s WHERE KODU = '%s'", dbLinkName, dbLinkName, id);

		List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);
		if (result != null && !result.isEmpty()) {
			return Common.getValue(result.get(0).get("ACIKLAMA"), "");
		}

		return "";
	}

	@PostMapping("/customer/getPersonelBranch/{personelName}")
	public String getPersonelBranch(@PathVariable("personelName") String personelName) {
		String query = String.format("select calisilan_bolum from cbs_kullanici%s "
				+ "where lower(kodu) = lower('%s')", dbLinkName, personelName);

		List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);
		if (result != null && !result.isEmpty()) {
			return Common.getValue(result.get(0).get("CALISILAN_BOLUM"), "");
		}

		return "010";
	}

	@PostMapping(value = {"/customer/getDataFromTblCustomerInfoBySelect/{customerNo}/{className}"
			, "/customer/getDataFromTblCustomerInfoBySelect/{customerNo}" } )
	public List<Map<String, Object>> getDataFromTblCustomerInfoBySelect(@PathVariable("customerNo") String customerNo,
			@PathVariable(value = "className", required = false) String className) {

		String query;

		if(className==null || className.equals("") ){
			query = String.format("SELECT * FROM webportal.tbl_customer_info WHERE CLASS_NAME is null "
					+ " AND CUSTOMER_NO = %s ORDER BY TX_NO DESC", customerNo);

		}else{
			query = String.format("SELECT * FROM webportal.tbl_customer_info WHERE CLASS_NAME = '%s' "
					+ " AND CUSTOMER_NO = %s ORDER BY TX_NO DESC", className, customerNo);
		}

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/customer/checkIfApproveExist/{customerNo}" )
	public List<Map<String, Object>> checkIfApproveExist(@PathVariable("customerNo") String customerNo){
		String query = String.format("select count(*) total_created from CBS_ISLEM%s m "
				+ " where MUSTERI_NUMARA=%s and islem_kod in (1000, 1001) and durum='C'", dbLinkName, customerNo);

		return reportProxy.getReportSQLResult(query);
	}

}
