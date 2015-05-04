package managedBeans;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class DemoBean {

	public static Part file1;

	public Part getFile1() {
		return file1;
	}

	public void setFile1(Part file1) {
		DemoBean.file1 = file1;
	}

	public static String upload() throws IOException {
		System.out.println("laaa");
		file1.write("C:\\Users\\asus\\git\\bestTrader-admin-web\\Blues-web\\src\\main\\webapp\\resource\\img\\"
				+ getFilename(file1));
		
		System.out.println("hedhi mchet");
		file1.write("D:\\JavaEE\\Serveur\\wildfly-8.2.0.Final\\standalone\\deployments\\Blues-web.war\\resource\\img\\"
				+ getFilename(file1));
		// System.out.println(getFilename(file1));
		return "success";
	}

	public static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}
}
