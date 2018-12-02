package iq.kurd.com.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import iq.kurd.com.co.code.service.CoCommonCdService;
import iq.kurd.com.co.code.vo.CoCommonCdVO;
import iq.kurd.com.util.format.StringUtil;
 

/**
 * 공통코드조회 : Common code management
 * @fileName  : CommonCodeTag.java
 * @package   : iq.kurd.com.web
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class CommonCodeTag extends SimpleTagSupport
{
	Log logger = LogFactory.getLog(CommonCodeTag.class);

	protected String upCd;
	protected String cd;
	protected String cdDesc;
	protected String print;

	protected PageContext pageContext;

	public String getUpCd() {
		return upCd;
	}

	public void setUpCd(String upCd) {
		this.upCd = upCd;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		// this.cd = cd;
		try {
			this.cd = (String) ExpressionEvaluatorManager.evaluate("cd", cd, String.class, pageContext);
		} catch (JspException e) {
			// TODO Auto-generated catch block
			logger.debug(e);
		}
	}
	
	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	public String getCdDesc() {
		return cdDesc;
	}

	public void setCdDesc(String cdDesc) {
		this.cdDesc = cdDesc;
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();

		String sCodeResult = getCommonCode();

		if (sCodeResult == null || sCodeResult.equals("null")) {
			sCodeResult = "";
		}

		out.print(sCodeResult);
	}

	/**
	 * 
	 * @methodName : getCommonCode
	 * @return : String
	 * @throws Exception 
	 */
	protected String getCommonCode() {
		String result = "";
		
		String nCd = this.cd;
		String nUpCd = this.upCd;
		String nCdDesc = this.cdDesc;
		
		if (!StringUtil.isEmpty(nCd) || !StringUtil.isEmpty(nUpCd)) {
			try {
				
				PageContext pctx = (PageContext) getJspContext();
				WebApplicationContext wactx = WebApplicationContextUtils.getRequiredWebApplicationContext(pctx.getServletContext());
				CoCommonCdService codeService = (CoCommonCdService) wactx.getBean("coCommonCdService");
				
				CoCommonCdVO coCommonCdVO = new CoCommonCdVO();
				coCommonCdVO.setCd(nCd);
				coCommonCdVO.setUpCd(nUpCd);
				coCommonCdVO.setCdDesc(nCdDesc);
				
				List<CoCommonCdVO> codeList = codeService.selectListCommonCode(coCommonCdVO);
				
				if (codeList.size() > 0) {
					ArrayList<String> list = new ArrayList<String>();
					for (CoCommonCdVO codeVO : codeList) {
//						if ("cdExt".equals(this.print)) {
//							list.add(codeVO.getCdExt());
//						} else if ("cdDesc".equals(this.print)) {
						if ("cdDesc".equals(this.print)) {
							list.add(codeVO.getCdDesc());
						} else {
							list.add(codeVO.getCdNm());
						}
					}
					result = list.toString().replaceAll("^\\[(.*)\\]$", "$1");
				}
			} catch (Exception e) {
				logger.debug(e);
			}
		}
		
		return result;
	}
}
