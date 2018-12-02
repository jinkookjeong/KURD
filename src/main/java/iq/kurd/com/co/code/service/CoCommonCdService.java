package iq.kurd.com.co.code.service;

import java.util.List;

import iq.kurd.com.co.code.vo.CoCommonCdVO;

/**
 * 공통코드 관리 service : Common Code service
 * @fileName  : CoCommonCodeService.java
 * @package   : iq.kurd.com.co.code.biz
 * @since     : 2018. 2. 07
 * @author    : Jin Kook JEONG
 */
public interface CoCommonCdService {
	
	/**
	 * 공통메인코드조회
	 * @methodName : selectListCommonUpCode
	 * @return     : List<CoCommonCdQVO>
	 * @param coCommonCodeSVO
	 * @throws Exception
	 */
	public List<CoCommonCdVO> selectListCommonUpCode() throws Exception;
	
	
	public int selectCdListCnt(CoCommonCdVO coCommonCdVO) throws Exception;
	
	public List<CoCommonCdVO> selectCdList(CoCommonCdVO coCommonCdVO) throws Exception ;
	
	/**
	 * 공통코드조회
	 * @methodName : selectListCommonCode
	 * @return     : List<CoCommonCdQVO>
	 * @param coCommonCodeSVO
	 * @throws Exception
	 */
	public List<CoCommonCdVO> selectListCommonCode(CoCommonCdVO coCommonCdVO) throws Exception;
	
	/**
	 * 공통코드조회페이징
	 * @methodName : selectListPageCommonCode
	 * @return     : CoCommonCdSVO
	 * @param coCommonCodeSVO
	 * @throws Exception
	 */
	public CoCommonCdVO selectListPageCommonCode(CoCommonCdVO coCommonCdVO) throws Exception;
	
	/**
	 * upCd로 공통코드조회
	 * @methodName : selectListCommonCodeByUpCd
	 * @return     : List<CoCommonCdQVO>
	 * @param upCd
	 * @return
	 * @throws Exception
	 */
	public List<CoCommonCdVO> selectListCommonCodeByUpCd(String upCd) throws Exception;
	
	/**
	 * upCd로 공통코드조회
	 * @methodName : selectListCommonCodeByUpCd
	 * @return     : List<CoCommonCdQVO>
	 * @param upCd
	 * @return
	 * @throws Exception
	 */
	public List<CoCommonCdVO> selectPartListCommonCodeByUpCd(String upCd, String[] arr) throws Exception;
	
	/**
	 * cd, upCd로 공통코드명 조회
	 * @methodName : getCommonCodeNameByUpCd
	 * @return     : CoCommonCdQVO
	 * @param upCd
	 * @param cd
	 * @return
	 * @throws Exception
	 */
	public CoCommonCdVO getCommonCodeNameByUpCd(String upCd, String cd) throws Exception;
	
	/**
	 * cd로 공통코드명 조회
	 * @methodName : getCommonCodeName
	 * @return     : CoCommonCdQVO
	 * @param cd
	 * @return
	 * @throws Exception
	 */
	public CoCommonCdVO getCommonCodeName(String cd) throws Exception;
	
	public CoCommonCdVO getCommonCodeNameByCdExt(String upCd, String cdExt) throws Exception;

	public List<CoCommonCdVO> selectListCommonCodePopup(CoCommonCdVO coCommonCdVO) throws Exception;
	
}
