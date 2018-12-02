package iq.kurd.com.co.code.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import iq.kurd.com.co.code.dao.CoCommonCdDAO;
import iq.kurd.com.co.code.service.CoCommonCdService;
import iq.kurd.com.co.code.vo.CoCommonCdVO;
import iq.kurd.com.util.format.LocaleUtil;


/**
 * 공통코드 관리 service : Common Code service
 * @fileName  : CoCommonCodeServiceImpl.java
 * @package   : iq.kurd.com.co.code.biz.impl
 * @since     : 2018. 2. 25.
 * @author    : Jin Kook JEONG
 */
@Service("coCommonCdService")
@Transactional
public class CoCommonCdServiceImpl extends EgovAbstractServiceImpl implements CoCommonCdService {

	@Resource(name="coCommonCdDAO")
	CoCommonCdDAO coCommonCdDAO;

	public List<CoCommonCdVO> selectListCommonUpCode() throws Exception {
		return coCommonCdDAO.listCommonUpCode();
	}
	
	
	public int selectCdListCnt(CoCommonCdVO coCommonCdVO) throws Exception {
		return coCommonCdDAO.selectCountCommonCode(coCommonCdVO);
	}
	
	public List<CoCommonCdVO> selectCdList(CoCommonCdVO coCommonCdVO) throws Exception {
		//return coCommonCdDAO.selectListcommonCode(coCommonCdVO, new RowBounds(coCommonCdVO.getOffSet(), coCommonCdVO.getRecordCountPerPage()));
		//return coCommonCdDAO.selectListcommonCode(coCommonCdVO, coCommonCdVO.getRecordCountPerPage());
		return null;
	}
	
	
	public List<CoCommonCdVO> selectListCommonCode(CoCommonCdVO coCommonCdVO) throws Exception {
		return coCommonCdDAO.listCommonCode(coCommonCdVO);
	}

	public CoCommonCdVO selectListPageCommonCode(CoCommonCdVO coCommonCdVO) throws Exception {
		
		/** paging */
		coCommonCdVO.setTotalRecordCount(coCommonCdDAO.selectCountCommonCode(coCommonCdVO));
		coCommonCdVO.setCoCommonCdVOList(coCommonCdDAO.selectListPageCommonCode(coCommonCdVO));

		return coCommonCdVO;
	}

	public List<CoCommonCdVO> selectListCommonCodeByUpCd(String upCd) throws Exception {

		CoCommonCdVO coCommonCdVO = new CoCommonCdVO();
		coCommonCdVO.setUpCd(upCd);

		return coCommonCdDAO.listCommonCode(coCommonCdVO);
	}

	public CoCommonCdVO getCommonCodeNameByUpCd(String upCd, String cd) throws Exception {

		CoCommonCdVO coCommonCodeVO = new CoCommonCdVO();
		coCommonCodeVO.setUpCd(upCd);
		coCommonCodeVO.setCd(cd);

		return coCommonCdDAO.selectCommonCodeName(coCommonCodeVO);
	}
	
	public CoCommonCdVO getCommonCodeName(String cd) throws Exception {
		
		CoCommonCdVO coCommonCodeVO = new CoCommonCdVO();
		coCommonCodeVO.setCd(cd);

		return coCommonCdDAO.selectCommonCodeName(coCommonCodeVO);
	}

	public List<CoCommonCdVO> selectPartListCommonCodeByUpCd(String upCd, String[] arr) throws Exception {
		CoCommonCdVO coCommonCodeVO = new CoCommonCdVO();
		coCommonCodeVO.setUpCd(upCd);
		coCommonCodeVO.setExcludeCodeArr(arr);

		return coCommonCdDAO.listCommonCode(coCommonCodeVO);
	}

	
	public List<CoCommonCdVO> selectListCommonCodePopup(CoCommonCdVO coCommonCdVO) throws Exception {
		
		List<CoCommonCdVO> listCommonCode = coCommonCdDAO.listCommonCode(coCommonCdVO);
		return listCommonCode;
	}
	
    public CoCommonCdVO getCommonCodeNameByCdExt(String upCd, String cdExt) throws Exception {
		
		CoCommonCdVO coCommonCdVO = new CoCommonCdVO();
		coCommonCdVO.setUpCd(upCd);
		coCommonCdVO.setCdExt(cdExt);
		List<CoCommonCdVO> listCommonCode = coCommonCdDAO.listCommonCodeByCdExt(coCommonCdVO);
		if (listCommonCode.size() == 0) {
			return null;
		} else {
			return listCommonCode.get(0);
		}
	}

}