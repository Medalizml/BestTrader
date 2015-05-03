package managedBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tn.esprit.Blues.ArticleServices.ArticleServicesWeb;
import tn.esprit.Blues.entities.Article;


@ManagedBean(name = "ar")
@ViewScoped
public class ArticleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Article article;
	List<Article> articles = new ArrayList<Article>();

	
	private String dateTest;

	@EJB
	ArticleServicesWeb articleServicesWeb;

	public ArticleBean() {
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

	public String doUpdateArticle() {

		

		articleServicesWeb.update(article);
		article.setEditable(false);
		article = new Article();

		
		return null;

	}

	

	public String doAddArticle() {

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
		try {
			DemoBean.upload();
			System.out.println("iciiiii");
		} catch (IOException e) {
			
			System.out.println("laaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}
		article.setPicture("resource/img/"+DemoBean.getFilename(DemoBean.file1));
		articleServicesWeb.add(article);
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



	public void doDeleteArticle(Article article) {

		articleServicesWeb.remove(article);
		this.init();

	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}




}
