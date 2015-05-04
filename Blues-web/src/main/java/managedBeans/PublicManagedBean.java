package managedBeans;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import privateCompany.PrivateCompnayService;
import tn.esprit.Blues.entities.Share;
import tn.esprit.Blues.entities.Company;
import tn.esprit.Blues.entities.Sector;

@ManagedBean
@SessionScoped
public class PublicManagedBean {
	@EJB
	PrivateCompnayService privateCompnayService;

	private Company publicCompany;
	private Company newCompany = new Company();
	private List<Company> CompanyTable;
	private List<Sector> sectors;
	private String sectorN;
	private String datePublic;
	private Share share = new Share();
	private String dateIss;
	private String dateClos;

	public String getDateIss() {
		return dateIss;
	}

	public void setDateIss(String dateIss) {
		this.dateIss = dateIss;
	}

	public String getDateClos() {
		return dateClos;
	}

	public void setDateClos(String dateClos) {
		this.dateClos = dateClos;
	}

	public Company getPublicCompany() {
		return publicCompany;
	}

	public void setPublicCompany(Company publicCompany) {
		this.publicCompany = publicCompany;
	}

	public String getDatePublic() {
		return datePublic;
	}

	public void setDatePublic(String datePublic) {
		this.datePublic = datePublic;
	}

	@PostConstruct
	public void init() {
		setCompanyTable(privateCompnayService.findAllPublic());
		setSectors(privateCompnayService.findAllSector());
		setNewCompany(new Company());
		setpublicCompany(new Company());
		setSectorN(null);

	}

	public Company getpublicCompany() {
		return publicCompany;
	}

	public void setpublicCompany(Company publicCompany) {
		this.publicCompany = publicCompany;
	}

	public List<Company> getCompanyTable() {
		return CompanyTable;
	}

	public void setCompanyTable(List<Company> companyTable) {
		CompanyTable = companyTable;
	}

	public void doRemoveCompany(Company c) {

		privateCompnayService.remove(c);
		init();
	}

	public String doput(Company company) {
		this.publicCompany = company;
		System.out.println(publicCompany.getName());
		return "updatePublic";
	}
	public String doDetail(Company company) {
		this.publicCompany = company;
		
		return "Detail";
	}

	public String doAd() {
		return "addPublic";
	}

	public String doUpdate() {
		privateCompnayService.update(publicCompany,
				(Share) publicCompany.getQuotation());
		return "public";
	}

	@SuppressWarnings("deprecation")
	public String doAdd() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			datePublic = sdf.format(sdf.parse(datePublic));
			dateIss = sdf.format(sdf.parse(dateIss));
			dateClos = sdf.format(sdf.parse(dateClos));
			System.out.println(datePublic + "     1");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		Date date = new Date(datePublic);

		Integer year = date.getYear();

		Integer month = date.getMonth();

		Integer day = date.getDate();

		java.sql.Date date1 = new java.sql.Date(year, month, day);

		Date date2 = new Date(dateIss);

		Integer year2 = date2.getYear();

		Integer month2 = date2.getMonth();
		Integer day2 = date2.getDate();

		java.sql.Date dateI = new java.sql.Date(year2, month2, day2);

		Date date3 = new Date(dateClos);

		Integer year3 = date3.getYear();

		Integer month3 = date3.getMonth();

		Integer day3 = date3.getDate();

		java.sql.Date dateC = new java.sql.Date(year3, month3, day3);

		try {
			DemoBean.upload();
			System.out.println("iciiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		} catch (IOException e) {

			System.out.println("laaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}
		newCompany.setLogo("resource/img/"
				+ DemoBean.getFilename(DemoBean.file1));
		share.setCompany(newCompany);
		newCompany.setNature("public");
		newCompany.setSector(privateCompnayService.findSectorByName(sectorN));
		newCompany.setDateIncorporation(date1);
		privateCompnayService.add(newCompany, share);
		init();
		return "public?faces-redirect=true";
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
