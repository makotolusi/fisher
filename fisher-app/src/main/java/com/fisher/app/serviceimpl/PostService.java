package com.fisher.app.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.fisher.app.dao.CommonDaoI;
import com.fisher.app.domain.Broad;
import com.fisher.app.domain.Person;
import com.fisher.app.domain.Post;
import com.fisher.app.service.PostServiceI;
import com.fisher.app.util.Utils;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

@Service
public class PostService implements PostServiceI{

	@Autowired
	private MongoOperations mongoTemplate;
	
	@Autowired
	private CommonDaoI commonDaoI;
	
	public Map<String, Object> writeByBroadId(Post post,String id) {
		Broad broad=(Broad)commonDaoI.findById(id, Broad.class);
		broad.addPost(post);
		
		Query query=new Query();
		query.addCriteria(Criteria.where(Broad.ID).is(id));
		Update update=new Update();
		update.update(Broad.POSTS, broad.getPosts());
		mongoTemplate.updateFirst(query, update, Broad.class);
		Map<String, Object> result = new HashMap<String, Object>();
		return Utils.resultSUC(result);
	}

	public Map<String, String> queryPostByBroadId(Person person) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void gridFSInput(File inputFile) {  
        DB db = mongoTemplate.getCollection(  
        		mongoTemplate.getCollectionName(Person.class)).getDB();  
        db.requestStart();  
//        File inputFile = new File(inputFilepath);  
        GridFSInputFile gfsInput;  
        try {  
            gfsInput = new GridFS(db, "fs")
                    .createFile(inputFile);  
            gfsInput.setFilename("qq123456789logo");// 保存到数据库的文件名为qq123456789logo  
            gfsInput.save();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        db.requestDone();  
  
    }  
}
