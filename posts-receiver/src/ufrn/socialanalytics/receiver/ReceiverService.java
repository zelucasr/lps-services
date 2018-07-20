package ufrn.socialanalytics.receiver;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Path;

import ufrn.socialanalytics.receiver.dao.PostDAOModel;
import ufrn.socialanalytics.receiver.model.Post;

@Path("/receiver")
public class ReceiverService {
	
	private PostDAOModel db;
	
	@Path("/sendpost")
	public String receivePost(Post post){
		return "";
	}
	
	@Path("/deletepost")
	public String deletePost(Post post){
		return "";
	}
	
	@Path("/updatepost")
	public String updatePost(Post post){
		return "";
	}
	
	@Path("/getall")
	public ArrayList<Post> getAll(){
		return null;
	}
	
	@Path("/getpost")
	public Post getPost(Integer id){
		return null;
	}
	
	@Path("/getpostbyinterval")
	public ArrayList<Post> getPostByInterval(Date init, Date end){
		return null;
	}

}
