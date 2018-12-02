package iq.kurd.com.popup.service;

import java.util.List;

import iq.kurd.com.popup.vo.CmOrgInfoVO;

public interface CmPopupOrgInfoSvc {

	CmOrgInfoVO selectOrgInfoList(CmOrgInfoVO cmOrgInfoVO) throws Exception;
	
	List<CmOrgInfoVO> selectOrgCdList(CmOrgInfoVO cmOrgInfoVO) throws Exception;	
	
}
