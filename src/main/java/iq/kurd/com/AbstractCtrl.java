package iq.kurd.com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.SessionAttributes;


@SessionAttributes("menuIdVO")
public class AbstractCtrl {
 
	protected Log log = LogFactory.getLog(super.getClass());
	
//	@ModelAttribute("menuIdVO")
//    public MenuIdVO menuIdVO(@ModelAttribute("menuIdVO") MenuIdVO mVO) {
//
//		return mVO;
//	}
}
