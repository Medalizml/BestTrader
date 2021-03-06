package filter;

import java.io.IOException;




import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tn.esprit.Blues.entities.Administrator;
import tn.esprit.Blues.entities.Customer;
import managedBeans.Authentification;

@WebFilter("/login.jsf")
public class LoginFilter implements Filter {


	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		System.out.println("filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Authentification session = (Authentification) req.getSession().getAttribute("auth");
		
		if(session!=null && session.isLoggedIn()&& session.getUser() instanceof Administrator){
			System.out.println((session.getUser() instanceof Administrator)+"filter login");
			
			resp.sendRedirect(req.getContextPath() + "/admin/welcome.xhtml");
		}else{
			System.out.println("filterlogin");
			chain.doFilter(request, response);
			
			
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
