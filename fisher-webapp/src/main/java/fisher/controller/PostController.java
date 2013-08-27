package fisher.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
			@RequestParam( required = false) MultipartFile[] myfiles,
			HttpServletRequest request
			) {
//		 for(MultipartFile myfile : myfiles){  
//	            if(myfile.isEmpty()){  
//	                System.out.println("文件未上传");  
//	            }else{  
//	                System.out.println("文件长度: " + myfile.getSize());  
//	                System.out.println("文件类型: " + myfile.getContentType());  
//	                System.out.println("文件名称: " + myfile.getName());  
//	                System.out.println("文件原名: " + myfile.getOriginalFilename());  
//	                System.out.println("========================================");  
//	                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
////	                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
//	                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
////	                FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));  
//	            }  
//	        }  
		Post post=new Post();
		post.createAuthor(uid);
		post.setTitle(title);
		post.setContent(content);
		MultipartHttpServletRequest request2=(MultipartHttpServletRequest)request;
		Map<String,MultipartFile> i=request2.getFileMap();
		List<String> imageid=new ArrayList<String>();
		List<InputStream> inputs=new ArrayList<InputStream>();
		for (Iterator iterator = request2.getFileNames(); iterator.hasNext();) {
			String filename= (String) iterator.next();
			try {
				inputs.add(request2.getFile(filename).getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(d.getInputStream());
		}
		try {
			
			ServletInputStream inputStream=request.getInputStream();
			 imageid=(List<String>)postServiceI.gridFSInput(inputs,"xl").get("imgId");
			post.setImgId(imageid);
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
	@RequestMapping(value = "/getImg/{postId}")
	public Map<String,Object> getImg(
			@PathVariable String postId,
			HttpServletResponse response, ModelMap result
			) {
		try {
			response.setContentType("image/jpeg");
			  postServiceI.findFileByName(postId,response.getOutputStream());
			  response.flushBuffer();
			  return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
