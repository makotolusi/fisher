package com.fisher.app.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Post {


	@Id
	private String id;
	private String title;
	private Date createDate;
	private int replayNum;
	private String content;
	private List<String> imgId;
	
	@DBRef
	private Broad broad;

	@DBRef
	private Person person;

	
	public Person getPerson() {
		return person;
	}
	public Person createAuthor(String id){
		Person p= new Person();
		p.setId(id);
		person=p;
		return p;
	}
	public void createBroad(String id){
		Broad b=new Broad();
		b.setId(id);
		broad=b;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	

	
	public List<String> getImgId() {
		return imgId;
	}
	public void setImgId(List<String> imgId) {
		this.imgId = imgId;
	}
	public Broad getBroad() {
		return broad;
	}
	public void setBroad(Broad broad) {
		this.broad = broad;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getReplayNum() {
		return replayNum;
	}
	public void setReplayNum(int replayNum) {
		this.replayNum = replayNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
