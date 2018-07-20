package ufrn.socialanalytics.receiver.dao;

import java.util.ArrayList;
import java.util.Date;

import ufrn.socialanalytics.receiver.model.Post;

public class PostDAO implements PostDAOModel {
	// fazer implementação com firebase
	@Override
	public boolean insert(Post post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Post post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Post post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Post getPostById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> getPostByInterval(Date init, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
