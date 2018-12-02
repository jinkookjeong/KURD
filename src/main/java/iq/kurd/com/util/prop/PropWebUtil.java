package iq.kurd.com.util.prop;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import iq.kurd.com.util.format.WebContextUtil;

public class PropWebUtil extends SimpleTagSupport {
	
	private String keyName;
	private String keyValue;
	
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public void doTag () throws JspException {
		JspWriter out = getJspContext().getOut();
		try {
			out.print(WebContextUtil.getUrl(keyName));
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
	}
}
