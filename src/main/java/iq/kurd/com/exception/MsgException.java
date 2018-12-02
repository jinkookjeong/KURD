package iq.kurd.com.exception;

/**
 * 화면에 에러 메세지 전달용
 * @author jwchoi
 *
 */
public class MsgException extends Exception {

	private static final long serialVersionUID = -3029187880941733652L;

	public MsgException(String msg){
		super(msg);
	}
	
	public MsgException(String msg, Throwable cause){
		super(msg, cause);
	}
	

}
