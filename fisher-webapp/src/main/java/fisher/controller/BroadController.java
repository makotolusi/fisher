package fisher.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fisher.app.service.BroadServiceI;

@Controller
@RequestMapping("/broad")
public class BroadController {

	@Autowired
	private BroadServiceI broadServiceI;


	
	@ResponseBody
	@RequestMapping(value = "/queryAll")
	public Map<String,Object> queryAll(ModelMap model) {
		return broadServiceI.queryBroadAll();
	}
	
}
