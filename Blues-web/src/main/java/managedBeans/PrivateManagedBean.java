package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import privateCompany.PrivateCompnayService;
import tn.esprit.Blues.entities.Company;

@ManagedBean
@ViewScoped
public class PrivateManagedBean {
@EJB
PrivateCompnayService privateCompnayService;

private Company privateCompany;
private List<Company> CompanyTable;


public PrivateManagedBean() {
	super();
	
}
@PostConstruct
public void init(){
	setCompanyTable(privateCompnayService.findAllPrivate());
	System.out.println(privateCompnayService.findAllPrivate());
	
}
public Company getPrivateCompany() {
	return privateCompany;
}
public void setPrivateCompany(Company privateCompany) {
	this.privateCompany = privateCompany;
}
public List<Company> getCompanyTable() {
	return CompanyTable;
}
public void setCompanyTable(List<Company> companyTable) {
	CompanyTable = companyTable;
}

}
