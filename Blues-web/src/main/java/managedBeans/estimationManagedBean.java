package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.Blues.EstimationService.EstimServices;
import tn.esprit.Blues.entities.Company;
import tn.esprit.Blues.entities.Quotation;

@ViewScoped
@ManagedBean(name="estim")
public class estimationManagedBean {
	
	@EJB
	EstimServices estimServices;
	private List<Company> companies;
	private float newEstim;
	private Quotation quotation;
	private Company selectedCompnay;
	
	




	public Quotation getQuotation() {
		return quotation;
	}



	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}



	public List<Company> getCompanies() {
		return companies;
	}



	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}



	public float getNewEstim() {
		return newEstim;
	}



	public void setNewEstim(float newEstim) {
		this.newEstim = newEstim;
	}
	


public Company getSelectedCompnay() {
		return selectedCompnay;
	}



	public void setSelectedCompnay(Company selectedCompnay) {
		this.selectedCompnay = selectedCompnay;
	}



public String doUpdate(Quotation s){
		
		
		estimServices.updateEstimation(s);
	
		return null;
	}
public String doUpdatedec(Quotation s){
	
	s.setEstimation(-s.getEstimation());
	estimServices.updateEstimation(s);

	return null;
}

public void openModal(Company c){
	 selectedCompnay=c;
	 System.out.println("zzzzzzzzzzzzz" + selectedCompnay.getName());
		 
	 }

	@PostConstruct
	public void init() {
		setCompanies(estimServices.findAll());
		quotation= new Quotation();
	
		
	}
	
	
	
	
	
}
