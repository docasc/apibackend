package com.bacom.rt.service;



import com.bacom.rt.model.dto.PostsDto;
import com.bacom.rt.model.dto.UsuarioDto;
import com.bacom.rt.model.entity.Posts;
import com.bacom.rt.model.entity.Usuario;

import java.util.List;

public interface IPostsService {
    List<Posts> listAll();
    Posts save(PostsDto posts);
    Posts update(PostsDto post);
    Posts findById(Integer id);
    void delete(Posts Posts);
    boolean existsById(Integer id);
}
