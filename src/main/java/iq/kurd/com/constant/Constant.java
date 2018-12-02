package iq.kurd.com.constant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import iq.kurd.com.KurdProperties;
import iq.kurd.com.util.format.SystemUtil;

/**
 * Constant 상수 처리 클래스 : Process class of constant
 * @fileName  : Constant.java
 * @package   : iq.kurd.com.constant
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */  
public class Constant{

		/** Fail Constant */
		public static final int FAIL    	=  -1;
		/** Success Constant */
		public static final int SUCCESS 	=   1;
		/** NOT Success Constant */
		public static final int NOTSUCCESS 	=  -2;
		/** OK Constant */
		public static final int OK  = 0;
	
		public static final String CK_USER_ID = "ck_userId";
		public static final String CK_USER_CL = "ck_userCl";
		public static final String CK_USER_NM = "ck_userNm";
		public static final String CK_MASTER_CD = "ck_masterCd";
		public static final String CK_LOCAL_IP = "ck_localIp";
		public static final String CK_BIZ_REG_NM = "ck_bizRegNm";
		public static final String CK_SUPPLIER_NM = "ck_supplierNm";
		public static final String CK_USER_DEPT = "ck_userDept";
		public static final String CK_AUTH_CD = "ck_authCd";
		public static final String CK_USER_DN = "ck_userDn";
		public static final String CK_VERSION = "ck_version";
		
		/**
		 * Session Top Menu
		 */
		public static final String TOP_MENU_LIST = "topMnList";
		
		/**
		 * Session Left Menu
		 */
		public static final String LEFT_MENU_LIST = "leftMnList";
		
		/**
		 * Session My Menu
		 */
		public static final String MY_MENU_LIST = "myMnList";
		
		/**
		 * My Menu code
		 */
		public static final String MY_MENU_CODE = "PT02000000";
		
		/**
		 * Propertise Location for Windows
		 * 해당 값 변경시 LANGTREE의 ComProperties도 변경할 것
		 */
		public static final String KURD_LOCAL = "/globals.properties";
		
		/**
		 * Propertise Location for Unix
		 * 해당 값 변경시 LANGTREE의 ComProperties도 변경할 것
		 */
		public static final String KURD_SERVER = "/WASDATA/properties/globals.properties";
		
		/**
		 * 클러스터링 사용 여부를 위한 상수값
		 * (프로퍼티에 존재 하지 않는 이유는 
		 * 인터셉터에서 매번 조회하기에 메모리
		 * 적재 상태로 이용하기 위해서 사용함
		 * false 일경우 쿠키갑으로 인증 처리
		 * )
		 */
		
		public static final boolean CLUSTER_YN = true; 
		
}



