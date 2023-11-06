package com.bacom.rt.model.dao;

import com.bacom.rt.model.entity.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostsDao extends CrudRepository<Posts,Integer> {
}
