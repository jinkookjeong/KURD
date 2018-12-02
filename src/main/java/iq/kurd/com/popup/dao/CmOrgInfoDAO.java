package iq.kurd.com.popup.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import iq.kurd.com.popup.vo.CmOrgInfoVO;
	
@Mapper("cmOrgInfoDAO")
public interface CmOrgInfoDAO {
	
	//목록
	public int selectOrgInfoListCnt(CmOrgInfoVO cmOrgInfoVO) throws Exception;
	public List<CmOrgInfoVO> selectOrgInfoList(CmOrgInfoVO cmOrgInfoVO) throws Exception;

	//Select Option 처리용(사실필요없음 그냥함)
	public List<CmOrgInfoVO> selectOrgUpCdList(CmOrgInfoVO cmOrgInfoVO) throws Exception;	
	public List<CmOrgInfoVO> selectOrgMidCdList(CmOrgInfoVO cmOrgInfoVO) throws Exception;

}
