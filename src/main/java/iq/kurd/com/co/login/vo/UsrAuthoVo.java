package iq.kurd.com.co.login.vo;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class UsrAuthoVo extends AbstractVO {
	
	private String usrId;
	private String authoCd;
	private String authoNm;
	private String peYn;	//PROCURING ENTITY YES OR NO
	private String deYn;	//DEMANDING ENTITY YES OR NO
	private String spYn;	//SUPLLIER YES OR GNO
	
}
