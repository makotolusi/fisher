package com.fisher.app.serviceimpl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.fisher.app.dao.CommonDaoI;
import com.fisher.app.domain.Broad;
import com.fisher.app.domain.Post;
import com.fisher.app.repository.PostRepositoryI;
import com.fisher.app.service.PostServiceI;
import com.fisher.app.util.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Service
public class PostService implements PostServiceI {

	@Autowired
	private MongoOperations mongoTemplate;

	@Autowired
	private PostRepositoryI postRepositoryI;

	@Autowired
	private CommonDaoI commonDaoI;

	public Map<String, Object> writeByBroadId(Post post, String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		post.createBroad(id);

		post = postRepositoryI.save(post);
		result.put(Utils.RESULT, post);
		return Utils.resultSUC(result);
	}

	public Map<String, Object> queryPostByBroadId(String id, int s, int e) {
		Map<String, Object> result = new HashMap<String, Object>();
		Broad broad = new Broad();
		broad.setId(id);
		Page<Post> posts = postRepositoryI.findByBroad(broad, new PageRequest(
				s, e));
		result.put(Utils.RESULT, posts);
		return Utils.resultSUC(result);
	}

	public Map<String, Object> gridFSInput(List<InputStream> imgByte,
			String fileName) {
		Map<String, Object> result = new HashMap<String, Object>();
		DB db = mongoTemplate.getCollection(
				mongoTemplate.getCollectionName(Post.class)).getDB();
		List<String> imgIds=new ArrayList<String>();
		for (Iterator iterator = imgByte.iterator(); iterator.hasNext();) {
			InputStream i = (InputStream) iterator.next();

			db.requestStart();
			GridFSInputFile gfsInput;

			try {
				gfsInput = new GridFS(db, Utils.IMG).createFile(i);
				gfsInput.setFilename(fileName);
				// gfsInput.setContentType(Utils.IMG);
				// 保存到数据库的文件名为qq123456789logo
				gfsInput.save();
				imgIds.add( gfsInput.getId().toString());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			db.requestDone();
		}
		result.put("imgId",imgIds);
		return result;
	}

	public Map<String, Object> findFileByName(String postId, OutputStream out) {
		DB db = mongoTemplate.getCollection(
				mongoTemplate.getCollectionName(Post.class)).getDB();
		Map<String, Object> result = new HashMap<String, Object>();
		Post posts=postRepositoryI.findById(postId);
//		BasicDBObject dbo = new BasicDBObject();
//		for (Iterator iterator = posts.getImgId().iterator(); iterator.hasNext();) {
//			String imgid = (String) iterator.next();
//			dbo.put("id", imgid);
//		}
		GridFSDBFile gfsFile;
		try {
//			gfsFile = new GridFS(db, Utils.IMG).findOne(new ObjectId(posts.getImgId().get(0)));
//			gfsFile.writeTo(out);
			gfsFile = new GridFS(db, Utils.IMG).findOne(new ObjectId(posts.getImgId().get(1)));
			gfsFile.writeTo(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
