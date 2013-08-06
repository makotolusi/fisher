package com.fisher.app.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.fisher.app.domain.Post;


public interface PostServiceI {


	
	public Map<String, Object> writeByBroadId(Post post,String id) ;

	public Map<String, Object> queryPostByBroadId(String id,int s,int e);
	
	public Map<String,Object> gridFSInput(InputStream imgByte,String fileName);
	public Map<String,Object> findFileByName(String fileName,OutputStream out);
}
