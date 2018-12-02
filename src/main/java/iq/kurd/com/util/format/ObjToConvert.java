package iq.kurd.com.util.format;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * 스트링 및 기타 변환
 * jwchoi
 * @version 0.1
 */
public class ObjToConvert {

	/**
	 * 객체에 대한 null 여부 체크
	 * @param object
	 * @return null 일 경우 true 값 리턴
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object object) {
         if (object == null) {
             return true;
         }

         if (object instanceof String) {
             String str = (String) object;
             return str.length() == 0;
         }

         if (object instanceof Collection) {
             Collection collection = (Collection) object;
             return collection.size() == 0;
         }

         if (object.getClass().isArray()) {
             try {
                 if (Array.getLength(object) == 0) {
                     return true;
                 }
             } catch (Exception e) {
            	 e.printStackTrace();
             }
         }

         return false;
     }
	
	/**
	 * 객체에 대한 not null 여부 체크
	 * @param object
	 * @return not null 일 경우 true 값 리턴
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Object object) {
		if (object == null) {
			return false;
		}
		
		if (object instanceof String) {
			String str = (String) object;
			if(str.length() > 0){
				return true;
			}else{
				return false;
			}
		}
		
		if (object instanceof Collection) {
			Collection collection = (Collection) object;
			if(collection.size() > 0){
				return true;
			}else{
				return false;
			}
		}
		
		if (object.getClass().isArray()) {
			try {
				if (Array.getLength(object) > 0) {
					return true;
				}else{
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	/**
	 * 객체에 대한 길이 체크
	 * @param object
	 * @return not null 일 경우 true 값 리턴
	 */
	@SuppressWarnings("rawtypes")
	public static int isLength(Object object) {
		if (object == null) {
			return 0;
		}
		
		if (object instanceof String) {
			String str = (String) object;
			if(str.length() > 0){
				return str.length();
			}else{
				return 0;
			}
		}
		
		if (object instanceof Collection) {
			Collection collection = (Collection) object;
			if(collection.size() > 0){
				return collection.size();
			}else{
				return 0;
			}
		}
		
		if (object.getClass().isArray()) {
			try {
				if (Array.getLength(object) > 0) {
					Array.getLength(object);
				}else{
					return 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	 
	 /**
	  * 객체를 int 로 변환
	 * @param object
	 * @return 실패나 에러시 0 으로 반환
	 */
	public static int strToint(Object object) {
		
		 int returnint = 0;
		 
		 try {
			
			 returnint = Integer.valueOf(String.valueOf(object));
		} catch (Exception e) {
			returnint = 0;
		}
		 
		 return returnint;
	 }
	
	/**
	 * 객체를 int 형 String 로 변환
	 * @param object
	 * @return 실패나 에러시 0 으로 반환
	 */
	public static String objToIntStr(Object object) {
		
		String returnint = "0";
		
		try {
			if (object instanceof Double) {
				returnint = String.valueOf(((Double)object).longValue());
				
			}else if(object instanceof Integer){
				returnint = String.valueOf(object);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnint = "0";
		}
		
		return returnint;
	}
	
}