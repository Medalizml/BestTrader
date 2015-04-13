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

@WebFilter({ "/welcome.xhtml","/companies/*",
		"/estimation/*", "/Article/*", "/dashbord.xhtml",
		"/users/*", "/mail/*","/statitiques/*"})
public class AdminZone implements Filter{

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
		if(!session.isLoggedIn()){
			System.out.println("not logged in");
			resp.sendRedirect(req.getContextPath() + "/login.jsf");
		}else{
			System.out.println("logged in");
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
