package iq.kurd.com.co.zip.service;

import iq.kurd.com.co.zip.vo.CoZipSVO;

/**
 * 우편번호  service : zip code service
 * @fileName  : CoZipService.java
 * @package   : iq.kurd.com.co.zip.biz
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
public interface CoZipService {
	
	/**
	 *  우편번호  목록 페이징 조회 : list page of zip code
	 * @methodName : selectListPageCoZip
	 * @return     : void
	 * @param coZIPSVO
	 * @throws Exception
	 */
	public void selectListPageCoZip(CoZipSVO coZIPSVO) throws Exception;
	
	/**
	 * 우편번호조회 : ZIP Code Views-1
	 * @methodName : selectListAddr1
	 * @return     : void
	 * @param coZipSVO
	 * @throws Exception
	 */
	public void selectListAddr1(CoZipSVO coZipSVO) throws Exception;
	
	/**
	 * 우편번호조회 : ZIP Code Views-2
	 * @methodName : selectListAddr2
	 * @return     : void
	 * @param coZipSVO
	 * @throws Exception
	 */
	public void selectListAddr2(CoZipSVO coZipSVO) throws Exception;
	
	/**
	 * 우편번호조회 : ZIP Code Views-3
	 * @methodName : selectListAddr3
	 * @return     : void
	 * @param coZipSVO
	 * @throws Exception
	 */
	public void selectListAddr3(CoZipSVO coZipSVO) throws Exception;

}
