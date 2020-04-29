package kg.demirbank.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kg.demirbank.proxy.ReportApiProxy;

@Service
public class CustomerSearchServiceImpl implements CustomerSearchService {

	@Autowired
	ReportApiProxy reportProxy;

	public List<Map<String, Object>> getCustomerSearchInfo(List<Map<String, Object>> searchData) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		if (searchData == null || searchData.isEmpty()) {
			map.put("return", "999");
			list.add(map);
			return list;
		}

		return searchData;
	}

}
