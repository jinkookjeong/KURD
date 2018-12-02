package iq.kurd.com.login.ctrl;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAuthCtrl {

  @GetMapping("/main.do")
  public String index(Model model, Principal principal) {
	  System.out.println("main Welcome");
    model.addAttribute("message", "You are logged in as " + principal.getName());
    return "index.com";
  }
 
  @RequestMapping("/loginActions.do")
  public String loginActions(Model model) {
	  System.out.println("loginAction");
    model.addAttribute("loginAction", "You are logged in as ");
    return "index.com";
  }
  
  @RequestMapping("/cm/public/test1.do")
  public String test1(Model model) {
	  System.out.println("test1");
    model.addAttribute("test", "You are logged in as ");
    return "test.com";
  }
  
}