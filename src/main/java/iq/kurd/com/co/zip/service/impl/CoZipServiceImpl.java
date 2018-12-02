package iq.kurd.com.co.zip.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import iq.kurd.com.co.zip.dao.CoZipDAO;
import iq.kurd.com.co.zip.service.CoZipService;
import iq.kurd.com.co.zip.vo.CoZipSVO;

/**
 * 우편번호  service : zip code service
 * @fileName  : CoZipServiceImpl.java
 * @package   : iq.kurd.com.co.zip.biz.impl
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
@Service("coZipService")
@Transactional
public class CoZipServiceImpl extends EgovAbstractServiceImpl implements CoZipService {

	@Resource(name="coZipDAO")
	CoZipDAO coZipDAO;


	public void selectListPageCoZip(CoZipSVO coZIPSVO) throws Exception {

		/** paging */
		coZIPSVO.setTotalRecordCount(coZipDAO.selectCountCoZip(coZIPSVO));
		coZIPSVO.setCoZipQVOList(coZipDAO.selectListPageCoZip(coZIPSVO));

	}


	public void selectListAddr1(CoZipSVO coZipSVO) throws Exception {
		coZipSVO.setCoZipAddr1List(coZipDAO.selectListAddr1(coZipSVO));
	}


	public void selectListAddr2(CoZipSVO coZipSVO) throws Exception {
		coZipSVO.setCoZipAddr2List(coZipDAO.selectListAddr2(coZipSVO));
	}


	public void selectListAddr3(CoZipSVO coZipSVO) throws Exception {
		coZipSVO.setCoZipAddr3List(coZipDAO.selectListAddr3(coZipSVO));
	}

}