package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.Blues.DashboardServices.DashboardServices;
import tn.esprit.Blues.entities.Article;
import tn.esprit.Blues.entities.Company;
import tn.esprit.Blues.entities.Customer;
import tn.esprit.Blues.entities.Portfolio;

@SessionScoped
@ManagedBean(name="Dash")
public class DashboardManagedBean {

	@EJB
	DashboardServices services;
	
	private List<Customer> inactifs;
	private List<Portfolio> ranking;
	private List<Company> estimCompanies;
	private List<Article> articles;
	
	
	
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Company> getEstimCompanies() {
		return estimCompanies;
	}

	public void setEstimCompanies(List<Company> estimCompanies) {
		this.estimCompanies = estimCompanies;
	}

	public List<Portfolio> getRanking() {
		return ranking;
	}

	public void setRanking(List<Portfolio> ranking) {
		this.ranking = ranking;
	}

	public List<Customer> getInactifs() {
		return inactifs;
	}

	public void setInactifs(List<Customer> inactifs) {
		this.inactifs = inactifs;
	}
	
	@PostConstruct
	void init(){
		
		setInactifs(services.getInactifs());
		setRanking(services.getRanking());
		setEstimCompanies(services.findAll());
		setArticles(services.getArticle());
	}
}
