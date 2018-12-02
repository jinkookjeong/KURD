package iq.kurd.com.co.menu.dao;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import iq.kurd.com.co.menu.vo.MenuVO;

/**
 * 
 * 메뉴 항목 조회 DAO interface
 * @author jwchoi
 *
 */
@Mapper("menuDAO")
public interface MenuDAO {


	/**
	 * Top Menu List
	 * @param paraMap
	 * @return
	 */
	public List<MenuVO> selectTopMenuList(Map<String, Object> paraMap) throws Exception;

	/**
	 * Left Menu List
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public List<MenuVO> selectLeftMnList(Map<String, Object> paraMap) throws Exception;


	/**
	 * selectMenuId
	 * @param upperMenuId
	 * @return
	 * @throws Exception
	 */
	public String selectMenuId(String upperMenuId)throws Exception;
}
