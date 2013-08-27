package com.fisher.app.mongo.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.fisher.app.domain.Broad;
import com.fisher.app.domain.Person;
import com.fisher.app.domain.Post;

@Repository
public class HelloMongo {

	@Autowired
	MongoOperations mongoOperations;

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
