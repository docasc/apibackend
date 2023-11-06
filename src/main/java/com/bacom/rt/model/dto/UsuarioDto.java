package com.bacom.rt.model.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
public class UsuarioDto implements Serializable {

    private Integer idUser;
    private String password;
    private String name;
    private String lastName;
    private String cellphone;
    private String fec_user_reg;
    private String fec_user_mod;
}
