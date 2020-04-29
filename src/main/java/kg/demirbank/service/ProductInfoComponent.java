package kg.demirbank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import kg.demirbank.proxy.ReportApiProxy;

@Component
public class ProductInfoComponent {

    @Autowired
    InfoServiceImpl service;

    @Value("${query.dblink.name}")
    private String dbLinkName;

    @Autowired
    ReportApiProxy reportProxy;


    private List<Map<String, Object>> debitCardList;
    private List<Map<String, Object>> branchList;
    private List<Map<String, Object>> creditProductCodeList;

    public List<Map<String, Object>> getDebitCardList() {
    	if (debitCardList == null || debitCardList.isEmpty())
    		this.initDebitCardTypeList();

    	return debitCardList;
    }

    public List<Map<String, Object>> getBranchList() {
    	if (branchList == null || branchList.isEmpty())
    		this.initBranchList();
    	
        return branchList;
    }

    public List<Map<String, Object>> getCreditProductCodeList() {
    	if (creditProductCodeList == null || creditProductCodeList.isEmpty())
    		this.initCreditProductCodeList();

    	return creditProductCodeList;
    }

//    @PostConstruct
    public void init(){
        initDebitCardTypeList();
        initBranchList();
        initCreditProductCodeList();
    }

    public void initDebitCardTypeList(){
        debitCardList = new ArrayList<>();
        service.getDebitCardTypeMap().stream().forEach(m -> debitCardList.add(m));
    }
    public void initBranchList(){
        branchList = new ArrayList<>();
        service.getBranchMap().stream().forEach(m -> branchList.add(m));
    }
    public void initCreditProductCodeList(){
        creditProductCodeList = new ArrayList<>();
        service.getCreditProductCodeMap().stream().forEach(m -> creditProductCodeList.add(m));
    }


}
