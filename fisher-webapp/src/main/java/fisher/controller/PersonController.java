package fisher.controller;


import java.util.List;

import com.fisher.app.domain.Person;
import com.fisher.app.service.PersonServiceI;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServiceI personServiceI;
	
	@ResponseBody
	  @RequestMapping(value = "/add")
	    public List<Person> add( ModelMap model) {
//		  ModelAndView mv = new ModelAndView("/index/index","command","LOGIN SUCCESS, " + username);
	      System.out.println("===============");
	      
	     return personServiceI.test();
	    }
}
