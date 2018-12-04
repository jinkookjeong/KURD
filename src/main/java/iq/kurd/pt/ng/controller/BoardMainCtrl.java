package iq.kurd.pt.ng.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.Gson;

import iq.kurd.com.AbstractCtrl;
import iq.kurd.com.co.code.service.CoCommonCdService;
import iq.kurd.com.co.code.vo.CoCommonCdVO;
import iq.kurd.com.co.login.StrCtnt;
import iq.kurd.com.co.login.vo.JonepsActor;
import iq.kurd.com.util.format.DateUtil; 
import iq.kurd.pt.ng.vo.NgBoardVO;
 

@Controller("boardMainCtrl")
public class BoardMainCtrl extends AbstractCtrl{
	
	@Resource(name="coCommonCdService")
	CoCommonCdService coCommonCdService;
	
	@Autowired
	private MessageSource messgeSource;
	
	
	//로그인 테스트
	@RequestMapping(value = "/page.do")
	public String page(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		System.out.println("page call");
		
		return "page.com";
	}
	
	@RequestMapping(value = "/pt/ng/sample/boardList.do")
	public String boardList(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		Locale loc = RequestContextUtils.getLocale(request);
		log.info("locale: "+loc.getLanguage());
		
		log.info("Wellcome page mapping! ");
		return "NgBoardList.ng";
	}
	
	@RequestMapping(value = "/pt/ng/sample/boardPage.do")
	public String boardPage(@ModelAttribute("ngBoardVO") NgBoardVO ngBoardVO, HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {

		String viewType = ngBoardVO.getViewType() == null ? "" : ngBoardVO.getViewType();
		String forwordUrl = "";
		
		 switch( viewType )
		 {
			case "new":
				String noti = DateUtil.getCurrentTime("yyyyMMdd")+DateUtil.getCurrentTime("hhmmss");//+"-"+(new Random(100)).nextInt();		
				ngBoardVO.setTendNo(noti);				
				
				log.info("Tend NO: "+noti);
				
				model.addAttribute("ngBoardVO", ngBoardVO); 
				
				CoCommonCdVO coCommonCdVO = new CoCommonCdVO(); //Common Code
				coCommonCdVO.setUpCd("EP1830");			
				List<CoCommonCdVO> listCommonCode = coCommonCdService.selectListCommonCode(coCommonCdVO);
				Gson gson = new Gson();
				model.addAttribute("cdTendType", gson.toJson(listCommonCode));
				forwordUrl = "NgBoardNew";
				break;
				
			case "detail":
				log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				log.info(ngBoardVO.toString());
				model.addAttribute("ngBoardVO", ngBoardVO);
				
				forwordUrl = "NgBoardDetail";
				break;
				
			case "modify":
				model.addAttribute("ngBoardVO", ngBoardVO);
				
				forwordUrl = "NgBoardModify";
				break;
				
		    default :
		    	forwordUrl = "NgBoardList";
	             break;
	        }
		 
		log.info("The next page");
		
		return forwordUrl+".ng";
	}

	
	@RequestMapping(value = "/pt/ng/sample/boardNew.do")
	public String registerBoard(@ModelAttribute("ngBoardVO") NgBoardVO ngBoardVO,
			 ModelMap model) throws Exception{
	
		//Init data setting.
		//Make tend number
		//ngBoardVO ngBoardVO = new ngBoardVO();	
		
		String noti = DateUtil.getCurrentTime("yyyyMMdd")+DateUtil.getCurrentTime("hhmmss");//+"-"+(new Random(100)).nextInt();		
		ngBoardVO.setTendNo(noti);				
		
		log.info("Tend NO: "+noti);
		
		model.addAttribute("ngBoardVO", ngBoardVO); 
		
		CoCommonCdVO coCommonCdVO = new CoCommonCdVO(); //Common Code
		coCommonCdVO.setUpCd("EP1830");			
		List<CoCommonCdVO> listCommonCode = coCommonCdService.selectListCommonCode(coCommonCdVO);
		Gson gson = new Gson();
		model.addAttribute("cdTendType", gson.toJson(listCommonCode));
		 
		return "NgBoardNew.ng";
	}
	
	//상세화면
	@RequestMapping(value = "/pt/ng/sample/boardDetail.do")
	public String boardDetail(@ModelAttribute("ngBoardVO") NgBoardVO ngBoardVO, HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
	
		model.addAttribute("ngBoardVO", ngBoardVO);
		
		return "NgBoardDetail.ng";
	}
	
	//상세화면에서 다시 수정화면으로 전환
	@RequestMapping(value = "/pt/ng/sample/boardModify.do")
	public String boardModify(@ModelAttribute("sampleBoardVO") NgBoardVO ngBoardVO, HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		model.addAttribute("ngBoardVO", ngBoardVO);
		
		return "NgBoardModify.ng";
	}
	
}
