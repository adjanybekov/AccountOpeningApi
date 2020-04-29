package kg.demirbank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kg.demirbank.model.CustomerSearch;
import kg.demirbank.proxy.ReportApiProxy;
import kg.demirbank.service.CustomerSearchService;
import kg.demirbank.utils.Common;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerSearchController {

	@Value("${query.dblink.name}")
	private String dbLinkName;

	@Autowired
	ReportApiProxy reportProxy;

	@Autowired
	CustomerSearchService customerSearchService;

	@PostMapping("/search")
	public Flux<Map<String, Object>> getCustomerList(@RequestBody CustomerSearch customerSearch) {
		return this.getCustomerList(10, 1, customerSearch);
	}

	@PostMapping("/search/{perPage}/{pageNo}")
	public Flux<Map<String, Object>> getCustomerList(@PathVariable("perPage") int perPage, @PathVariable("pageNo") int pageNo, @RequestBody CustomerSearch customerSearch) {
		String query;
		int to = perPage * pageNo;

		query = String.format(
				"select a.*, to_char(a.DOGUM_TARIHI,'dd-mm-yyyy') DOGUM_TARIHI_FMT from cbs_musteri%s a where musteri_tipi_kod = 1" + " and musteri_no = nvl('%s', musteri_no)"
						+ " and lower(soyadi) = lower(nvl('%s', soyadi))" + " and lower(isim) = lower(nvl('%s', isim))"
						+ " and lower(nvl(ikinci_isim, 'X')) = lower(nvl('%s', nvl(ikinci_isim, 'X')))"
						+ " and lower(nvl(vergi_no, 'X')) = lower(nvl('%s', nvl(vergi_no, 'X')))"
						+ " and nvl(company_of_the_staff, 0) = nvl('%s', nvl(company_of_the_staff, 0))"
						+ " and lower(nvl(pasaport_no, 'X')) = lower(nvl('%s', nvl(pasaport_no, 'X')))"
						+ " and dogum_tarihi = nvl2('%s', to_date('%s','dd.mm.yyyy'), dogum_tarihi)"
						+ " and rownum between 1 and %d", dbLinkName,
				Common.getValue(customerSearch.getCustomerNumber(), ""),
				Common.getValue(customerSearch.getSurname(), ""),
				Common.getValue(customerSearch.getName(), ""),
				Common.getValue(customerSearch.getSecondName(), ""),
				Common.getValue(customerSearch.getTaxNumber(), ""),
				Common.getValue(customerSearch.getStaffCompanyNumber(), ""),
				Common.getValue(customerSearch.getPassportNumber(), ""),
				Common.getValue(customerSearch.getBirthDate(), ""),
				Common.getValue(customerSearch.getBirthDate(), ""), to);

		List<Map<String, Object>> searchData = reportProxy.getReportSQLResult(query);

		customerSearchService.getCustomerSearchInfo(searchData);

		return Flux.fromIterable(searchData);
	}

	@PostMapping("/search/getCustomerSize")
	public Mono<Integer> getCustomerSize() {
		String query = String.format("select count(*) COUNT from cbs_musteri%s where musteri_tipi_kod is not null", dbLinkName);

		List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);

		Integer count = Integer.parseInt(result.get(0).get("COUNT").toString());

		return Mono.just(count);
	}

}