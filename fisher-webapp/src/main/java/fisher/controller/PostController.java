package fisher.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fisher.app.domain.Post;
import com.fisher.app.service.PostServiceI;
import com.fisher.app.util.Utils;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostServiceI postServiceI;

	@ResponseBody
	@RequestMapping(value = "/write/{broadId}/{uid}/{title}")
	public Map<String,Object> writeToBroad(
			@PathVariable String broadId,
			@PathVariable String uid,
			@PathVariable String title,
			@RequestParam(value = "content", required = false) String content,
			HttpServletRequest request
			) {
		Post post=new Post();
		post.createAuthor(uid);
		post.setTitle(title);
		post.setContent(content);
		try {
			ServletInputStream inputStream=request.getInputStream();
			String imgId=postServiceI.gridFSInput(inputStream,"xl").get("imgId").toString();
			post.setImgId(imgId);
			System.out.println(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return postServiceI.writeByBroadId(post, broadId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryByBroadId/{broadId}/{page}/{size}")
	public Map<String,Object> queryByBroadId(
			@PathVariable Integer page,
			@PathVariable Integer size,
			@PathVariable String broadId
			) {
		return postServiceI.queryPostByBroadId(broadId, page, size);
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveImg")
	public Map<String,Object> saveImg(
			) {
		return null;//postServiceI.gridFSInput();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getImg")
	public Map<String,Object> getImg(
			HttpServletResponse response, ModelMap result
			) {
		try {
			response.setContentType("image/jpeg");
			  postServiceI.findFileByName("",response.getOutputStream());
			  response.flushBuffer();
			  return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
