package fisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fisher.app.service.InitServiceI;

@Controller
@RequestMapping("/init")
public class InitController {

	@Autowired
	private InitServiceI initServiceI;


	/***
	 * the whole village soon learnt that a large sum of money had been lost
	 * the local butcher
	 */
	@ResponseBody
	@RequestMapping(value = "/run")
	public void run() {
		 initServiceI.run();
	}
	
}
