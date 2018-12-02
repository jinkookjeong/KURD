package iq.kurd.com.web;

import java.io.IOException;
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
import iq.kurd.com.util.format.LocaleUtil;
 
 
/**
 * 공통코드조회 : Common code management
 * @fileName  : ComCodeTag.java
 * @package   : iq.kurd.com.web
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class ComCodeTag extends SimpleTagSupport
{
	Log logger = LogFactory.getLog( ComCodeTag.class );
  
	protected String upCd;
	protected String cd;

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
	 * @methodName : getCd
	 * @return     : String
	 */
	public String getCd()
	{
		return cd;
	}

	/**
	 * 
	 * @methodName : setCd
	 * @return     : void
	 * @param cd
	 */
	public void setCd( String cd )
	{
		//this.cd = cd;
		try
		{
			this.cd = (String)ExpressionEvaluatorManager.evaluate("cd", cd, String.class, pageContext);
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

	    	if("".equals(this.cd) || this.cd == null){

	    		return "";
	    	}

	    	CoCommonCdVO regCodeVO = new CoCommonCdVO();

	    	String langCode = "ko";
	    	
	    	regCodeVO.setLoc(langCode);
	    	regCodeVO.setUpCd( this.upCd );
	    	regCodeVO.setCd( this.cd );

	    	// 리스트 이나 단건으로 조회됨
	    	List<CoCommonCdVO> resultRow = codeService.selectListCommonCode(regCodeVO);

	        return resultRow.get(0).getCdNm();
	    }
	    catch( Exception e )
	    {
	    	logger.debug( e );
	    }

	     return null;
	}
}
