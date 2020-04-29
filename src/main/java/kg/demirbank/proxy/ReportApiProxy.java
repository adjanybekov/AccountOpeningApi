package kg.demirbank.proxy;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ReportsApi", url = "http://10.30.14.55:8083")
@RibbonClient(name = "ReportsApi")
public interface ReportApiProxy {

//	@PostMapping("/getReportDetailsResult/{id}")
//	public Mono<ReportDetails> getReportDetailsResult(@PathVariable(value = "id") Integer reportId);
//
//	@PostMapping("/getAllReports")
//	public List<Report> getAllReports();
//
//	@PostMapping("/getAllStatus")
//	public List<Status> getAllStatus();
//
//	@PostMapping("/getAllAppStatus")
//	public List<AppStatus> getAllAppStatus();
//
//	@PostMapping(path = "/getReport/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public Mono<Report> getReport(@PathVariable(value = "id") Integer reportId,
//			@RequestBody Map<String, Object> params);

	@PostMapping(path = "/getReportSQLResult", consumes = MediaType.TEXT_PLAIN_VALUE)
	public List<Map<String, Object>> getReportSQLResult(@RequestBody String query);


	@PostMapping(path = "/executeQuery")
	public boolean executeQuery(@RequestBody String query);
}
