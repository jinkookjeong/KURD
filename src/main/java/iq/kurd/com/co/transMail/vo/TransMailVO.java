package iq.kurd.com.co.transMail.vo;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TransMailVO extends AbstractVO {	
	private String mailSeq;
	private String mailPefkey;
	private String mailId;
	private String sendMail;
	private String callbackMail;
	private String mailMsg;
	private String mailTranStat;
	private String mailDate;
	private String mailType;
	private String sendUserNm;
	private String mailDateS;
	private String mailDateE;
	private String mailTitle;	
}
