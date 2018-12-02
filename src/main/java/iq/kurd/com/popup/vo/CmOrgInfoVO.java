package iq.kurd.com.popup.vo;

import java.util.List;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class CmOrgInfoVO extends AbstractVO{

	private String orgNo;
	private String distrctCd;
	private String orgNm;
	private String orgEnNm;
	private String abbrNm;
	
	private CmOrgInfoVO cmOrgInfoVO;
	private List<CmOrgInfoVO> cmOrgInfoVOList;
	private List<CmOrgInfoVO> cmOrgInfoCdVOList;
	
}
