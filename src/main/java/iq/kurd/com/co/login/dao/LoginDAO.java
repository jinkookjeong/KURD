package iq.kurd.com.co.login.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import iq.kurd.com.co.login.vo.JonepsActor;
import iq.kurd.com.co.login.vo.LoginVo;
import iq.kurd.com.co.login.vo.UsrAuthoVo;

@Mapper("loginDAO")
public interface LoginDAO {
	public JonepsActor loginCheck(LoginVo loginVo) throws Exception;
	
	public JonepsActor loginCheckCookie(LoginVo loginVo) throws Exception;

	public List<UsrAuthoVo> selectUsrAuthList(LoginVo loginVo) throws Exception;

	public void updateUsrLoginDt(LoginVo loginVo) throws Exception;

	public JonepsActor loginCheckById(LoginVo loginVo) throws Exception;

	public List<UsrAuthoVo> selectUsrAuthListById(LoginVo loginVo) throws Exception;
}