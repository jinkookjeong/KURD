package iq.kurd.com.co.main.vo;

import java.util.Map;
import java.util.HashMap;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import iq.kurd.com.AbstractVO;
import lombok.Data;

import java.math.BigDecimal;
   
/**
 * 포탈 메인메뉴 QVO : Portal Main Menu QVO
 * @fileName  : PtMainMenuQVO.java
 * @package   : iq.kurd.com.co.main.vo
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */  
@Data
@SuppressWarnings("serial")
public class PtMainMenuQVO extends AbstractVO{ 
  
	private String menuId;

	private String menuNm;

	private String menuClCd;

	private String rscUrl;

	private BigDecimal dispOrd;

	private BigDecimal menuLvl;

	private String menuDefault;

	private BigDecimal menuDefaultOrd;

	private BigDecimal menuOrd;

	private String hasChild;


}