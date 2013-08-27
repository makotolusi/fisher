package com.fisher.app.fisher_app;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class PostFile {
	// public static void main(String[] args) throws Exception {
	// HttpClient httpclient = new DefaultHttpClient();
	// httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
	// HttpVersion.HTTP_1_1);
	//
	// HttpPost httppost = new
	// HttpPost("http://localhost:8080/fisher-webapp/post/write/51ef9456fb620441734fc8bd/51ee1838fb62a835787403a6/%E6%88%91%E8%A6%81%E5%8E%BB%E9%92%93%E9%B1%BC");
	// File file = new File("D:\\QQ图片20130805171641.jpg");
	//
	// FileEntity reqEntity = new FileEntity(file);
	//
	// httppost.setEntity(reqEntity);
	// reqEntity.setContentType("multipart/form-data;boundary=---------------------------7d4a6d158c9");
	// System.out.println("executing request " + httppost.getRequestLine());
	// HttpResponse response = httpclient.execute(httppost);
	// HttpEntity resEntity = response.getEntity();
	//
	// System.out.println(response.getStatusLine());
	// if (resEntity != null) {
	// System.out.println(EntityUtils.toString(resEntity));
	// }
	// if (resEntity != null) {
	// resEntity.consumeContent();
	// }
	//
	// httpclient.getConnectionManager().shutdown();
	// }


	 public static void main(String[] args) throws ClientProtocolException,  
     IOException {  
 HttpClient httpclient = new DefaultHttpClient();  
 //请求处理页面  
 HttpPost httppost = new HttpPost(  
         "http://localhost:8080/fisher-webapp/post/write/51ef9456fb620441734fc8bd/51ee1838fb62a835787403a6/qunimade");  
 //创建待处理的文件  
 FileBody file = new FileBody(new File("D:\\QQ图片20130805171641.jpg"));  
 
 //创建待处理的文件  
 FileBody file2 = new FileBody(new File("D:\\lusi.jpg")); 
 
 //创建待处理的表单域内容文本  
 StringBody descript = new StringBody("0431.la");  

 //对请求的表单域进行填充  
 MultipartEntity reqEntity = new MultipartEntity();  
 reqEntity.addPart("image1", file);  
 reqEntity.addPart("image2", file2);  
 //设置请求  
 httppost.setEntity(reqEntity);  
 //执行  
 HttpResponse response = httpclient.execute(httppost);  
 //HttpEntity resEntity = response.getEntity();  
 //System.out.println(response.getStatusLine());  
 if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){  
     HttpEntity entity = response.getEntity();  
     //显示内容  
     if (entity != null) {  
         System.out.println(EntityUtils.toString(entity));  
     }  
     if (entity != null) {  
         entity.consumeContent();  
     }  
 }  
}  
}
