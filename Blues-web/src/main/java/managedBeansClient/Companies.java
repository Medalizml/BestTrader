package managedBeansClient;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.Blues.entities.Bank;
import tn.esprit.Blues.entities.Company;
import ScreenersServices.ScreenersServices;

@ManagedBean
@SessionScoped

public class Companies {
	
	
	@EJB
	ScreenersServices services;
	
	private List<Company> listPrivate;
	private List<Company> listPublic;
	private List<Bank> listBank;
	private Company company=new Company();
	
	
	public String detailsCompany(Company c) {
		setCompany(c);
		return "companyDetails";
	}
	
	public String lienCompany(){
		
		if(company.getNature().equals("public")){
			return	"companiesPublic.xhtml";

		}
		else if (company.getNature().equals("private")){
			return	"companiesPrivate.xhtml";

		}
		else {
		return	"bank.xhtml";
		}
	}
	
	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}

	@PostConstruct
	public void init(){
		setListPrivate(services.findAllPrivate());
		setListPublic(services.findAllPublic());
		setListBank(services.findAllBank());
	}


	public List<Company> getListPrivate() {
		return listPrivate;
	}


	public void setListPrivate(List<Company> listPrivate) {
		this.listPrivate = listPrivate;
	}


	public List<Company> getListPublic() {
		return listPublic;
	}


	public void setListPublic(List<Company> listPublic) {
		this.listPublic = listPublic;
	}


	public List<Bank> getListBank() {
		return listBank;
	}


	public void setListBank(List<Bank> listBank) {
		this.listBank = listBank;
	}

	

}
