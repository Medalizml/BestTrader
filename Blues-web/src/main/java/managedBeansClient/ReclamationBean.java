package managedBeansClient;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tn.esprit.Blues.ReclamationService.ReclServices;
import tn.esprit.Blues.entities.Customer;
import tn.esprit.Blues.entities.Reclamation;

@ViewScoped
@ManagedBean
public class ReclamationBean {
	@ManagedProperty("#{auth.customer}")
	private Customer customer;
	@EJB
	private ReclServices reclServices;
	private List<Reclamation> recList;
	private Reclamation reclamation;

	public Reclamation getReclamation() {
		return reclamation;
	}

	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Reclamation> getRecList() {
		return recList;
	}

	public void setRecList(List<Reclamation> recList) {
		this.recList = recList;
	}
	public void addRec(){
		
		Date d = new Date();
		reclamation.setDate(d);
		reclamation.setSender(customer);
		reclServices.addReclamation(reclamation);
		this.Init();
		
	}

	@PostConstruct
	public void Init() {
		reclamation = new Reclamation();
		setRecList(reclServices.findAll(customer));
	}

}
