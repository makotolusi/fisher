package com.fisher.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Broad {

	public static final String POSTS="posts";
	public static final String ID="id";

	@Id
	private String id;
	private String title;
	private String imgUrl;
	private String description;
	private Date createDate;
	@Transient
	private BroadType broadType;
	
	@DBRef
	private List<Post> posts=new ArrayList<Post>();
	
	
	public void addPost(Post post){
		posts.add(post);
		
	}
	
	
	public List<Post> getPosts() {
		return posts;
	}


	public BroadType getBroadType() {
		return broadType;
	}
	public void setBroadType(BroadType broadType) {
		this.broadType = broadType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public enum BroadType {
		NORMAL("台钓"), DEEPSEA("海钓"), EXIST("已存在");
		// 成员变量
		private String msg;

		// 构造方法
		private BroadType(String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}

	}
	
}
