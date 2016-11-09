package com.bees.others;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class onlineFilter
 */
public class onlineFilter implements Filter {

	public void destroy() {
		System.out.println("onlineFilter destroying");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// HttpServletRequest httpRequest = (HttpServletRequest) request;
		// String url = httpRequest.getRequestURL().toString();
		// HttpSession session = httpRequest.getSession();
		// if (session.getAttribute("Manager") == null) {
		// request.getRequestDispatcher("/login.jsp").forward(request,
		// response);
		// }
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("onlineFilter initing");
	}

}
