package iq.kurd.com.util.format;

import iq.kurd.com.KurdProperties;
import iq.kurd.com.util.format.SystemUtil;

/**
 * Web Context Util : Web Context Util
 * @fileName  : WebContextUtil.java
 * @package   : iq.kurd.com.util.format
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class WebContextUtil {

  
	/**
	 * 
	 * @methodName : getUrl
	 * @return     : String
	 * @param biz
	 */
	public static String getUrl(String biz){

		String url = "";
		if(biz.equals("nebid")){
			if(SystemUtil.isOsLocal()){
				url = KurdProperties.getProperty("localUrl");
			}else{
				url = KurdProperties.getProperty("kurdUrl");
			}
			
		}
		/*else if(biz.equals("pt")){
			if(SystemUtil.isOsLocal()){
				url = NebidProperties.getProperty("localPTUrl");
			}else{
				url = NebidProperties.getProperty("JonepsPTUrl");
			}
    	}else if(biz.equals("eb")){
			if(SystemUtil.isOsLocal()){
				url = NebidProperties.getProperty("localEPUrl");
			}else{
				url = NebidProperties.getProperty("JonepsEPUrl");
			}
		}else if(biz.equals("sp")){
			if(SystemUtil.isOsLocal()){
				url = NebidProperties.getProperty("localSPUrl");
			}else{
				url = NebidProperties.getProperty("JonepsSPUrl");
			}
		}else if(biz.equals("um")){
			if(SystemUtil.isOsLocal()){
				url = NebidProperties.getProperty("localUMUrl");
			}else{
				url = NebidProperties.getProperty("JonepsUMUrl");
			}
		}else if(biz.equals("ed")){
			if(SystemUtil.isOsLocal()){
				url = NebidProperties.getProperty("localEDUrl");
			}else{
				url = NebidProperties.getProperty("JonepsEDUrl");
			}
		}else if(biz.equals("el")){
			if(SystemUtil.isOsLocal()){
				url = NebidProperties.getProperty("localELUrl");
			}else{
				url = NebidProperties.getProperty("JonepsELUrl");
			}
		}else if(biz.equals("report")){
			if(SystemUtil.isOsLocal()){
				url = NebidProperties.getProperty("localReportUrl");
			}else{
				url = NebidProperties.getProperty("JonepsReportUrl");
			}
		}*/

		return url;

	}


	/**
	 * get file Path : get file Path 
	 * @methodName : getFildPath
	 * @return     : String
	 * @deprecated use {@link #getFilePath()} instead.
	 */
	@Deprecated
	public static String getFildPath(){

		return getFilePath();
	}
	
	/**
	 * get file Path : get file Path 
	 * @methodName : getFilePath
	 * @return     : String
	 */
	public static String getFilePath() {

		String returnUrl;

		if (SystemUtil.isOsLocal()) {
			returnUrl = KurdProperties.getProperty("LOCAL_FILE_PATH");
		} else {
			returnUrl = KurdProperties.getProperty("SERVER_FILE_PATH");
		}

		return returnUrl;
	}
	
	/**
	 * change Menu code : change Menu code 
	 * @methodName : changeMenuCd
	 * @return     : String
	 * @param menuCd
	 */
	public static String changeMenuCd(String menuCd) {
		/*if ( "p".equals(menuCd) ) {
			return "portal";
		} else if ( "s".equals(menuCd)) {
			return "shop";
		} else if ( "ct".equals(menuCd)) {
			return "cont";
		} else if ( "u".equals(menuCd)) {
			return "usemn";
		} else if ( "b".equals(menuCd)) {
			return "bid";
		} else if ( "ex".equals(menuCd)) {
			return "exms";
		} else if ( "ca".equals(menuCd)) {
			return "cata";
		} else if ("st".equals(menuCd)){
			return "statistic";
		}*/

		return "";
	}

	/**
	 * get external link URL
	 * @methodName : getExtLinkUrl
	 * @return     : String
	 */
	public static String getExtLinkUrl() {
		if (SystemUtil.isOsLocal() || ("2.6.32-573.18.1.el6.x86_64".equals(System.getProperty("os.version")) && "Asia/Seoul".equals(System.getProperty("user.timezone")))) {
			return KurdProperties.getProperty("localEXTLINK");
		} else {
			return KurdProperties.getProperty("EXTLINK");
		}
	}
}
