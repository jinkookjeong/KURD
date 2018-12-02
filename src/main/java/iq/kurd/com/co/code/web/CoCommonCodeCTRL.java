package iq.kurd.com.co.code.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import iq.kurd.com.co.code.service.CoCommonCdService;
import iq.kurd.com.co.code.vo.CoCommonCdVO;
import iq.kurd.com.util.format.LocaleUtil;

/**
 * 공통코드 관리 Control : Common Code Control
 * @fileName  : CoCommonCodeCTRL.java
 * @package   : iq.kurd.com.co.code.web
 * @since     : 2018. 2. 25.
 * @author    : Jin Kook JEONG
 */
@Controller("coCommonCodeCTRL")
public class CoCommonCodeCTRL {

	@Resource(name="coCommonCdService")
	CoCommonCdService coCommonCodeService;

	/**
	 * 공통코드 리스트 : list Common Code
	 * @methodName : forwardCoCommonCdList
	 * @return     : String
	 * @param coCommonCodeSVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/co/code/forwardCoCommonCodePopup.do")
	public String forwardCoCommonCdList (@ModelAttribute("coCommonCodeVO") CoCommonCdVO coCommonCodeVO
								  , ModelMap model) throws Exception {

		return "/popup/CoCommonCodePopup.co";

	}


	/**
	 * 공통코드 페이지 리스트 : Common code page list
	 * @methodName : selectListPageCoCommonCode
	 * @return     : String
	 * @param coCommonCodeSVO
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/co/code/selectListPageCoCommonCode.do")
	public String selectListPageCoCommonCode (@ModelAttribute("coCommonCodeVO") CoCommonCdVO coCommonCodeVO
								  , ModelMap model) throws Exception {

		//coCommonCodeVO.setLoc(LocaleUtil.getLangCode());
		coCommonCodeService.selectListPageCommonCode(coCommonCodeVO);
		model.addAttribute("resultList", coCommonCodeVO.getCoCommonCdVOList());

		return "/popup/CoCommonCodePopupFrame.co";
	}
}
