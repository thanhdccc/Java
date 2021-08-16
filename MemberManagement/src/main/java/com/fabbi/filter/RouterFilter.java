package com.fabbi.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class URLFilter
 */
public class RouterFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RouterFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest requestTmp = (HttpServletRequest) request;
		HttpServletResponse responseTmp = (HttpServletResponse) response;
		
		String requestURI = requestTmp.getRequestURI();
		
		HttpSession session = requestTmp.getSession();
		
		if (session.getAttribute("username") == null && !requestURI.endsWith("login.jsp") && !requestURI.endsWith("login")) {
			responseTmp.sendRedirect("login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
