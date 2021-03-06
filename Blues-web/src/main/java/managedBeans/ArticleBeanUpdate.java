package managedBeans;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.Blues.ArticleServices.ArticleServicesWeb;
import tn.esprit.Blues.entities.Article;
import tn.esprit.Blues.entities.Portfolio;


@ManagedBean(name = "ar1")
@SessionScoped
public class ArticleBeanUpdate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Article article;
	List<Article> articles = new ArrayList<Article>();
	


	private String dateTest;

	@EJB
	ArticleServicesWeb articleServicesWeb;

	public ArticleBeanUpdate() {
		article = new Article();

	}

	@PostConstruct
	public void init() {
		setArticles(articleServicesWeb.findAll());
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String saveAction() {

		// get all existing value but set "editable" to false

		articleServicesWeb.update(article);
		article.setEditable(false);
		article = new Article();

		// return to current page
		return null;

	}

	

	public String inscrire() {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			dateTest = sdf.format(sdf.parse(dateTest));
			System.out.println(dateTest + "     1");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		Date date = new Date(dateTest);

		Integer year = date.getYear();
		System.out.println(year);
		Integer month = date.getMonth();
		System.out.println(month);
		Integer day = date.getDate();
		System.out.println(day);
		java.sql.Date date1 = new java.sql.Date(year, month, day);
		article.setDate(date1);
		System.out.println(date1);

		System.out.println(date);

		articleServicesWeb.add(article);
		// FacesMessage message = new FacesMessage("Succ�s de l'inscription !");
		// FacesContext.getCurrentInstance().addMessage(null, message);
		article = new Article();
		this.init();

		return null;
	}

	public String getDateTest() {
		return dateTest;
	}

	public void setDateTest(String dateTest) {
		this.dateTest = dateTest;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String cancel() {

		article = new Article();
		this.init();
		return null;
	}

	public void delete(Article article) {

		articleServicesWeb.remove(article);
		this.init();

	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String doInsert(Article art) {
		
		setArticle(art);
		System.out.println(article.getAuthor());
		System.out.println(article.getDate());
		return "update";

	}

	public String doUpdateAR() {
		java.sql.Date date1;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			dateTest = sdf.format(sdf.parse(dateTest));
			System.out.println(dateTest + "     1");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		Date date = new Date(dateTest);

		Integer year = date.getYear();
		System.out.println(year);
		Integer month = date.getMonth();
		System.out.println(month);
		Integer day = date.getDate();
		System.out.println(day);
	    date1 = new java.sql.Date(year, month, day);
		article.setDate(date1);
		System.out.println(article.getDate());
		articleServicesWeb.update(article);
		
		return "Article";
	}
	
	public String donavigation( Article a)
	{article=a;
	return "Detail"; 
	}


	
	

}
