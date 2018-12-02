package iq.kurd.com.co.login.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import iq.kurd.com.co.login.dao.LoginDAO;
import iq.kurd.com.co.login.service.LoginService;
import iq.kurd.com.co.login.vo.JonepsActor;
import iq.kurd.com.co.login.vo.LoginVo;
import iq.kurd.com.co.login.vo.UsrAuthoVo;

@Service("loginService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@Resource MessageSource messageSource;
	
	@Resource(name = "loginDAO")
	LoginDAO loginDAO;
	
	LoginVo loginVo = null;
	
	public JonepsActor loginCheck(LoginVo loginVo) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("=======================[loginCheck] START=================================");
		}
		JonepsActor actor = loginDAO.loginCheck(loginVo);
		if(logger.isDebugEnabled()){
			logger.debug("=======================[loginCheck] END=================================");
		}
		return actor;
	}
	
	public JonepsActor loginCheckCookie(LoginVo loginVo) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("=======================[loginCheckCookie] START=================================");
		}
		JonepsActor actor = loginDAO.loginCheckCookie(loginVo);
		if(logger.isDebugEnabled()){
			logger.debug("=======================[loginCheckCookie] END=================================");
		}
		return actor;
	}
	
	public List<UsrAuthoVo> selectUsrAuthList(LoginVo loginVo) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("=======================[selectUsrAuthList] START=================================");
		}
		return loginDAO.selectUsrAuthList(loginVo);
	}

	@Override
	public JonepsActor loginCheckById(LoginVo loginVo) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("=======================[loginCheckById] START=================================");
		}
		JonepsActor actor = loginDAO.loginCheckById(loginVo);
		if(logger.isDebugEnabled()){
			logger.debug("=======================[loginCheckById] END=================================");
		}
		return actor;
	}
	
	public List<UsrAuthoVo> selectUsrAuthListById(LoginVo loginVo) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("=======================[selectUsrAuthListById] START=================================");
		}
		return loginDAO.selectUsrAuthListById(loginVo);
	}
}
