package iq.kurd.com.co.login.service;

import java.util.List;

import iq.kurd.com.co.login.vo.JonepsActor;
import iq.kurd.com.co.login.vo.LoginVo;
import iq.kurd.com.co.login.vo.UsrAuthoVo;

public interface LoginService {
	
	public JonepsActor loginCheck(LoginVo loginVo) throws Exception;
	
	public JonepsActor loginCheckCookie(LoginVo loginVo) throws Exception;
	
	public List<UsrAuthoVo> selectUsrAuthList(LoginVo loginVo) throws Exception;

	public JonepsActor loginCheckById(LoginVo loginVo) throws Exception;

	public List<UsrAuthoVo> selectUsrAuthListById(LoginVo loginVo) throws Exception;

}
