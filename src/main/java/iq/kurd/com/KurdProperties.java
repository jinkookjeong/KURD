package iq.kurd.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import iq.kurd.com.constant.Constant;
import iq.kurd.com.util.format.SystemUtil;


/**
 * properties값들을 파일로부터 읽어와   Globals클래스의 정적변수로 로드시켜주는 클래스 : Read the properties file values
 * @fileName  : NebidProperties.java
 * @package   : iq.kurd.common
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class KurdProperties{


	//프로퍼티값 로드시 에러발생하면 반환되는 에러문자열
	public static final String ERR_CODE =" EXCEPTION OCCURRED";
	public static final String ERR_CODE_FNFE =" EXCEPTION(FNFE) OCCURRED";
	public static final String ERR_CODE_IOE =" EXCEPTION(IOE) OCCURRED";
 
	//파일구분자
    static final char FILE_SEPARATOR     = File.separatorChar;


	/**
	 * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환(Globals.java 전용) : Key value of the property value to a string
	 * @methodName : getProperty
	 * @return     : String
	 * @param keyName
	 */
	public static String getProperty(String keyName){
		String value = ERR_CODE;
		InputStream is = null;
		FileInputStream fis = null;
		try{
			Properties props = new Properties();
			if(SystemUtil.isOsLocal()){
				is  = KurdProperties.class.getResourceAsStream(Constant.KURD_LOCAL); //상대경로
				//fis = new FileInputStream(Constant.KURD_LOCAL); //절대경로
			}else{ 
				//is  = KurdProperties.class.getResourceAsStream(Constant.KURD_SERVER); //상대경로 
				fis = new FileInputStream(Constant.KURD_SERVER); //절대경로
			}
			props.load(new java.io.BufferedInputStream(is));
			value = props.getProperty(keyName).trim();
		}catch(FileNotFoundException fne){
			debug(fne);
		}catch(IOException ioe){
			debug(ioe);
		}catch(Exception e){
			debug(e);
		}finally{
			try {
				if (is != null) is.close();
				if (fis != null) fis.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return value;
	}


	/**
	 * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환 : Key value of the property value to a string
	 * @methodName : getProperty
	 * @return     : String
	 * @param fileName
	 * @param key
	 */
	public static String getProperty(String fileName, String key){
		InputStream is = null;
		try{
			java.util.Properties props = new java.util.Properties();
			is  = KurdProperties.class.getResourceAsStream(fileName);
			props.load(new java.io.BufferedInputStream(is));
			is.close();
			String value = props.getProperty(key);
			return value;
		}catch(java.io.FileNotFoundException fne){
			return ERR_CODE_FNFE;
		}catch(java.io.IOException ioe){
			return ERR_CODE_IOE;
		}finally{
			try {
				if (is != null) is.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}


	/**
	 * 주어진 프로파일의 내용을 파싱하여 (key-value) 형태의 구조체 배열을 반환한다.
	 * @methodName : loadPropertyFile
	 * @return     : ArrayList
	 * @param property
	 */
	public static ArrayList loadPropertyFile(String property){

		// key - value 형태로 된 배열 결과
		ArrayList keyList = new ArrayList();

		String src = property.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		FileInputStream fis = null;
		try
		{

			File srcFile = new File(src);
			if (srcFile.exists()) {

				java.util.Properties props = new java.util.Properties();
				fis  = new FileInputStream(src);
				props.load(new java.io.BufferedInputStream(fis));
				fis.close();

				int i = 0;
				Enumeration plist = props.propertyNames();
				if (plist != null) {
					while (plist.hasMoreElements()) {
						Map map = new HashMap();
						String key = (String)plist.nextElement();
						map.put(key, props.getProperty(key));
						keyList.add(map);
					}
				}
			}
		} catch (Exception ex){
		    ex.printStackTrace();
		} finally {
			try {
				if (fis != null) fis.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return keyList;
	}

	/**
	 * 
	 * @methodName : debug
	 * @return     : void
	 * @param obj
	 */
	private static void debug(Object obj) {
		if (obj instanceof java.lang.Exception) {
			((Exception)obj).printStackTrace();
		}
	}
}

