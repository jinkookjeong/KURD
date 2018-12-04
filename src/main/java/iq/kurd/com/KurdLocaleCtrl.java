package iq.kurd.com;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import iq.kurd.com.util.msg.ResourceMessage;

@Controller("kurdLocaleCtrl")
public class KurdLocaleCtrl extends AbstractCtrl{
		
	@Resource(name="messageSource")
	protected MessageSource messageSource;
	
	@Autowired
	protected CookieLocaleResolver localeResolver;
	
	@Autowired
	protected ResourceMessage resourceMessage; 
	
	@RequestMapping("/setLocale.do")
	public ModelAndView setLocale(@RequestParam(required = false) String lang,
		    ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException{

		log.info("setLocale: "+lang);
		String[] language = new String[]{"ko","ku","ar","en"};
		boolean bool = false;
		
		String rcvLang = lang.toLowerCase();
	    for (int i = 0; i < language.length; i++) {
			if(language[i].equals(rcvLang)) {
				bool = true;
			}
		}
	    Locale locale = null;;
		if(bool) {
			locale = new Locale(rcvLang);
		}else {
			locale = new Locale("en"); //관련언어가 없으면 무조건 English
		}
		
		localeResolver.setLocale(request, response, locale);
		 
		//log.info("######### resource message ##########");
		//log.info(resourceMessage.getResourceMsg("ng.board.list.title"));
		//log.info(resourceMessage.getResourceMsg("errors.minInteger", new String[]{"aaa","bbb"}));
		 
		//log.info("######### resource message ##########");
		//redirectStrategy.sendRedirect(request, response, "/WEB-INF/jsp/com/util/redirect.jsp");
		//return "redirect:/index.jsp" ;
	     return new ModelAndView("forward:/WEB-INF/jsp/com/util/redirect.jsp");
	     
	}
}

