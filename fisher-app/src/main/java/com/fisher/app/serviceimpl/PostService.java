package com.fisher.app.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpRetryException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.fisher.app.dao.CommonDaoI;
import com.fisher.app.domain.Broad;
import com.fisher.app.domain.Person;
import com.fisher.app.domain.Post;
import com.fisher.app.repository.PostRepositoryI;
import com.fisher.app.service.PostServiceI;
import com.fisher.app.util.Utils;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Service
public class PostService implements PostServiceI{

	@Autowired
	private MongoOperations mongoTemplate;
	
	@Autowired
	private PostRepositoryI postRepositoryI;
	
	@Autowired
	private CommonDaoI commonDaoI;
	  
	  
	public Map<String, Object> writeByBroadId(Post post,String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		post.createBroad(id);
		
		post=postRepositoryI.save(post);
		result.put(Utils.RESULT, post);
		return Utils.resultSUC(result);
	}

	public Map<String, Object> queryPostByBroadId(String id,int s,int e) {
		Map<String, Object> result = new HashMap<String, Object>();
		Broad broad=new Broad();
		broad.setId(id);
		Page<Post> posts=postRepositoryI.findByBroad(broad, new PageRequest(s, e));
		result.put(Utils.RESULT, posts);
		return Utils.resultSUC(result);
	}
	
	public Map<String,Object> gridFSInput(InputStream imgByte,String fileName) {  
		Map<String, Object> result = new HashMap<String, Object>();
		  DB db = mongoTemplate.getCollection(  
		      		mongoTemplate.getCollectionName(Post.class)).getDB();
        db.requestStart();  
        GridFSInputFile gfsInput; 
        
        try {  
            gfsInput = new GridFS(db, Utils.IMG)
                    .createFile(imgByte);  
            gfsInput.setFilename(fileName);
//            gfsInput.setContentType(Utils.IMG);
           // 保存到数据库的文件名为qq123456789logo  
            gfsInput.save();  
            result.put("imgId", gfsInput.getId());
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        
        db.requestDone();  
        return result;
    }  
	
	public Map<String,Object> findFileByName(String fileName,OutputStream out){
		  DB db = mongoTemplate.getCollection(  
		      		mongoTemplate.getCollectionName(Post.class)).getDB();
		Map<String, Object> result = new HashMap<String, Object>();
	    GridFSDBFile gfsFile ;
	    try {      
	      gfsFile = new GridFS(db, "img").findOne(new ObjectId("5200c0e068cf9bf884e85bf8"));
	      gfsFile.writeTo(out);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return result;
	  }
	
}
