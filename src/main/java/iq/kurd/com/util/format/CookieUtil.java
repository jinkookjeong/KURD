package iq.kurd.com.util.format;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import iq.kurd.com.constant.Constant;
import iq.kurd.com.util.login.SSO;

/**
 * 쿠키값을 생성 한다. :: cookie util
 * @fileName  : CookieUtil.java
 * @package   : iq.kurd.com.util.format
 * @since     : 2018. 2. 21
 * @author    : Jin Kook JEONG
 */
public class CookieUtil {

	  
	
	/**
	 * 쿠키값을 가져온다. : get Cookie Value
	 * @methodName : getCookieValue
	 * @return     : String
	 * @param request
	 * @param name
	 * @throws Exception
	 */
	public static String getCookieValue(HttpServletRequest request, String name) throws Exception{

		SSO sso = new SSO(request);

		if(Constant.CK_USER_ID.equals(name)){
			return sso.getUserId();
		}else if(Constant.CK_USER_CL.equals(name)){
			return sso.getUserCl();
		}else if(Constant.CK_USER_NM.equals(name)){
			return sso.getUserNm();
		}else if(Constant.CK_MASTER_CD.equals(name)){
			return sso.getBizRegNo();
		}else if(Constant.CK_LOCAL_IP.equals(name)){
			return sso.getClientIp();
		}else if(Constant.CK_BIZ_REG_NM.equals(name)){
			return sso.getBizRegNm();
		}else if(Constant.CK_SUPPLIER_NM.equals(name)){
			return sso.getSupplierNm();
		}else if(Constant.CK_USER_DEPT.equals(name)){
			return sso.getStaffDeptNm();
		}else if(Constant.CK_AUTH_CD.equals(name)){
			return sso.getAuthCd();
		}else if(Constant.CK_USER_DN.equals(name)){
			return sso.getUserDn();
		}else if(Constant.CK_VERSION.equals(name)){
			return sso.getVersion();
		}

		return null;
	}

	
	/**
	 * No ActiveX용 쿠키값을 가져온다. : get Cookie Value _ No ActiveX
	 * @methodName : getCookieValueNoAx
	 * @return     : String
	 * @param request
	 * @param name
	 * @throws Exception
	 */
	public static String getCookieValueNoAx(HttpServletRequest request, String name) throws Exception{

		String returnStr = "";

		Cookie[] cookies = request.getCookies();

		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals(name)){
				returnStr = cookies[i].getValue();
			}
		}

		return URLDecoder.decode(returnStr, "UTF-8");

	}

}
