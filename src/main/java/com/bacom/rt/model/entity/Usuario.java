package com.bacom.rt.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    @Column(name="password")
    private String password;
    @Column(name="name")
    private String name;
    @Column(name="lastname")
    private String lastName;
    @Column(name="cellphone")
    private String cellphone;
    @Column(name="fec_user_reg")
    private LocalDateTime fec_usu_reg;
    @Column(name="fec_user_mod")
    private LocalDateTime fec_usu_mod;
}
