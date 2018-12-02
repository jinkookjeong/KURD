package iq.kurd.com.co.transSMS.dao;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import iq.kurd.com.co.transSMS.vo.TransSMSVO;

@Mapper("transSMSDAO")
public interface TransSMSDAO {
	public void insertTransSMS(TransSMSVO transSMSVO) throws Exception;
}
