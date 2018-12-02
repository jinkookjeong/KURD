package iq.kurd.com.util.format;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Locale Util : Locale Util
 * @fileName  : LocaleUtil.java
 * @package   : iq.kurd.com.util.format
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class LocaleUtil {

	/**
	 * get LangCode : get LangCode
	 * @methodName : getLangCode
	 * @return     : String
	 */
	public static String getLangCode(){
		
		//Locale locale = LocaleContextHolder.getLocale();
		//String langCode = locale.getLanguage();
		
		//return langCode;
		return "ko";
	}
	
	/**
	 * get Locale : get Locale 
	 * @methodName : getLocale
	 * @return     : Locale
	 */
	public static Locale getLocale(){
		Locale local = new Locale("ko");
		
		//return LocaleContextHolder.getLocale();
		return local;
	}
	
}
