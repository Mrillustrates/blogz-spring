package org.launchcode.blogz.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PostDao extends CrudRepository<Post, Integer> {
    
    List<Post> findByAuthor(int authorId);
    
    // TODO - add method signatures as needed
	//Post findByAuthorId(int authorId);
  
    Post findByUid(int uid);

    

	//Post findByUsername(String username);

	//Post findByAuthorId(int id);
}
