package com.fisher.app.fisher_app;

import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;


public class PostFile {
  public static void main(String[] args) throws Exception {
    HttpClient httpclient = new DefaultHttpClient();
    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

    HttpPost httppost = new HttpPost("http://localhost:8080/fisher-webapp/post/write/51ef9456fb620441734fc8bd/51ee1838fb62a835787403a6/%E6%88%91%E8%A6%81%E5%8E%BB%E9%92%93%E9%B1%BC");
    File file = new File("D:\\QQ图片20130805171641.jpg");

    FileEntity reqEntity = new FileEntity(file, "binary/octet-stream");

    httppost.setEntity(reqEntity);
    reqEntity.setContentType("binary/octet-stream");
    System.out.println("executing request " + httppost.getRequestLine());
    HttpResponse response = httpclient.execute(httppost);
    HttpEntity resEntity = response.getEntity();

    System.out.println(response.getStatusLine());
    if (resEntity != null) {
      System.out.println(EntityUtils.toString(resEntity));
    }
    if (resEntity != null) {
      resEntity.consumeContent();
    }

    httpclient.getConnectionManager().shutdown();
  }
}
