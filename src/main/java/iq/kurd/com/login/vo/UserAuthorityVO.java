package iq.kurd.com.login.vo;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class UserAuthorityVO extends AbstractVO{

	private String userId;
	private String authCd;
	private String authNm;
	
}
