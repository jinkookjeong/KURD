package iq.kurd.com.co.menu.vo;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class MenuVO extends AbstractVO {	

	private String menuId;
	private String upperMenuId;
	private String menuType;
	private String menuUrl;
	private String menuCd;
	private String menuMnEn;
	private String menuMnAb;
	private String menuMnKo;
	
	private int menuLavel;
	private int menuOrder;
	
}
