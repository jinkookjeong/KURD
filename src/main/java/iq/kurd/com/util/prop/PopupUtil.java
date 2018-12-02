package iq.kurd.com.util.prop;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import iq.kurd.com.KurdProperties;
import iq.kurd.com.util.format.SystemUtil;


 
public class PopupUtil extends SimpleTagSupport {
	 
	public void doTag () throws JspException {
		JspWriter out = getJspContext().getOut();
		String loc  = null;
		try {
			if(SystemUtil.isOsLocal()){
				loc = KurdProperties.getProperty("localDomain");
			} else {
				loc = KurdProperties.getProperty("nebidDomain");
			}
			out.print(loc);
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
	}
}
