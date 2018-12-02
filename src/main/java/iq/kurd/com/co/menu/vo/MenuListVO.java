package iq.kurd.com.co.menu.vo;

import java.util.List;

import iq.kurd.com.AbstractVO;
import lombok.Data;

	
@Data
@SuppressWarnings("serial")
public class MenuListVO extends AbstractVO {	
	
	private List<MenuVO> mnList;
	
}
