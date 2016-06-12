/*
 * 
 */
package main.java.com.test.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {
    
    /*
     * Method that add the headers to Request in Filter
     * @param req Servlet Request 
     * @param res Servlet Response
     * @chain servlet filter chain
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	    throws IOException, ServletException {
	// filter to add the headers
	HttpServletResponse response = (HttpServletResponse) res;
	response.setHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
	response.setHeader("Access-Control-Max-Age", "3600");
	response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}