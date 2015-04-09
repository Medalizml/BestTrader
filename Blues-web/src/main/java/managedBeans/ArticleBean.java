package managedBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;



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
	List<Article> articles=new ArrayList<Article>();
	
	private String destination="resource\\img\\";
	private String serveurPath="C:\\JAVAEE\\serveur\\wildfly-8.2.0.Final\\standalone\\deployments\\Blues-web.war\\";
	
	
	
	
	
	
	
	@EJB
	ArticleServicesWeb articleServicesWeb;
	
	 

	public ArticleBean() {
		article=new Article();
		
	}

	@PostConstruct
	public void init(){
		setArticles(articleServicesWeb.findAll());
	}
	
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}


	
	public String saveAction() {
		 
		//get all existing value but set "editable" to false 
		
			articleServicesWeb.update(article);
			article.setEditable(false);
			article = new Article();
			
		
		//return to current page
		return null;
 
	}
 
	public String editAction(Article article) {
 
		article.setEditable(true);
		return null;
	}
	
	
	public String inscrire() {
		
		
		 
		
		articleServicesWeb.add(article);
		//FacesMessage message = new FacesMessage("Succès de l'inscription !");
		//FacesContext.getCurrentInstance().addMessage(null, message);
		article = new Article();
		this.init();
	
		return null;
	}
	 public void addMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	
	public String cancel () {
		
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


	
	 
	    
	    /**public String getSelectedItems() {

	        // Get selected items.
	        articles = new ArrayList<Article>();
	        for (Article article : 	articles) {
	            if (article.isSelected()) {
	                articles.add(article);
	                article.setSelected(false); // Reset.
	            }
	        }

	        // Do your thing with the MyData items in List selectedDataList.

	        return "selected"; // Navigation case.
	    }
	    **/
	    
	    
	 
	    
//	    
//		 public void upload() {
//			 
//			
//		        if(file != null) {
//		            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//		            FacesContext.getCurrentInstance().addMessage(null, message);
//		            try {
//		                copyFile(file.getFileName(), file.getInputstream());
//		                
//		                System.out.println(file.getFileName());
//		            } catch (IOException e) {
//		                e.printStackTrace();
//		                System.out.println("ioexception");
//		            }
//		        }else{
//		        	System.out.println("null");
//		        }
//		    }
	    
		
	    
	    
	    
	
	
}
