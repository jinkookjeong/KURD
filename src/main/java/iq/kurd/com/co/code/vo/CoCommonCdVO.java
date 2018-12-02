package iq.kurd.com.co.code.vo;

import java.math.BigDecimal;
import java.util.List;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@Data
@SuppressWarnings({"serial", "unchecked"})
public class CoCommonCdVO extends AbstractVO {

	private String upCd;

	private String upCdNm;

	private String upCdNmKo;

	private BigDecimal length;

	private String useYn;

	private String relayYn;

	private String edocRelay;

	private String similCd;

	private String superCd;
	
	private String cdDesc;

	private String cd;

	private String cdNm;

	private String cdNmKo;

	private String cdNm2;

	private String cdNm2Ko;

	private String brief;

	private String attCd;

	private String attValue;

	private BigDecimal cdOrd;

	private String loc;

	private String title;
	
	private CoCommonCdVO coCommonCdVO;

    private List<CoCommonCdVO> coCommonCdVOList;

	private String objectName;

	private boolean isStartWithFull;

	private String[] excludeCodeArr;

	private String selectedCode;

	private boolean isStartWithSelect;

	private String cdNmAr;

	private String cdNmStr;
	
	private String cdExt;
	
}