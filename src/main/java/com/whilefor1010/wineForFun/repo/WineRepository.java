package com.whilefor.blogITP.repo;

import com.whilefor.blogITP.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {


}
