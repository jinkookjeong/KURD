package iq.kurd.com.co.zip.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import iq.kurd.com.co.zip.vo.CoZipQVO;
import iq.kurd.com.co.zip.vo.CoZipSVO;


/**
 * 우편번호 DAO : zip code DAO
 * @fileName  : CoZipDAO.java
 * @package   : iq.kurd.com.co.zip.dao
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
@Mapper("coZipDAO")
public interface CoZipDAO {


	/**
	 * 우편번호  목록 페이징 조회 : list page of zip code
	 * @methodName : selectListPageCoZip
	 * @return     : List<CoZipQVO>
	 * @param inputObj
	 * @throws Exception
	 */
	public List<CoZipQVO> selectListPageCoZip (CoZipSVO inputObj) throws Exception;



	/**
	 * 우편번호  카운트 조회 :count of list page 
	 * @methodName : selectCountCoZip
	 * @return     : int
	 * @param inputObj
	 * @throws Exception
	 */
	public int selectCountCoZip (CoZipSVO inputObj) throws Exception;



	/**
	 * 우편번호조회 : ZIP Code Views-1
	 * @methodName : selectListAddr1
	 * @return     : List<CoZipQVO>
	 * @param inputObj
	 * @throws Exception
	 */
	public List<CoZipQVO> selectListAddr1 (CoZipSVO inputObj) throws Exception;


	/**
	 * 우편번호조회 : ZIP Code Views-2
	 * @methodName : selectListAddr2
	 * @return     : List<CoZipQVO>
	 * @param inputObj
	 * @return
	 * @throws Exception
	 */
	public List<CoZipQVO> selectListAddr2 (CoZipSVO inputObj) throws Exception;


	/**
	 * 
	 * 우편번호 목록 조회 :  list of zip code
	 * @return     : List<CoZipQVO>
	 * @param inputObj
	 * @return
	 * @throws Exception
	 */
	public List<CoZipQVO> selectListAddr3 (CoZipSVO inputObj)throws Exception;

}