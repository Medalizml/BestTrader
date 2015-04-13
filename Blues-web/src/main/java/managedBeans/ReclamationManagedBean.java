package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.Blues.ReclamationService.ReclServices;
import tn.esprit.Blues.entities.Reclamation;

@SessionScoped
@ManagedBean(name = "recl")
public class ReclamationManagedBean {

	@EJB
	ReclServices reclServices;
	private Reclamation reclamation;
	private List<Reclamation> Reclamations;

	public Reclamation getReclamation() {
		return reclamation;
	}
	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}
	public List<Reclamation> getReclamations() {
		return Reclamations;
	}
	public void setReclamations(List<Reclamation> reclamations) {
		Reclamations = reclamations;
	}
	public String doDeleteRec(Reclamation r){
		reclServices.remove(r);
		init();
		return null;
	}
	public String doDetails(Reclamation r) {
		setReclamation(r);
		System.out.println(r.getTitle());
		return "detailsEmail";

	}
	@PostConstruct
	public void init() {
		setReclamations(reclServices.findAll());
	}

}
