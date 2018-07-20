package ufrn.socialanalytics.receiver.dao;

import java.util.ArrayList;
import java.util.Date;

import ufrn.socialanalytics.receiver.model.Post;

public interface PostDAOModel {

	public boolean insert(Post post);

	public boolean remove(Post post);

	public boolean update(Post post);

	public Post getPostById(int id);

	public ArrayList<Post> getPostByInterval(Date init, Date end);

	public ArrayList<Post> getAll();

}
