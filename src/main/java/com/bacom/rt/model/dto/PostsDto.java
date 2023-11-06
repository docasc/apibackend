package com.bacom.rt.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PostsDto {

    private Integer idPost;
    private String text;
    private Integer idUser;
    private String fec_post_reg;
    private String fec_post_mod;
}
