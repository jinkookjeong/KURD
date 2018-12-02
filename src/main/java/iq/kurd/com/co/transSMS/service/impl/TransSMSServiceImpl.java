package iq.kurd.com.co.transSMS.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import iq.kurd.com.co.transSMS.dao.TransSMSDAO;
import iq.kurd.com.co.transSMS.service.TransSMSService;
import iq.kurd.com.co.transSMS.vo.TransSMSVO;

@Service("transSMSService")
public class TransSMSServiceImpl extends EgovAbstractServiceImpl implements TransSMSService {

	@Resource(name="transSMSDAO")
	TransSMSDAO transSMSDAO;
	
	@Override
	public void insertTransSMS(TransSMSVO transSMSVO) throws Exception {
		transSMSDAO.insertTransSMS(transSMSVO);
	}
}