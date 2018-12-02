package iq.kurd.com.login.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import iq.kurd.com.login.vo.UserVO;

@Mapper("userDAO")
public interface UserDAO {
	
	public UserVO selectUser(UserVO userVO) throws Exception;
	public List<UserVO> selectUserAuthList(UserVO userVO) throws Exception;
	
}
