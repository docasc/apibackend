package com.bacom.rt.service.impl;

import com.bacom.rt.model.dao.PostsDao;
import com.bacom.rt.model.dto.PostsDto;
import com.bacom.rt.model.dto.UsuarioDto;
import com.bacom.rt.model.entity.Posts;

import com.bacom.rt.model.entity.Usuario;
import com.bacom.rt.service.IPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostsImplService implements IPostsService {

    @Autowired
    private PostsDao postsDao;


    public List<Posts> listAll() {
        return (List) postsDao.findAll();
    }

    @Override
    public Posts save(PostsDto postsDto) {
        Posts posts = Posts.builder()
                .idPost(postsDto.getIdPost())
                .text(postsDto.getText())
                .idUser(postsDto.getIdUser())
                .fec_post_reg(LocalDateTime.now())
                .fec_post_mod(LocalDateTime.now())
                .build();
        return postsDao.save(posts);
    }

    public Posts update(PostsDto postsDto) {
        Posts post = Posts.builder()
                .idPost(postsDto.getIdPost())
                .text(postsDto.getText())
                .idUser(postsDto.getIdUser())
                .fec_post_reg(LocalDateTime.parse(postsDto.getFec_post_reg(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .fec_post_mod(LocalDateTime.now())
                .build();
        return postsDao.save(post);
    }

    @Override
    public Posts findById(Integer id) {
        return postsDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Posts posts) {
        postsDao.delete(posts);
    }

    @Override
    public boolean existsById(Integer id) {
        return postsDao.existsById(id);
    }
}
