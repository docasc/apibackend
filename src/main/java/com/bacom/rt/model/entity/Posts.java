package com.bacom.rt.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "posts")
public class Posts {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;
    @Column(name="text")
    private String text;
    @Column(name="iduser")
    private Integer idUser;
    @Column(name="fec_post_reg")
    private LocalDateTime fec_post_reg;
    @Column(name="fec_post_mod")
    private LocalDateTime fec_post_mod;
}
