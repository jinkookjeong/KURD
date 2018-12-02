package iq.kurd.com.co.menu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import iq.kurd.com.co.menu.dao.MenuDAO;
import iq.kurd.com.co.menu.service.MenuService;
import iq.kurd.com.co.menu.vo.MenuVO;

@Service("menuService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService {

	@Resource(name = "menuDAO")
	MenuDAO menuDAO;
	

	@Override
	public List<MenuVO> selectTopMenuList(Map<String, Object> paraMap) throws Exception {
		return menuDAO.selectTopMenuList(paraMap);
	}

	@Override
	public List<MenuVO> selectLeftMnList(Map<String, Object> paraMap) throws Exception {
		return menuDAO.selectLeftMnList(paraMap);
	}

	@Override
	public String selectMenuId(String upperMenuId) throws Exception {
		return menuDAO.selectMenuId(upperMenuId);
	}

}
