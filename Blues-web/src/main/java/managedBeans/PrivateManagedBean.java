package managedBeans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import privateCompany.PrivateCompnayService;
import tn.esprit.Blues.entities.Company;
import tn.esprit.Blues.entities.Sector;
import tn.esprit.Blues.entities.Share;

@ManagedBean
@SessionScoped
public class PrivateManagedBean {
	@EJB
	PrivateCompnayService privateCompnayService;

	private Company privateCompany;
	private Company newCompany=new Company();
	private List<Company> CompanyTable;
	private List<Sector> sectors;
	private String sectorN;
	private Share share=new Share();
	public PrivateManagedBean() {
		super();

	}

	@PostConstruct
	public void init() {
		setCompanyTable(privateCompnayService.findAllPrivate());
		System.out.println(privateCompnayService.findAllPrivate());
		setSectors(privateCompnayService.findAllSector());
		setNewCompany(new Company());
		setPrivateCompany(new Company());
		setSectorN(null);
		
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
	public void doRemoveCompany(Company c){
		
		privateCompnayService.remove(c);
		init();
	}
	public String doput(Company company){
		this.privateCompany=company;
		System.out.println(privateCompany.getName());
		return "updatePrivate";
	}
	public String doAd(){
		return "addPrivate";
	}
	public String doUpdate(){
		privateCompnayService.update(privateCompany,(Share)privateCompany.getQuotation());
		return "private";
	}
	public String doAdd(){
		try {
			DemoBean.upload();
			System.out.println("iciiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("laaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}
		newCompany.setLogo("resource/img/"+DemoBean.getFilename(DemoBean.file1));
		share.setCompany(newCompany);
		newCompany.setNature("private");
		newCompany.setSector(privateCompnayService.findSectorByName(sectorN));
		privateCompnayService.add(newCompany,share);
		init();
		return "private?faces-redirect=true";
	}

	public Company getNewCompany() {
		return newCompany;
	}

	public void setNewCompany(Company newCompany) {
		this.newCompany = newCompany;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public String getSectorN() {
		return sectorN;
	}

	public void setSectorN(String sectorN) {
		this.sectorN = sectorN;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

}
