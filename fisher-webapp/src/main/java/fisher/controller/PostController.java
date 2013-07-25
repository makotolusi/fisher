package fisher.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fisher.app.service.BroadServiceI;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private BroadServiceI broadServiceI;

	@ResponseBody
	@RequestMapping(value = "/write/{broadId}/{uid}/{title}")
	public Map<String,Object> writeToBroad(
			@PathVariable String broadId,
			@PathVariable String uid,
			@PathVariable String title,
			@RequestParam(value = "content", required = false) Long content
			) {
		return broadServiceI.queryBroadAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryAll")
	public Map<String,Object> queryAll(ModelMap model) {
		return broadServiceI.queryBroadAll();
	}
	
}
