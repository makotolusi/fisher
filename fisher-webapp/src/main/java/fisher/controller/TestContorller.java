package fisher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestContorller {

	  @RequestMapping(value = "/test")
	    public String getTest( ModelMap model) {
//		  ModelAndView mv = new ModelAndView("/index/index","command","LOGIN SUCCESS, " + username);
	      System.out.println("===============");
	        return "index";
	    }
}
