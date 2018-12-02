package iq.kurd.com.co.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iq.kurd.com.co.menu.vo.MenuVO;

/**
 * 메뉴 조회
 * @author jwchoi
 *
 */
public interface MenuService {


	/**
	 * TopMenu List Get
	 * @param paraMap
	 * @return TopMenu List
	 * @throws Exception
	 */
	List<MenuVO> selectTopMenuList(Map<String, Object> paraMap)throws Exception;

	/**
	 * LeftMenu List Get
	 * @param paraMap
	 * @return
	 */
	List<MenuVO> selectLeftMnList(Map<String, Object> paraMap)throws Exception;

	/**
	 * menuId select
	 * @param upperMenuId
	 * @return
	 * @throws Exception
	 */
	String selectMenuId(String upperMenuId)throws Exception;
	

}
