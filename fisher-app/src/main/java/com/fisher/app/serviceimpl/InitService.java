package com.fisher.app.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.fisher.app.domain.Broad;
import com.fisher.app.domain.Person;
import com.fisher.app.domain.Post;
import com.fisher.app.repository.BroadRepositoryI;
import com.fisher.app.service.BroadServiceI;
import com.fisher.app.service.InitServiceI;
import com.fisher.app.util.Utils;

@Service
public class InitService implements InitServiceI{

	@Autowired
	private MongoOperations mongoOperations;
	public void run() {

		if (!mongoOperations.collectionExists(Person.class)) {
			mongoOperations.createCollection(Person.class);
		}

		if (!mongoOperations.collectionExists(Broad.class)) {
			mongoOperations.createCollection(Broad.class);
		}
		if (!mongoOperations.collectionExists(Post.class)) {
			mongoOperations.createCollection(Post.class);
		}
		this.runInitData();
		
	}
	
	public void runInitData() {
		mongoOperations.dropCollection(Broad.class);
		mongoOperations.createCollection(Broad.class);
		String s[]={"台钓专区","HARA专区","海钓专区","竞技比赛","二手渔具","钓饵交流","钓技交流","钓竿交流","池塘信息","野钓信息","度假村"};
		for (int i = 0; i < s.length; i++) {
			Broad broad=new Broad();
			broad.setTitle(s[i]);
			broad.setCreateDate(new Date());
//			broad.setImgUrl("xx_broad_image_"+(i+1)+".jpg");
			mongoOperations.insert(broad);
		}
		
	}

}
