package iq.kurd.com.exception;

/**
 * 권한오류 처리 : Authentication Exception
 * @fileName  : AuthenticationException.java
 * @package   : iq.kurd.com.exception
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public class AuthenticationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2594723140522356115L;

	public AuthenticationException(String msg){
		super(msg);
	}
	
	public AuthenticationException(String msg, Throwable cause){
		super(msg, cause);
	}

}
