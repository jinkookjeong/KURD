package iq.kurd.com.co.zip.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import iq.kurd.com.co.zip.service.CoZipService;
import iq.kurd.com.co.zip.vo.CoZipSVO;

/**
 * 우편번호 조회 Controller : zip code Controller
 * @fileName  : CoZipCTRL.java
 * @package   : iq.kurd.com.co.zip.web
 * @since     : 2018. 2. 27.
 * @author    : Jin Kook JEONG
 */
@Controller("coZipCTRL")
public class CoZipCTRL {

	@Resource(name="coZipService")
	CoZipService coZipService;


	/**
	 * 우편번호 리스트 : forwardCoZipList Method
	 * @methodName : forwardCoZipList
	 * @return     : String
	 * @param coZipSVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/co/zip/forwardCoZipPopup.do")
	public String forwardCoZipList (@ModelAttribute("coZipSVO") CoZipSVO coZipSVO
			                       , ModelMap model) throws Exception {

		if(coZipSVO.getAddr1() != null){
			coZipService.selectListAddr1(coZipSVO);
			model.addAttribute("addr1List", coZipSVO.getCoZipAddr1List());
			coZipService.selectListAddr2(coZipSVO);
			model.addAttribute("addr2List", coZipSVO.getCoZipAddr2List());
			coZipService.selectListAddr3(coZipSVO);
			model.addAttribute("addr3List", coZipSVO.getCoZipAddr3List());
		}else{
			coZipService.selectListAddr1(coZipSVO);
			model.addAttribute("addr1List", coZipSVO.getCoZipAddr1List());
		}

		return "/co/zip/CoZipPopup";

	}

	/**
	 * 주소2 조회 : selectListAddr2 Method
	 * @methodName : selectListAddr2
	 * @return     : String
	 * @param coZipSVO
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/co/zip/selectListAddr2.do")
	public String selectListAddr2 (@ModelAttribute("coZipSVO") CoZipSVO coZipSVO
								      , ModelMap model) throws Exception {


		coZipService.selectListAddr2(coZipSVO);
		model.addAttribute("resultList", coZipSVO.getCoZipAddr2List());

		return "/co/zip/CoZipPopupFrame";

	}


	/**
	 * 주소3조회 : selectListAddr3 Method
	 * @methodName : selectListAddr3
	 * @return     : String
	 * @param coZipSVO
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/co/zip/selectListAddr3.do")
	public String selectListAddr3 (@ModelAttribute("coZipSVO") CoZipSVO coZipSVO
								      , ModelMap model) throws Exception {


		coZipService.selectListAddr3(coZipSVO);
		model.addAttribute("resultList", coZipSVO.getCoZipAddr3List());

		return "/co/zip/CoZipPopupFrame";

	}

	/**
	 * 우편번호페이지 조회 :  selectListPageCoZip Method
	 * @methodName : selectListPageCoZip
	 * @return     : String
	 * @param coZipSVO
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/co/zip/selectListPageCoZip.do")
	public String selectListPageCoZip (@ModelAttribute("coZipSVO") CoZipSVO coZipSVO
								      , ModelMap model) throws Exception {


		coZipService.selectListPageCoZip(coZipSVO);
		model.addAttribute("resultList", coZipSVO.getCoZipQVOList());

		return "/co/zip/CoZipPopupFrame";

	}


}
