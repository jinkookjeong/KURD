package iq.kurd.com.web;

import java.io.IOException;

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
 * @fileName  : ComCodeExtTag.java
 * @package   : iq.kurd.com.web
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class ComCodeExtTag extends SimpleTagSupport
{
	Log logger = LogFactory.getLog( ComCodeExtTag.class );

	protected String upCd;
	protected String cdExt;

	protected PageContext pageContext;

	/**
	 * 
	 * @methodName : getUpCd
	 * @return     : String
	 */
	public String getUpCd()
	{
		return upCd;
	}

	/**
	 * 
	 * @methodName : setUpCd
	 * @return     : void
	 * @param upCd
	 */
	public void setUpCd( String upCd )
	{
		this.upCd = upCd;
	}

	/**
	 * 
	 * @methodName : getCdExt
	 * @return     : String
	 */
	public String getCdExt()
	{
		return cdExt;
	}

	/**
	 * 
	 * @methodName : setCdExt
	 * @return     : void
	 * @param cdExt
	 */
	public void setCdExt( String cdExt )
	{
		//this.cd = cdExt;
		try
		{
			this.cdExt = (String)ExpressionEvaluatorManager.evaluate("cdExt", cdExt, String.class, pageContext);
		}
		catch (JspException e)
		{
			// TODO Auto-generated catch block
			logger.debug( e );
		}
	}

	public void doTag() throws JspException, IOException
	{
	    JspWriter out = getJspContext().getOut();

	    String sCodeResult = getCommonCode();

	    if( sCodeResult ==  null )
	    {
	    	sCodeResult = "";
	    }

	    if( sCodeResult.equals( "null" ) )
	    {
	    	sCodeResult = "";
	    }

	    out.print( sCodeResult );
	}

	/**
	 * 
	 * @methodName : getCommonCode
	 * @return     : String
	 */
	protected String getCommonCode()
	{
	    PageContext pctx = (PageContext)getJspContext();
	    WebApplicationContext wactx = WebApplicationContextUtils.getRequiredWebApplicationContext(pctx.getServletContext());

	    CoCommonCdService codeService = ( CoCommonCdService )wactx.getBean( "coCommonCdService" );

	    try
	    {
	    	String cdNmStr = "";
	    	if (!StringUtil.isEmpty(upCd) && !StringUtil.isEmpty(cdExt)) {
	    		CoCommonCdVO commonCode = codeService.getCommonCodeNameByCdExt(upCd, cdExt);
	    		if (commonCode != null && !StringUtil.isEmpty(commonCode.getCdNmStr())) {
	    			cdNmStr = commonCode.getCdNmStr();
	    		}
	    	}
	        return cdNmStr;
	    }
	    catch( Exception e )
	    {
	    	logger.debug( e );
	    }
	    return null;
	}
}
