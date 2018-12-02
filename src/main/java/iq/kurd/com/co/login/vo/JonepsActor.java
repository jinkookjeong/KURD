package iq.kurd.com.co.login.vo;

import java.util.List;

import iq.kurd.com.AbstractVO;
import lombok.Data;


@SuppressWarnings("serial")
@Data
public class JonepsActor extends AbstractVO {
	
	/**
	 * 유저 아이디
	 */
	private String usrId;
	
	/**
	 * 유저 타입 (A:관리자 G:기관 S:업체)
	 * */
	private String usrType;
	
	/**
	 * 마스터 코드 
	 */
	private String mastNo;
	
	/**
	 * 마스트 명
	 */
	private String mastNm;
	
	/**
	 * 국가식별번호(담당자) 
	 */
	private String nid;
	
	/**
	 * 담당자명 
	 */
	private String chrgrNm;
	
	/**
	 * 부서코드 
	 */
	private String deptNo;
	
	/**
	 * 부서명 
	 */
	private String deptNm;
	
	/**
	 * 전화번호 
	 */
	private String telno;
	
	/**
	 * 팩스번호 
	 */
	private String faxno;
	
	/**
	 * 휴대폰번호 
	 */
	private String cellno;
	
	/**
	 * 이메일 
	 */
	private String email;
	
	/**
	 * 승인여부 
	 */
	private String aprovYn;
	
	/**
	 * 탈퇴여부 
	 */
	private String delYn;
	
	/**
	 * 이메일 수신여부
	 */
	private String emailYn;
	
	/**
	 * SMS 수신여부 
	 */
	private String smsYn;
	
	/**
	 * 전자문서 수신여부 
	 */
	private String recvYn;
	
	/**
	 * 기관유형코드
	 */
	private String orgSeCd;
	
	/**
	 * 기관유형코드명
	 */
	private String orgSeCdNm;
	
	private String signCerti;
	
	/**
	 * 권한 목록
	 */
	private List<UsrAuthoVo> authCdList;

}
