package iq.kurd.com.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import iq.kurd.com.popup.dao.CmOrgInfoDAO;
import iq.kurd.com.popup.service.CmPopupOrgInfoSvc;
import iq.kurd.com.popup.vo.CmOrgInfoVO;
 
@Service("cmPopupOrgInfoSvc")  
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class CmPopupOrgInfoSvcImpl implements CmPopupOrgInfoSvc{
 
	@Resource(name = "cmOrgInfoDAO")
	CmOrgInfoDAO cmOrgInfoDAO;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public CmOrgInfoVO selectOrgInfoList(CmOrgInfoVO cmOrgInfoVO) throws Exception {
		
		cmOrgInfoVO.setTotalRecordCount(cmOrgInfoDAO.selectOrgInfoListCnt(cmOrgInfoVO)); //count
		cmOrgInfoVO.setCmOrgInfoVOList(cmOrgInfoDAO.selectOrgInfoList(cmOrgInfoVO)); //List
		cmOrgInfoVO.setCmOrgInfoCdVOList(cmOrgInfoDAO.selectOrgUpCdList(cmOrgInfoVO)); //upperCode option list
		
		return cmOrgInfoVO; 
	}

	@Override
	public List<CmOrgInfoVO> selectOrgCdList(CmOrgInfoVO cmOrgInfoVO) throws Exception {
		//cmOrgInfoVO.setCmOrgInfoCdVOList(cmOrgInfoDAO.selectOrgMidCdList(cmOrgInfoVO));
		return cmOrgInfoDAO.selectOrgMidCdList(cmOrgInfoVO);
		
	}
}
