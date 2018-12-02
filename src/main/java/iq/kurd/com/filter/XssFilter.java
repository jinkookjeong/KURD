/**
 * 
 * 1. Class Name : XssFilter.java
 * 2. Description : 클래스 설명
 * Modification information
 *
 * 수정일			수정자		수정내용
 * ----------   	-----       ------------------------
 * 2013. 8. 30.      psh        최초생성      
 *         
 * @author psh
 * @since 2013. 8. 30.
 * @virsion 1.0
 */
package iq.kurd.com.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XssFilter implements Filter 
{
	private FilterConfig fc;
	public void destroy() {
		this.fc = null; 
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new RequestWrapper((HttpServletRequest)req), resp);

	}

	public void init(FilterConfig fc) throws ServletException {
		this.fc = fc;
	}
}