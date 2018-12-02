package iq.kurd.com.login.vo;

import java.util.List;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class UserVO extends AbstractVO{
	
	private String userId;
	private String signCerti;
	private String password;
	private String mastId;
	private String nid;
	private String deptNo;
	private String deptNm;
	private String telNo;
	private String cellNo;
	private String eMail;
	private String eMailYn;
	private String smsYn;
	private String aprovYn;
	private String rcvYn;
	
	private String authCd;
	private String authNm;
	
	private UserVO userVO;
	private List<UserVO> userVOList;
	
	private List<UserAuthorityVO> userAuthorityVO;
  
}