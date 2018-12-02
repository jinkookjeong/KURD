package iq.kurd.com.co.transMail.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import iq.kurd.com.co.transMail.dao.TransMailDAO;
import iq.kurd.com.co.transMail.service.TransMailService;
import iq.kurd.com.co.transMail.vo.TransMailVO;

@Service("transMailService")
public class TransMailServiceImpl extends EgovAbstractServiceImpl implements TransMailService {

	@Resource(name="transMailDAO")
	TransMailDAO transMailDAO;
	
	@Override
	public void insertTransMail(TransMailVO transMailVO) throws Exception {
		transMailDAO.insertTransMail(transMailVO);
	}
}