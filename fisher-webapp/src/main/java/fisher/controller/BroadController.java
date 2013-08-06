package fisher.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fisher.app.service.BroadServiceI;

@Controller
@RequestMapping("/broad")
public class BroadController {

	@Autowired
	private BroadServiceI broadServiceI;


	
	@ResponseBody
	@RequestMapping(value = "/queryAll/{page}/{size}")
	public Map<String,Object> queryAll(	
			@PathVariable Integer page,
			@PathVariable Integer size
			) {
		return broadServiceI.queryAll(page, size);
	}
	
	public static void main(String[] args) {
		Long l=1375113600L*1000;
		Date d=new Date();
		d.setTime(l);
		System.out.println(System.currentTimeMillis());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		  System.out.println("uDate: "+df.format(d));
	}
}
