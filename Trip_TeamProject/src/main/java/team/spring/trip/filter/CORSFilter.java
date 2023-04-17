package team.spring.trip.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		String origin = request.getHeader("origin");
		response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT"); 
		response.setHeader("Access-Control-Max-Age", "3600"); 
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

}
