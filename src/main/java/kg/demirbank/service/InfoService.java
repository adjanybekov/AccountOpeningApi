package kg.demirbank.service;

import java.util.List;
import java.util.Map;

public interface InfoService {

	public List<Map<String, Object>> getBranchList();

	public List<Map<String, Object>> getCustomerTypeList();

	public List<Map<String, Object>> getCustomerGroupList();

	public List<Map<String, Object>> getIdTypeList();

	public List<Map<String, Object>> getMaritalStatusList();

	public List<Map<String, Object>> getNationalityList();

	public List<Map<String, Object>> getProfessionList();

	public List<Map<String, Object>> getRatingList();

	public List<Map<String, Object>> getResidentialStatusList();

	public List<Map<String, Object>> getAddressTypeList();

	public List<Map<String, Object>> getCityList();

	public List<Map<String, Object>> getCountryList();

	public List<Map<String, Object>> getAllCustomerListSize();

}
