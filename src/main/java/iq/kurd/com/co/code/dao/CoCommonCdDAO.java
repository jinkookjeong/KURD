package iq.kurd.com.co.code.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import iq.kurd.com.co.code.vo.CoCommonCdVO;



/**
 * 공통코드 조회 : 
 * @fileName  : CoCommonCdDAO.java
 * @package   : iq.kurd.com.co.code.dao
 * @since     : 2018. 2. 25.
 * @author    : Jin Kook JEONG
 */
@Mapper("coCommonCdDAO")
public interface CoCommonCdDAO {
 

	/**
	 * 공통메인코드 조회 : 
	 * @methodName : listCommonUpCode
	 * @return     : List<CoCommonCdVO>
	 * @param inputObj
	 * @throws Exception
	 */
	public List<CoCommonCdVO> listCommonUpCode() throws Exception; 

	/**
	 * 공통코드 조회 : 
	 * @methodName : listCommonCode
	 * @return     : List<CoCommonCdQVO>
	 * @param inputObj
	 * @throws Exception
	 */
	public List<CoCommonCdVO> listCommonCode (CoCommonCdVO inputObj) throws Exception; 


	/**
	 * 공통코드 조회 목록 페이징 조회
	 * @methodName : selectListPageCommonCode
	 * @return     : List<CoCommonCdQVO>
	 * @param inputObj
	 * @throws Exception
	 */
	public List<CoCommonCdVO> selectListPageCommonCode (CoCommonCdVO inputObj) throws Exception; 


	/**
	 * 공통코드 조회 목록 개수 조회
	 * @methodName : selectCountCommonCode
	 * @return     : int
	 * @param inputObj
	 * @throws Exception
	 */
	public int selectCountCommonCode (CoCommonCdVO inputObj) throws Exception; 

	/**
	 * 공통코드 조회 데이터를 조회한다.
	 * @methodName : selectCommonCodeName
	 * @return     : CoCommonCdQVO
	 * @param inputObj
	 * @return
	 * @throws Exception
	 */
	public CoCommonCdVO selectCommonCodeName (CoCommonCdVO inputObj) throws Exception;
	
	
	/**
	 * 코드 목록 조회
	 * @methodName : selectListCommonCodeByCds
	 * @return     : List<CoCommonCdQVO>
	 * @param inputObj
	 * @return
	 * @throws Exception
	 */
	public List<CoCommonCdVO> selectListCommonCodeByCds(Map<String, Object> inputObj) throws Exception;
	
	
	/**
	 * 공통코드 조회 : 
	 * @methodName : listCommonCodeByCdExt
	 * @return     : List<CoCommonCdQVO>
	 * @param inputObj
	 * @throws Exception
	 */
	public List<CoCommonCdVO> listCommonCodeByCdExt (CoCommonCdVO inputObj) throws Exception; 
	
	
	public List<CoCommonCdVO> selectListcommonCode(CoCommonCdVO coCommonCdVO,  RowBounds rowbounds) throws Exception;
	

}