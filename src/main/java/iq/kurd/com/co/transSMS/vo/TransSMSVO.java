package iq.kurd.com.co.transSMS.vo;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TransSMSVO extends AbstractVO {	
	private String smsSeq;
	private String smsRefkey;
	private String smsId;
	private String smsPhone;
	private String smsCallback;
	private String smsMsg;
	private String smsTranStat;
	private String smsDate;
	private String smsType;
	private String smsRecevNm;	
	private String smsDateS;
	private String smsDateE;
	
}
