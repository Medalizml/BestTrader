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

import managedBeans.Authentification;
import tn.esprit.Blues.entities.Administrator;
import tn.esprit.Blues.entities.Customer;

@WebFilter("/user/profile/*")
public class UserZone implements Filter{

	public UserZone() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Authentification session = (Authentification) req.getSession().getAttribute("auth");
		
		if(session!=null && session.isLoggedIn()&&session.getUser() instanceof Customer){
			System.out.println("filterUser userlogged");
			chain.doFilter(request, response);
			
		}else{
			System.out.println("filterUser user not logged");
			
			resp.sendRedirect(req.getContextPath() + "/login.jsf");
			
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
