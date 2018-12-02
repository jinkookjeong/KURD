package iq.kurd.com.util.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import iq.kurd.com.util.format.WebContextUtil;

/**
 * 
 * @fileName  : SSO.java
 * @package   : iq.kurd.com.util.login
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class SSO extends SSOBase{
	 
	
	public SSO(PageContext pageContext) throws Exception
	{
		super(pageContext, WebContextUtil.getUrl("nebid") + "/um/lg/UmLgLogin.jsp", WebContextUtil.getUrl("nebid") + "/um/lg/UmLgLogin.jsp");
	}
 
	public SSO(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	     super(request, response, WebContextUtil.getUrl("nebid") + "/um/lg/UmLgLogin.jsp", WebContextUtil.getUrl("nebid") + "/um/lg/UmLgLogin.jsp");
	}

	public SSO(HttpServletRequest request) throws Exception{
		 super(request, null, WebContextUtil.getUrl("nebid") + "/um/lg/UmLgLogin.jsp", WebContextUtil.getUrl("nebid") + "/um/lg/UmLgLogin.jsp");
	}

	/**
	 * 
	 * @methodName : getUserId
	 * @return     : String
	 * @throws Exception
	 */
	public String getUserId() throws Exception{
		return getSSOLoginTokenValue("userId");
	}

	/**
	 * 
	 * @methodName : getUserCl
	 * @return     : String
	 * @throws Exception
	 */
	public String getUserCl() throws Exception{
		return getSSOLoginTokenValue("userCl");
	}

	/**
	 * 
	 * @methodName : getBizRegNo
	 * @return     : String
	 * @throws Exception
	 */
	public String getBizRegNo() throws Exception{
		return getSSOLoginTokenValue("masterCd");
	}

	/**
	 * 
	 * @methodName : getBizRegNm
	 * @return     : String
	 * @throws Exception
	 */
	public String getBizRegNm() throws Exception{
		return getSSOLoginTokenValue("supplierNm");
	}

	/**
	 * 
	 * @methodName : getUserNm
	 * @return     : String
	 * @throws Exception
	 */
	public String getUserNm() throws Exception{
		return getSSOLoginTokenValue("userNm");
	}

	/**
	 * 
	 * @methodName : getClientIp
	 * @return     : String
	 * @throws Exception
	 */
	public String getClientIp() throws Exception{
		return getSSOLoginTokenValue("clientIP");
	}

	/**
	 * 
	 * @methodName : getSupplierNm
	 * @return     : String
	 * @throws Exception
	 */
	public String getSupplierNm() throws Exception{
		return getSSOLoginTokenValue("supplierNm");
	}

	/**
	 * 
	 * @methodName : getStaffDeptNm
	 * @return     : String
	 * @throws Exception
	 */
	public String getStaffDeptNm() throws Exception{
		return getSSOLoginTokenValue("staffDeptNm");
	}

	/**
	 * 
	 * @methodName : getAuthCd
	 * @return     : String
	 * @throws Exception
	 */
	public String getAuthCd() throws Exception{
		return getSSOLoginTokenValue("authCd");
	}

	/**
	 * 
	 * @methodName : getUserDn
	 * @return     : String
	 * @throws Exception
	 */
	public String getUserDn() throws Exception{
		return getSSOLoginTokenValue("userDn");
	}
	
	/**
	 * 
	 * @methodName : getVersion
	 * @return     : String
	 * @throws Exception
	 */
	public String getVersion() throws Exception{
		return getSSOLoginTokenValue("version");
	}
	
	/**
	 * 
	 * @methodName : setVersion
	 * @return     : String
	 * @throws Exception
	 */
	public String setVersion(String version) throws Exception{
		return getSSOLoginTokenValue("version");
	}

	/**
	 * 
	 * @methodName : getSSOLoginTokenAll
	 * @return     : String
	 * @throws Exception
	 */
	public String getSSOLoginTokenAll() throws Exception{

		return getSSOLoginToken();
	}


}
