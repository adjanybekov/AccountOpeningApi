package kg.demirbank.service;

import java.util.List;
import java.util.Map;

public interface CustomerSearchService {

	public List<Map<String, Object>> getCustomerSearchInfo(List<Map<String, Object>> searchData);

}
