package managedBeansClient;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.Blues.entities.Article;
import tn.esprit.Blues.entities.Company;
import tn.esprit.Blues.entities.Sector;
import clientServices.homeServices;


@ManagedBean(name="home")
@SessionScoped
public class homeManagedBean {
	
	@EJB
	homeServices services;
	
	List<Article> articles;
	List<Sector> sectors;
	List<Long> nbCompany;
	List<Company> posEstim;
	List<Company> negEstim;
	List<Company> privates;
	List<Company> publics;
	List<Company> banks;

	public List<Company> getPrivates() {
		return privates;
	}

	public void setPrivates(List<Company> privates) {
		this.privates = privates;
	}

	public List<Company> getPublics() {
		return publics;
	}

	public void setPublics(List<Company> publics) {
		this.publics = publics;
	}

	public List<Company> getBanks() {
		return banks;
	}

	public void setBanks(List<Company> banks) {
		this.banks = banks;
	}

	public homeServices getServices() {
		return services;
	}

	public List<Company> getPosEstim() {
		return posEstim;
	}

	public void setPosEstim(List<Company> posEstim) {
		this.posEstim = posEstim;
	}

	public List<Company> getNegEstim() {
		return negEstim;
	}

	public void setNegEstim(List<Company> negEstim) {
		this.negEstim = negEstim;
	}

	public void setServices(homeServices services) {
		this.services = services;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public List<Long> getNbCompany() {
		return nbCompany;
	}

	public void setNbCompany(List<Long> nbCompany) {
		this.nbCompany = nbCompany;
	}
	
	@PostConstruct
	void init(){
		
		setArticles(services.getArticle());
		setSectors(services.findAllSectors());
		setNbCompany(services.nbrCompanyBySector());
		setPosEstim(services.findCompaniesPositivEstimation());
		setNegEstim(services.findCompaniesNegativEstimation());
		setBanks(services.findBanks());
		setPrivates(services.findPrivateCompanies());
		setPublics(services.findPublicCompanies());
	}

	

	
		
	}
