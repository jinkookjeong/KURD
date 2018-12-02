package iq.kurd.com.co.login;

import org.apache.commons.lang.StringUtils;

/**
 * <ul>
 * <li>업무명 : 문자열 상수를 정의
 * <li>설 명 : JONEPS에서 사용하는 문자열 상수를 정의
 * </ul>
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일         	수정자          	 수정내용
 *  -------       --------    ---------------------------
 *  2016.05.12  	bjs 		최초 생성
 * </pre> 
 * 
 * @author bjs
 * @since 2016.05.12
 * @version 1.0
 */
public final class StrCtnt {
	/** EMPTY <b> {@value} </b> 길이가 0인 문자 */
	public static final String EMPTY = StringUtils.EMPTY;

	/** JONEPS_NCULT_ACTOR = <b> {@value} </b> Session에 저장되어있는 사용자정보 키값 */
	public static final String JONEPS_ACTOR = "JONEPS_ACTOR";

	/** JONEPS_MSG = <b> {@value} </b> Cmd에서 클라인트로 보내는 메세지 타입선언 상수 */
	public static final String JONEPS_MSG = "JONEPS_MSG";

	/** JONEPS_MSG_ERR = <b> {@value} </b> Cmd에서 발생한 오류를 클라인트로 보내는 메세지 타입선언 상수 */
	public static final String JONEPS_MSG_ERR = "JONEPS_MSG_ERR";

	/** JONEPS_USER_LOCALE = <b> {@value} </b> 사용자별 Locale 설정용 session변수 */
//	public static final String JONEPS_USER_LOCALE = "JONEPS_USER_LOCALE";

	/** MODIFIER = <b> {@value} </b> DAO에 넘겨주는 사용자정보 키 */
	public static final String MODIFIER = "MODIFIER";
	
	public static final String USR_ID = "usrId";
	
	public static final String AUTHO_CD = "authoCd";
	
	public static final String SIGN_CERTI = "signCerti";
	
	public static final String REFER_URL = "referUrl";
}
