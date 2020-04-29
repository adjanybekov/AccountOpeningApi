package kg.demirbank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kg.demirbank.proxy.ReportApiProxy;
import kg.demirbank.service.ProductInfoComponent;
import kg.demirbank.utils.Common;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Value("${query.dblink.name}")
	private String dbLinkName;

	@Autowired
	ReportApiProxy reportProxy;

    @Autowired
    ProductInfoComponent staticInfo;

    @PostMapping("/getExistingAccounts/{custNo}")
    public List<Map<String, Object>> getExistingAccounts(@PathVariable("custNo") String custNo){
        String query = String.format("select * from cbs.cbs_hesap%s"
                + " where musteri_no = %s and (URUN_SINIF_KOD like 'NON INT.BEARING%%' OR URUN_SINIF_KOD like 'INTEREST BEARING%%' "
                + "or URUN_SINIF_KOD like '%%ELCARD%%')",  dbLinkName,custNo);


		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getExistingDebitCards/{custNo}")
	public List<Map<String, Object>> getExistingDebitCards(@PathVariable("custNo") String custNo) {
		String query = String.format("select q1.*, q2.PRIMARY_ACCOUNT from "
				+ "(select d.CARD_NO, d.CARD_ID_NO, d.CARD_STYLE, d.BRANCH_CODE, d.STATUS, SUBSTR(TO_CHAR(WM_CONCAT(b.ACCOUNT_NO)), 1, 200) accounts "
				+ "from cbs.cbs_debit_card%1$s d, cbs.cbs_debit_card_account%1$s"
				+ " b where d.CUSTOMER_NO = %2$s and d.card_id_no = b.CARD_ID_NO "
				+ "group by d.CARD_ID_NO, d.CARD_STYLE, d.BRANCH_CODE, d.STATUS, d.CUSTOMER_NO, d.CARD_NO) q1 left join "
				+ "(select d.ACCOUNT_NO PRIMARY_ACCOUNT, d.CARD_ID_NO from cbs.cbs_debit_card_account%1$s d"
				+ " where d.CARD_ID_NO = d.CARD_ID_NO and d.CARD_ACCOUNT_TYPE = 'PRIMARY') q2 ON q1.CARD_ID_NO = q2.CARD_ID_NO"
				, dbLinkName, custNo);

		return reportProxy.getReportSQLResult(query);
	}

    @PostMapping("/getDebitCardTypeList")
    public List<Map<String, Object>> getDebitCardTypeList(){
        return staticInfo.getDebitCardList();
    }


    @PostMapping("/getBranchList")
    public List<Map<String, Object>> getBranchList(){
        return staticInfo.getBranchList();
    }

	@PostMapping("/getDebitCardPrimaryAccounts/{custNo}")
	public List<Map<String, Object>> getDebitCardPrimaryAccounts(@PathVariable("custNo") String custNo) {
		String query = String.format("select hesap_no, doviz_kodu " + "from cbs.cbs_hesap%s"
				+ " where musteri_no = %s AND DURUM_KODU = 'A' and urun_tur_kod = 'DEMAND DEP' "
				+ "AND URUN_SINIF_KOD IN ( 'INTEREST BEARING-LC', 'INTEREST BEARING-FC', 'NON INT.BEARING-LC', 'NON INT.BEARING-FC') "
				,dbLinkName ,custNo);

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getExistingCreditCards/{custNo}")
	public List<Map<String, Object>> getExistingCreditCards(@PathVariable("custNo") String custNo) {
		String query = String.format("select CARD_NO, CARD_ID_NO, STATUS, BRANCH_CODE, PRODUCT_CODE, PAYMENT_COLLECTION_ACC_NO, "
						+ " PRICING_GROUP, STATEMENT_CODE, CC_CUSTOMER_GROUP from cbs.cbs_credit_card%s"
						+ " where customer_no = %s", dbLinkName, custNo);

		return reportProxy.getReportSQLResult(query);
	}


    @PostMapping("/getCreditProductCodeList")
    public List<Map<String, Object>> getCreditProductCodeList(){

        return staticInfo.getCreditProductCodeList();
    }

	@PostMapping("/findCreditProductCodeById/{id}")
	public String findCreditProductCodeById(@PathVariable("id") String id) {
		if (id == null || id.isEmpty() || id.matches("([\\s]*)")) {
			return "";
		}
		String query = String.format("select * from cbs.CBS_PRODUCT_CODES%s where code = '%s'", dbLinkName, id);
		List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);
		if (result != null && !result.isEmpty()) {
			return Common.getValue(result.get(0).get("EXPLANATION"), "");
		}
		return "";
	}

	@PostMapping("/getCreditLimit/{custNo}")
	public List<Map<String, Object>> getCreditLimit(@PathVariable("custNo") String custNo) {
		String query = String.format("select MUSTERI_LIMIT, MUSTERI_LIMIT_CY from CBS_KART_MUSTERI_LIMIT%s"
				+ " where musteri_no = %s and status = 'A'", dbLinkName, custNo);
		return reportProxy.getReportSQLResult(query);
	}
	@PostMapping("/getDebitCardIdNo/{txNo}")
	public List<Map<String, Object>> getDebitCardIdNo(@PathVariable("txNo") String txNo) {
		String query = String.format("select card_no, card_id_no from cbs.CBS_DEBIT_CARD_MAIN_TRAN%s"
				+ " where tx_no = %s", dbLinkName, txNo);

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/findCreditPurposeOfLoanById/{id}")
	public String findCreditPurposeOfLoanById(@PathVariable("id") String id) {

		if (id == null || id.isEmpty() || id.matches("([\\s]*)")) {
			return "";
		}
		String query = String.format("select * from cbs.CBS_KREDI_KULLANDIRIM_KODLARI%s"
				+ " where KULLANDIRIM_KODU = '%s'",dbLinkName, id);

		List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);
		if (result != null && !result.isEmpty()) {
			return Common.getValue(result.get(0).get("ACIKLAMA"), "");
		}
		return "";
	}
	@PostMapping("/getAccountCount/{custNo}")
	public String getAccountCount(@PathVariable("custNo") String custNo) {

		String query = String.format("SELECT count(*) ACCOUNT_COUNT FROM cbs.cbs_hesap%s"
				+ " WHERE musteri_no = %s ", dbLinkName,custNo);
		List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);
		if (result != null && !result.isEmpty()) {
			return Common.getValue(result.get(0).get("ACCOUNT_COUNT"), "");
		}
		return "";
	}

	@PostMapping("/getAccountNoFromDb/{txNo}")
	public List<Map<String, Object>> getAccountNoFromDb(@PathVariable("txNo") String txNo) {

		String query = String.format("select doviz_kodu, hesap_no, EXTERNAL_HESAP_NO "
				+ "from cbs.cbs_hesap_basvuru_toplu_hes_is%s where tx_no = %s",dbLinkName, txNo);

		return reportProxy.getReportSQLResult(query);
	}

	//todo returns empty list
	@PostMapping("/getCreditPaymentDateList")
	public List<Map<String, Object>> getCreditPaymentDateList() {

		String query = String.format("select CODE, PKG_SOA_COMMON.text_translation%s"
				+ "(EXPLANATION, 'RUS') EXPLANATION  from cbs.CBS_CC_STATEMENT_CODE%s"
				+ " where CODE in ('D5', '15', '25') ",dbLinkName,dbLinkName);

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getCreditPurposeOfLoanList")
	public List<Map<String, Object>> getCreditPurposeOfLoanList() {

		String query = String.format("select kullandirim_kodu, PKG_SOA_COMMON.text_translation%s"
				+ "(aciklama,'RUS') aciklama from cbs.CBS_KREDI_KULLANDIRIM_KODLARI%s" ,dbLinkName,dbLinkName);

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getCreditCustomerGroupList")
	public List<Map<String, Object>> getCreditCustomerGroupList() {

		String query = String.format("select * from cbs.CBS_CC_CUSTOMER_GROUP%s" ,dbLinkName);

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getAccCurrencyList")
	public List<Map<String, Object>> getAccCurrencyList() {

		String query = String.format("select doviz_kodu  from cbs.cbs_doviz_kodlari%s"
				+ " where doviz_kodu in ('KGS','USD','EUR','GBP','RUB','KZT','TRY') order by NUMARA desc " ,dbLinkName);

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getAccTypeList")
	public List<Map<String, Object>> getAccTypeList() {

		String query = String.format("select code, style_name from cbs.cbs_card_styles%s"
				+ " where status = 'A' and code <> 'D' ORDER BY case  "
				+ " when code='DCV' then 'AA' when code='VDG' then 'AB' else code end" ,dbLinkName);

		return reportProxy.getReportSQLResult(query);
	}
	@PostMapping("/getOtherAccountCurrencyList")
	public List<Map<String, Object>> getOtherAccountCurrencyList() {

		String query = String.format("select doviz_kodu,aciklama  from cbs.cbs_doviz_kodlari%s"
				+ " where doviz_kodu not in ('KGS', 'USD', 'EUR', 'GBP', 'RUB', 'KZT','TRY') order by doviz_kodu",dbLinkName);

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getDebitCardOptionList")
	public List<Map<String, Object>> getDebitCardOptionList() {

		String query = String.format("select DEBIT_CARD_OPTION_CODE, DEBIT_CARD_OPTION_DESC from "
				+ "(select decode(rownum, 1, 'Card Cancellation', 2, 'New PIN generation'"
				+ ") as DEBIT_CARD_OPTION_DESC," + "decode(rownum, 1, 'CANCEL CARD', 2, 'NEW PIN'"
				+ ") as DEBIT_CARD_OPTION_CODE from dual connect by level <= 2)");

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getCustomerTypeList")
	public List<Map<String, Object>> getCustomerTypeList() {

		String query = String.format("select CODE, STYLE_NAME from (select decode(rownum, 1, 'Retail', 2, 'Corporate') "
				+ "as STYLE_NAME, decode(rownum, 1, 'retail', 2, 'corporate') as CODE from dual connect by level <= 2) ");

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getInternetBankingModeList")
	public List<Map<String, Object>> getInternetBankingModeList() {

		String query = String.format("select CODE, STYLE_NAME from (select decode(rownum, 1, 'Full', 2, 'Viewer') "
				+ "as STYLE_NAME, decode(rownum, 1, 'full', 2, 'viewer') as CODE from dual connect by level <= 2) ");

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getInternetBankingStatusList")
	public List<Map<String, Object>> getInternetBankingStatusList() {

		String query = String.format("select CODE, STYLE_NAME from (select decode(rownum, 1, 'Disabled', 2, 'Enabled') "
				+ "as STYLE_NAME, decode(rownum, 1, 'sDISAB', 2, 'sENAB') as CODE from dual connect by level <= 2) ");

		return reportProxy.getReportSQLResult(query);
	}


	@PostMapping("/getAccountProductClassList")
	public List<Map<String, Object>> getAccountProductClassList() {

		String query = String.format("SELECT KOD, ACIKLAMA " + "FROM cbs.CBS_URUN_SINIF%s"
				+ " WHERE cbs.CBS_URUN_SINIF.MODUL_TUR_KOD = pkg_hesap.modul_tur_vadesiz%s"
				+ " and cbs.CBS_URUN_SINIF.URUN_TUR_KOD='DEMAND DEP' and ((KOD='NON INT.BEARING-LC' and aciklama='NON INT.BEARING-LC') "
				+ " or (KOD='NON INT.BEARING-FC'and aciklama='NON INT.BEARING-FC')) ",dbLinkName,dbLinkName);

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getCardsAccounts/{custNo}")
	public List<Map<String, Object>> getCardsAccounts(@PathVariable("custNo") String custNo) {

		String query = String.format("select d.card_id_no, TO_CHAR(h.hesap_no) hesap_no " + "from cbs.cbs_hesap%1$s"
				+ " h, cbs.cbs_debit_card_account%1$s"
				+ " d where d.customer_no = %2$s and d.account_no = h.hesap_no union all"
				+ " select c.card_id_no, c.PAYMENT_COLLECTION_ACC_NO from cbs.cbs_credit_card%1$s"
				+ " c where c.customer_no = %2$s",dbLinkName,custNo);

		return reportProxy.getReportSQLResult(query);
	}

	@PostMapping("/getDebitCardSecondaryAccounts/{custNo}")
	public List<Map<String, Object>> getDebitCardSecondaryAccounts(@PathVariable("custNo") String custNo) {

		String query = String.format("select hesap_no, doviz_kodu from cbs.cbs_hesap%s"
				+ " where musteri_no = %s AND DURUM_KODU = 'A' and urun_tur_kod = 'DEMAND DEP' "
				+ "AND URUN_SINIF_KOD IN ( 'INTEREST BEARING-LC', 'INTEREST BEARING-FC', 'NON INT.BEARING-LC', 'NON INT.BEARING-FC') "
				+ "AND doviz_kodu in ('KGS', 'USD', 'EUR') ",dbLinkName,custNo);

		return reportProxy.getReportSQLResult(query);
	}
	@PostMapping("/getCreditPaymentCollectionAccountList/{custNo}")
	public List<Map<String, Object>> getCreditPaymentCollectionAccountList(@PathVariable("custNo") String custNo) {

		String query = String.format("select * from cbs.cbs_hesap%s "
				+ " where urun_tur_kod ='DEMAND DEP' and  urun_sinif_kod = 'NON INT.BEARING-LC' "
				+ " and doviz_kodu = 'KGS' and musteri_no = %s and durum_kodu = 'A'",dbLinkName,custNo);

		return reportProxy.getReportSQLResult(query);
	}



}
