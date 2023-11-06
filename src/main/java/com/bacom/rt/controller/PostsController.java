package com.bacom.rt.controller;

import com.bacom.rt.model.dto.PostsDto;
import com.bacom.rt.model.dto.UsuarioDto;
import com.bacom.rt.model.entity.Posts;
import com.bacom.rt.model.entity.Usuario;
import com.bacom.rt.model.payload.MensajeResponse;
import com.bacom.rt.service.IPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostsController {

    @Autowired
    private IPostsService postsService;

    @GetMapping("posts")
    public ResponseEntity<?> showAll() {
        List<Posts> postsList = postsService.listAll();
        if (postsList == null || postsList.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay Posts publicados")
                            .object(null)
                            .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Se encontraron posts")
                            .object(postsList)
                            .build(), HttpStatus.OK);
        }

    }

    @PostMapping("post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody PostsDto postsDto) {
        Posts postSave = null;
        try {
            postSave = postsService.save(postsDto);
            postsDto = PostsDto.builder().idPost(postSave.getIdPost())
                    .text(postSave.getText())
                    .idUser(postSave.getIdUser())
                    .fec_post_reg(postSave.getFec_post_reg().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .fec_post_mod(postSave.getFec_post_mod().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build();

            return new ResponseEntity<>(MensajeResponse
                    .builder()
                    .mensaje("Se guardó correctamente!")
                    .object(postsDto).build(), HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("post/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Posts posts = postsService.findById(id);
        if (posts == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Post no encontrado!")
                            .object(null)
                            .build(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Post encontrado!")
                            .object(PostsDto.builder().idPost(posts.getIdPost())
                                    .text(posts.getText())
                                    .idUser(posts.getIdUser())
                                    .build())
                            .build(), HttpStatus.OK);
        }
    }

    @DeleteMapping("post/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        try {
            Posts postDelete = postsService.findById(id);
            postsService.delete(postDelete);
            return new ResponseEntity<>(postDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("post/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody PostsDto postsDto, @PathVariable Integer id) {
        Posts postUpdate = null;
        Posts findPost = postsService.findById(id);
        try {
            if (postsService.existsById(id)) {
                postsDto.setFec_post_reg(findPost.getFec_post_reg().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                postsDto.setIdUser(findPost.getIdUser());
                postUpdate = postsService.update(postsDto);


                postsDto.setIdPost(id);
                postsDto = PostsDto.builder()
                        .idPost(findPost.getIdPost())
                        .text(findPost.getText())
                        .idUser(findPost.getIdUser())
                        .fec_post_reg(findPost.getFec_post_reg().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .fec_post_mod(findPost.getFec_post_mod().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build();

                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Se actualizó correctamente!")
                        .object(postsDto).build(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("EL POST NO EXISTE")
                                .object(null)
                                .build(), HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}