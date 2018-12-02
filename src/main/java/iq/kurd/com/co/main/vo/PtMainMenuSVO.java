package iq.kurd.com.co.main.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import iq.kurd.com.AbstractVO;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class PtMainMenuSVO extends AbstractVO{ 
	
	private String menuId;

	private String upperMenuId;

	private String menuNmKo;

	private String menuCd;

	private BigDecimal menuLvl;

	private String menuOrd;

	private String rscUrl;

	private String useYn;

	private String creId;

	private Timestamp creDt;

	private String menuType;

	private String menuDefault;

	private String imgUrl;

	private String urlTarget;

	private String dispCd;

	private String caYn;

	private String tip;

	private String menuNmEn;

	private String dispOrd;

	private String roleId;

    private List<PtMainMenuQVO> ptMainMenuQVOList;

	private PtMainMenuQVO ptMainMenuQVO;

	private String authCd;

	private String[] authCdArr;

	private String firstMenuId;

	private String defaultRscUrl;

}