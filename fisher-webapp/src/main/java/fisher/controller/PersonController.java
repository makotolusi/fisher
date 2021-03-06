package fisher.controller;

import java.util.List;
import java.util.Map;

import com.fisher.app.domain.Person;
import com.fisher.app.service.PersonServiceI;
import com.fisher.app.util.Password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServiceI personServiceI;

	@ResponseBody
	@RequestMapping(value = "/login/{eml}/{psw}")
	public Map<String, String> login(@PathVariable String eml,@PathVariable String psw, ModelMap model) {
		Person p = new Person();
		p.setEmail(eml);
		String password = Password.createPassword(psw);
		p.setPassword(password);
		return personServiceI.login(p);
	}

	@ResponseBody
	@RequestMapping(value = "/register")
	public Map<String, String> register(ModelMap model) {
		Person p = new Person();
		p.setEmail("lusi@sina.com");
		String password = Password.createPassword("123");
		p.setPassword(password);
		return personServiceI.register(p);
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryAll")
	public List<Person> queryAll(ModelMap model) {
		
		return personServiceI.queryAll();
	}
	
}
