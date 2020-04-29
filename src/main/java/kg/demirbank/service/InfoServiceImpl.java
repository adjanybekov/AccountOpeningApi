package kg.demirbank.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kg.demirbank.proxy.ReportApiProxy;

@Service
public class InfoServiceImpl implements InfoService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${query.dblink.name}")
	private String dbLinkName;

	@Autowired
	ReportApiProxy reportApi;

	public List<Map<String, Object>> getBranchList() {
		return reportApi.getReportSQLResult(String.format("select * from cbs_bolum%s", dbLinkName));
	}

	public List<Map<String, Object>> getCustomerTypeList() {
		return reportApi.getReportSQLResult(String.format("select * from cbs_musteri_tipi_kodlari%s", dbLinkName));
	}

	public List<Map<String, Object>> getCustomerGroupList() {
		return reportApi.getReportSQLResult(String.format("select * from cbs_dk_grup_kodlari%s", dbLinkName));
	}

	public List<Map<String, Object>> getIdTypeList() {
		return reportApi.getReportSQLResult(String.format("select * from CBS_KIMLIK_KODLARI%s", dbLinkName));
	}

	public List<Map<String, Object>> getMaritalStatusList() {
		return reportApi.getReportSQLResult(String.format("select * from CBS_MEDENI_HAL_KODLARI%s", dbLinkName));
	}

	public List<Map<String, Object>> getNationalityList() {
		return reportApi.getReportSQLResult(String.format("select * from CBS_UYRUK_KODLARI%s", dbLinkName));
	}

	public List<Map<String, Object>> getProfessionList() {
		return reportApi.getReportSQLResult(String.format("select * from CBS_MESLEK_KODLARI%s", dbLinkName));
	}

	public List<Map<String, Object>> getRatingList() {
		return reportApi.getReportSQLResult(String.format("select * from CBS_RATING_KODLARI%s", dbLinkName));
	}

	public List<Map<String, Object>> getResidentialStatusList() {
		return reportApi.getReportSQLResult(String.format("select * from CBS_YERLESIM_KODLARI%s", dbLinkName));
	}

	public List<Map<String, Object>> getAddressTypeList() {
		return reportApi.getReportSQLResult(String.format("select * from CBS_ADRES_KODLARI%s", dbLinkName));
	}

	public List<Map<String, Object>> getCityList() {
		return reportApi.getReportSQLResult(String.format("select * from CBS_IL_KODLARI%s", dbLinkName));
	}

	public List<Map<String, Object>> getCityMaps() {
		return reportApi.getReportSQLResult(
				String.format("SELECT IL_KODU, PKG_SOA_COMMON.text_translation%1$s(IL_ADI,'RUS') IL_ADI, "
						+ " ULKE_KODU FROM CBS_IL_KODLARI%1$s ORDER BY SIRA_NO,IL_KODU", dbLinkName));
	}

	public List<Map<String, Object>> getCountryList() {
		return reportApi.getReportSQLResult(String.format("select * from CBS_ULKE_KODLARI%s", dbLinkName));
	}

	public List<Map<String, Object>> getAllCustomerListSize() {
		return reportApi.getReportSQLResult(String
				.format("select count(*) count from cbs_musteri where musteri_tipi_kod is not null%s", dbLinkName));
	}

	public List<Map<String, Object>> getMaritalStatusMap() {
		return reportApi.getReportSQLResult(String.format(
				"select MEDENI_HAL_KODU, PKG_SOA_COMMON.text_translation%s(ACIKLAMA,'RUS') as ACIKLAMA"
						+ " from CBS_MEDENI_HAL_KODLARI%s" + " order by sira_no, medeni_hal_kodu",
				dbLinkName, dbLinkName));
	}

	public List<Map<String, Object>> getCustomerTypeMap() {
		return reportApi.getReportSQLResult(String.format(
				"SELECT MUSTERI_TIPI, PKG_SOA_COMMON.text_translation%s(ACIKLAMA,'RUS') as ACIKLAMA"
						+ " FROM CBS_MUSTERI_TIPI_KODLARI%s" + " ORDER BY SIRA_NO, MUSTERI_TIPI",
				dbLinkName, dbLinkName));
	}

	public List<Map<String, Object>> getIdentificationDocMap() {
		return reportApi
				.getReportSQLResult(String.format(
						"SELECT KIMLIK_TIPI, PKG_SOA_COMMON.text_translation%s(ACIKLAMA,'RUS') ACIKLAMA"
								+ " FROM CBS_KIMLIK_KODLARI%s" + " ORDER BY SIRA_NO, KIMLIK_TIPI",
						dbLinkName, dbLinkName));
	}

	public List<Map<String, Object>> getCountryMap() {
		return reportApi.getReportSQLResult(String.format(
				"SELECT ULKE_KODU, ULKE_ADI FROM CBS_ULKE_KODLARI%s"
						+ " ORDER BY case when ulke_kodu='KG' then 'AA' when ulke_kodu='KZ' then 'AB' "
						+ " when ulke_kodu='RU' then 'AC' when ulke_kodu='TR' then 'AD' else ULKE_ADI end",
				dbLinkName));
	}

	public List<Map<String, Object>> getCitizenshipMap() {
		return reportApi.getReportSQLResult(String.format(
				"SELECT UYRUK_KODU, PKG_SOA_COMMON.text_translation%s"
						+ " (ACIKLAMA,'RUS') as ACIKLAMA FROM CBS_UYRUK_KODLARI%s ORDER BY case "
						+ " when UYRUK_KODU='KG' then 'AA' when UYRUK_KODU='KZ' then 'AB' "
						+ " when UYRUK_KODU='RU' then 'AC' when UYRUK_KODU='TR' then 'AD' else ACIKLAMA end",
				dbLinkName, dbLinkName));
	}

	public List<Map<String, Object>> getNationalityMap() {
		return reportApi.getReportSQLResult(String.format(
				"SELECT UYRUK_KODU, PKG_SOA_COMMON.text_translation%s"
						+ "(ACIKLAMA,'RUS') as ACIKLAMA FROM CBS_UYRUK_KODLARI%s ORDER BY case "
						+ "when UYRUK_KODU='KG' then 'AA' when UYRUK_KODU='KZ' then 'AB' "
						+ "when UYRUK_KODU='RU' then 'AC' when UYRUK_KODU='TR' then 'AD' else ACIKLAMA end",
				dbLinkName, dbLinkName));
	}

	public List<Map<String, Object>> getOccupationCodeMap() {
		return reportApi.getReportSQLResult(String.format(
				"SELECT MESLEK_KODU, PKG_SOA_COMMON.text_translation%s"
						+ "(ACIKLAMA,'RUS') as ACIKLAMA FROM CBS_MESLEK_KODLARI%s" + " ORDER BY SIRA_NO,ACIKLAMA",
				dbLinkName, dbLinkName));
	}

	public List<Map<String, Object>> getCompanyOfTheStaffMap() {
		return reportApi.getReportSQLResult(
				String.format("select musteri_no, pkg_musteri.sf_musteri_adi%s(musteri_no) adi" + " from cbs_musteri%s"
						+ " where musteri_tipi_kod in (3) order by adi", dbLinkName, dbLinkName));
	}

	public List<Map<String, Object>> getCurrencyMap() {
		return reportApi.getReportSQLResult(String.format("select dvz from cbs_kur%s"
				+ " where dvz='KGS' or dvz='USD' or dvz='TRY' or dvz='EUR' or dvz='RUB' or dvz='KZT' "
				+ " order by case when dvz='KGS' then 'AA' when dvz='USD' then 'AB'"
				+ " when dvz='EUR' then 'AC' when dvz='RUB' then 'AD'" + "when dvz='RUB' then 'AE' else dvz end",
				dbLinkName));
	}

	public List<Map<String, Object>> getUniversityMap() {
		List<Map<String, Object>> result = reportApi.getReportSQLResult(String.format(
				"select P.DEGER from cbs_parametre%s p" + " where P.KOD='STUDENT_CARD_UNIVERSITIES'", dbLinkName));

		Map<String, Object> d = result.get(0);
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
		return result;

	}

	public List<Map<String, Object>> getEducationStatusMap() {
		return reportApi.getReportSQLResult(String.format(
				"select EDU_STATUS_CODE, EDU_STATUS_NAME from (select decode(rownum, 1, 'Студент', 2, 'Аспирант', 3, 'Школьник') "
						+ "as EDU_STATUS_NAME, decode(rownum, 1, '1', 2, '2', 3, '3') as EDU_STATUS_CODE from dual connect by level <= 3) "));
	}

	public List<Map<String, Object>> getPurposeMap() {
		return reportApi.getReportSQLResult(
				String.format("select PURPOSE_CODE, PURPOSE_NAME from (select decode(rownum, 1, 'Получение зарплаты', "
						+ "2, 'Для поручительства', 3, 'Проведение интернет транзакций', 4, 'Иное') "
						+ "as PURPOSE_NAME, decode(rownum, 1, 'Получение зарплаты', 2, 'Для поручительства', 3, "
						+ "'Проведение интернет транзакций', 4, 'other') as PURPOSE_CODE from dual connect by level <= 4) "));
	}

	public List<Map<String, Object>> getPublicMap() {
		return reportApi.getReportSQLResult(String.format(
				"select PO_CODE, PO_NAME from (select decode(rownum, 1, 'ИПДЛ', 2, 'НПДЛ', 3, 'ППДЛ МО') as PO_NAME, "
						+ "decode(rownum, 1, '1', 2, '2', 3, '3') as PO_CODE from dual connect by level <= 3)"));
	}

	public List<Map<String, Object>> getPublicRelatedMap() {
		return reportApi.getReportSQLResult(String.format(
				"select PO_RELATED_CODE, PO_RELATED_NAME from (select decode(rownum, 1, 'Близкий родственник', 2, 'Деловой партнер', "
						+ "3, 'Официальный представитель') as PO_RELATED_NAME, "
						+ "decode(rownum, 1, '1', 2, '2', 3, '3') as PO_RELATED_CODE from dual connect by level <= 3)"));
	}

	public List<Map<String, Object>> getRiskLevelCodesMap() {
		return reportApi.getReportSQLResult(String
				.format("SELECT RISK_LEVEL_CODE, PKG_SOA_COMMON.text_translation%s(EXPLANATION,'RUS') EXPLANATION "
						+ " FROM CBS_RISK_LEVEL_CODES%s", dbLinkName, dbLinkName));
	}

	public List<Map<String, Object>> getRiskCountryMap() {
		return reportApi.getReportSQLResult(String.format("SELECT ULKE_KODU FROM CBS_RISK_ULKE_KODLARI%s", dbLinkName));
	}

	//------------------ productController
	public List<Map<String, Object>> getDebitCardTypeMap() {
		return reportApi.getReportSQLResult(String.format("select code, style_name from cbs.cbs_card_styles%1$s" +
										" where status = 'A' and code <> 'D' ORDER BY case " +
										" when code='DCV' then 'AA' when code='VDG' then 'AB' else code end", dbLinkName));
	}

	public List<Map<String, Object>> getBranchMap() {
		return reportApi.getReportSQLResult(String.format("select kodu, adi from cbs.CBS_BOLUM%s where kodu <> 'SYS'", dbLinkName));
	}

	public List<Map<String, Object>> getCreditProductCodeMap() {
		return reportApi.getReportSQLResult(String.format("select * from cbs.CBS_PRODUCT_CODES%s", dbLinkName));
	}


}
