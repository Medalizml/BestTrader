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

@WebFilter("/admin/*")
public class AdminZone implements Filter{
	
	

	public AdminZone() {
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
		
		System.out.println("filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Authentification session = (Authentification) req.getSession().getAttribute("auth");
		
		if(session!=null && session.isLoggedIn()&&session.getUser() instanceof Administrator){
			System.out.println("filterAdmin");
			chain.doFilter(request, response);
			
		}else{
			System.out.println("filterAdmin");
			
			resp.sendRedirect(req.getContextPath() + "/login.jsf");
			
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
