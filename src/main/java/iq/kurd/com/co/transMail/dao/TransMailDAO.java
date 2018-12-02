package iq.kurd.com.co.transMail.dao;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import iq.kurd.com.co.transMail.vo.TransMailVO;

@Mapper("transMailDAO")
public interface TransMailDAO {
	public void insertTransMail(TransMailVO transMailVO) throws Exception;
}
