package iq.kurd.com.util.format;

 

/**
 * 해당 os 판별함  : Winodws  ==> TRUE, UNIX/LINUX  ==> FALSE
 * @fileName  : SystemUtil.java
 * @package   : iq.kurd.com.util.format
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class SystemUtil {


	/**
	 * 로컬확인 : Local check
	 * @methodName : isOsLocal
	 * @return     : Boolean
	 * @return
	 */
	public static Boolean isOsLocal(){

		if((System.getProperty("os.name").toUpperCase().startsWith("WINDOWS"))){

				return true;

			}else{


				return false;
			}

	}

}
