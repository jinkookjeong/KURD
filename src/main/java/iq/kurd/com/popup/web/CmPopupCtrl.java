package iq.kurd.com.popup.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("cmPopupCtrl")
public class CmPopupCtrl {

	@RequestMapping(value = "/pt/ng/sample/comPopupOrgSearch.do")
	public String popupTest(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		return "comPopupOrgSearch.com";
	}
}
