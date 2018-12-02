package iq.kurd.com.util.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Signature;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import iq.kurd.com.KurdProperties;

//import com.kica.server.api.EbidApi;

import iq.kurd.com.util.format.StringUtil;
import iq.kurd.com.util.format.SystemUtil;
 

/**
 * 
 * @fileName  : SSOBase.java
 * @package   : iq.kurd.com.util.login
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class SSOBase
{
  private HttpServletRequest request;
  private HttpServletResponse response;

  private String ssoLoginToken;
  private String ssoLoginTokenKey;
  private String ssoLoginTokenSign;
  private String ssoLoginTokenValue;

  private String ssoLoginTokenState = "NULL";

  private String ssoLoginURL;
  private String ssoLoginURL2;

  
 
public SSOBase(PageContext pageContext, String ssoLoginURL, String ssoLoginURL2) throws Exception
  {
	this((HttpServletRequest)pageContext.getRequest(),
	(HttpServletResponse)pageContext.getResponse(),
	ssoLoginURL, ssoLoginURL2);

  }

  public SSOBase(HttpServletRequest request, HttpServletResponse response, String ssoLoginURL, String ssoLoginURL2) throws Exception
  {
        this.ssoLoginTokenState = "NULL";

        this.request = request;
        this.response = response;

        this.ssoLoginURL = ssoLoginURL;
        this.ssoLoginURL2 = ssoLoginURL2;

       Cookie cookie1 = getCookie("_SLT");
       Cookie cookie2 = getCookie("_SLTK");
        Cookie cookie3 = getCookie("_SLTS");

        if (cookie1 != null)
          this.ssoLoginToken = URLDecoder.decode(cookie1.getValue(), "UTF-8");
        if (cookie2 != null)
          this.ssoLoginTokenKey = URLDecoder.decode(cookie2.getValue(), "UTF-8");
        if (cookie3 != null)
          this.ssoLoginTokenSign = URLDecoder.decode(cookie3.getValue(), "UTF-8");
  }

 /**
 * 
 * @methodName : getSSOLoginTokenValue
 * @return     : String
 * @param name
 * @throws Exception
 */
public String getSSOLoginTokenValue(String name) throws Exception
  {
        if (!(validateSSOLoginToken())) return null;
        if (this.ssoLoginTokenValue == null) return null;

        String tokenValue = getTokenValue(this.ssoLoginTokenValue, name);

        if (tokenValue == null) return "";

        return URLDecoder.decode(tokenValue, "UTF-8");
  }


  /**
 * 
 * @methodName : getSSOLoginToken
 * @return     : String
 * @throws Exception
 */
public String getSSOLoginToken() throws Exception
  {
	  if (!(validateSSOLoginToken())) return null;
	  if (this.ssoLoginTokenValue == null) return null;

	  return URLDecoder.decode(this.ssoLoginTokenValue, "UTF-8");
  }

 /**
 * 
 * @methodName : login
 * @return     : void
 * @throws Exception
 */
public void login() throws Exception
  {
       String url = null;

       if (this.ssoLoginURL == null)
      try {
           this.response.sendRedirect("about:blank");
      }
      catch (IOException localIOException) {
      }
      catch (IllegalStateException localIllegalStateException) {
      }
      String retPage = getReturnPage();

       if ((retPage == null) || (retPage.equals(""))) {
         url = this.ssoLoginURL;

         if ((!(this.ssoLoginTokenState.equals("NULL"))) &&
           (!(this.ssoLoginTokenState.equals("VALID"))))
           url = url + "?err_cd=" + URLEncoder.encode(this.ssoLoginTokenState, "UTF-8");
    }
    else
    {
         url = this.ssoLoginURL + "?ret_page=" + URLEncoder.encode(retPage, "UTF-8");

         if ((!(this.ssoLoginTokenState.equals("NULL"))) &&
           (!(this.ssoLoginTokenState.equals("VALID")))) {
           url = url + "&err_cd=" + URLEncoder.encode(this.ssoLoginTokenState, "UTF-8");
      }
    }
    try
    {
         this.response.sendRedirect(url);
    } catch (IOException localIOException1) {
    }
    catch (IllegalStateException localIllegalStateException1) {
    }
  }

/**
 * 
 * @methodName : login2
 * @return     : void
 * @throws Exception
 */
public void login2() throws Exception {
       String url = null;

       if (this.ssoLoginURL2 == null)
      try {
           this.response.sendRedirect("about:blank");
      }
      catch (IOException localIOException) {
      }
      catch (IllegalStateException localIllegalStateException) {
      }
       String retPage = getReturnPage();

       if ((retPage == null) || (retPage.equals(""))) {
         url = this.ssoLoginURL2;

         if ((!(this.ssoLoginTokenState.equals("NULL"))) &&
           (!(this.ssoLoginTokenState.equals("VALID"))))
           url = url + "?err_cd=" + URLEncoder.encode(this.ssoLoginTokenState, "UTF-8");
    }
    else
    {
         url = this.ssoLoginURL2 + "?ret_page=" + URLEncoder.encode(retPage, "UTF-8");

         if ((!(this.ssoLoginTokenState.equals("NULL"))) &&
           (!(this.ssoLoginTokenState.equals("VALID")))) {
           url = url + "&err_cd=" + URLEncoder.encode(this.ssoLoginTokenState, "UTF-8");
      }
    }
    try
    {
         this.response.sendRedirect(url);
    }
    catch (IOException localIOException1) {
    }
    catch (IllegalStateException localIllegalStateException1) {
    }
  }

 /**
 * 
 * @methodName : login3
 * @return     : void
 * @throws Exception
 */
public void login3() throws Exception {
       String url = null;

       if (this.ssoLoginURL == null)
      try {
           this.response.sendRedirect("about:blank");
      }
      catch (IOException localIOException) {
      }
      catch (IllegalStateException localIllegalStateException) {
      }
       String retPage = getReturnPage();

       if ((retPage == null) || (retPage.equals(""))) {
         url = this.ssoLoginURL + "&popupFlag=Y";

         if ((!(this.ssoLoginTokenState.equals("NULL"))) &&
           (!(this.ssoLoginTokenState.equals("VALID"))))
           url = url + "?err_cd=" + URLEncoder.encode(this.ssoLoginTokenState, "UTF-8");
    }
    else
    {
         url = this.ssoLoginURL + "?ret_page=" + URLEncoder.encode(retPage, "UTF-8") + "&popupFlag=Y";

         if ((!(this.ssoLoginTokenState.equals("NULL"))) &&
           (!(this.ssoLoginTokenState.equals("VALID")))) {
           url = url + "&err_cd=" + URLEncoder.encode(this.ssoLoginTokenState, "UTF-8");
      }
    }
    try
    {
         this.response.sendRedirect(url);
    }
    catch (IOException localIOException1)
    {
    }
    catch (IllegalStateException localIllegalStateException1) {
    }
  }

 

	/**
	 * 
	 * @methodName : isLogin
	 * @return     : boolean
	 * @return
	 * @throws Exception
	 */
	public boolean isLogin() throws Exception{
       if (this.ssoLoginToken == null) return false;
       if (!(validateSSOLoginToken())) return false;

       String loginType = getSSOLoginTokenValue("ltype");
       if (loginType == null || !loginType.equals("C")) {
           return false;
       }

       return true;
  }

 /**
 * 
 * @methodName : isLogin2
 * @return     : boolean
 * @return
 * @throws Exception
 */
 public boolean isLogin2() throws Exception
  {
	  if(ssoLoginToken == null){
          return false;
	  }
	  String id = getSSOLoginTokenValue("id");
	  if (id == null || id.equals(""))
	  {
	      return false;
	  }
        return validateSSOLoginToken();

  }

 /**
 * 
 * @methodName : checkLogin
 * @return     : void
 * @throws Exception
 */
 public void checkLogin() throws Exception
  {
       if (!(isLogin()))
         login();
  }

 /**
 * 
 * @methodName : checkLogin2
 * @return     : void
 * @throws Exception
 */
 public void checkLogin2() throws Exception
  {
       if (!(isLogin2()))
        login2();
  }

 /**
 * 
 * @methodName : checkLogin3
 * @return     : void
 * @throws Exception
 */
 public void checkLogin3() throws Exception
  {
       if (!(isLogin()))
         login3();
  }
 
 /**
 * 
 * @methodName : validateSSOLoginToken
 * @return     : boolean
 * @return
 */
  private boolean validateSSOLoginToken()
  {
		ssoLoginTokenValue = "&userId=letech&userCl=userCl&masterCd=masterCd&userNm=엘이테크&clientIP=127.0.0.1&supplierNm=공급업체명&staffDeptNm=부서명&authCd=authCd&userDn=e=acheteur1@acheteur1.tn,cn=Acheteur 1,o=ACHETEUR 1,c=TN";
		return true;

		/*
		if (!ssoLoginTokenState.equals("NULL")) {
          if (ssoLoginTokenState.equals("VALID")) { return true;  }
          else                                    { return false; }
      }

       if ((this.ssoLoginToken == null) ||
         (this.ssoLoginTokenKey == null) ||
         (this.ssoLoginTokenSign == null)) {
         this.ssoLoginTokenState = "NULL";
         return false;
       }

       String token = null;

	 try {

         String kicaUrl = "";

 		 if(SystemUtil.isOsLocal()){
 			kicaUrl = PpsProperties.getProperty("LOCAL_KICA_LIB_PATH");
 		 }else{
 			kicaUrl = PpsProperties.getProperty("SERVER_KICA_LIB_PATH");
 		 }
                
    	 EbidApi api = EbidApi.getInstance(kicaUrl);
    	 Map<String, String> cookieMap = new HashMap<String, String>();
    	 cookieMap.put("_SLT", this.ssoLoginToken);
    	 cookieMap.put("_SLTK", this.ssoLoginTokenKey);
    	 cookieMap.put("_SLTS", this.ssoLoginTokenSign);

    	 token = api.verifySSOValue("server", cookieMap);

    	 if(token == null || token.length() == 0)
    	 {
    	 //SSO 검증 실패 에러처리
    	 return false;
    	 }
        	 
     }catch (Exception ex) { 
    	ex.printStackTrace();
    }

	//만료일자
    String expireDate = getTokenValue(token, "expire");

    Date ctime = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String now = (String) dateFormat.format(ctime);
    if (expireDate.compareTo(now) < 0) {
        ssoLoginTokenState = "TIMEOUT";
        return false;
    }

    String tokenIP  = getTokenValue(token, "clientIp");
    String remoteIP = request.getHeader("x-forwarded-for") == null?request.getRemoteAddr():request.getHeader("x-forwarded-for");


    if (tokenIP == null) {
        ssoLoginTokenState = "INVALID";
        return false;
    }

    if (!tokenIP.equals(remoteIP)) {
    }

    ssoLoginTokenValue = token;
    ssoLoginTokenState = "VALID";
    return true;
		 */
  }

	 /**
	 * 
	 * @methodName : getReturnPage
	 * @return     : String
	 * @return
	 */
	private String getReturnPage()
	  {
	       String retPage = null;
	
	       String qs = this.request.getQueryString();
	       if (qs == null) {
	         Enumeration<?> paramNames = this.request.getParameterNames();
	         if (paramNames.hasMoreElements()) {
	           retPage = this.request.getHeader("REFERER");
	      }
	      else
	           retPage = this.request.getRequestURL().toString();
	    }
	    else
	    {
	         retPage = this.request.getRequestURL().toString() + "?" + qs;
	    }
	
	       return retPage;
	  }

  /**
 * 
 * @methodName : getCookie
 * @return     : Cookie
 * @param name
 * @return
 */
  private Cookie getCookie(String name)
  {
       Cookie[] cookies = this.request.getCookies();
       if (cookies == null) {
         return null;
    }
       Cookie cookie = null;

       int count = cookies.length;

       for (int i = count - 1; i >= 0; --i) {
         cookie = cookies[i];
         if (cookie.getName().equals(name)) {
           return cookie;
      }
    }

       return null;
  }

 /**
 * 
 * @methodName : getTokenValue
 * @return     : String
 * @param str
 * @param name
 */
  private String getTokenValue(String str, String name)
  {
	  if(str == null){
		  return null;
	  }
       StringTokenizer st1 = new StringTokenizer(str, "&");
       while (st1.hasMoreTokens()) {
         StringTokenizer st2 = new StringTokenizer(st1.nextToken(), "=");
         String tokenName = st2.nextToken();

         if (tokenName.equals(name)) {
             if ( st2.hasMoreTokens() ) {
                 String tokenValue = st2.nextToken();
                 return tokenValue;
             }
         }
    }

       return null;
  }

}


