package iq.kurd.com.co.menu.vo;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class MenuIdVO extends AbstractVO {	
 
	private String menuId ;
	private String upperMenuId;
	private String subMenuId;
	
}
