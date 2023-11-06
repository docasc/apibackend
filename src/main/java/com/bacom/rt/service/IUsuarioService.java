package com.bacom.rt.service;

import com.bacom.rt.model.dto.UsuarioDto;
import com.bacom.rt.model.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> listAll();
    Usuario save(UsuarioDto usuario);
    Usuario update(UsuarioDto usuario);
    Usuario findById(Integer id);
    void delete(Usuario Usuario);
    boolean existsById(Integer id);
}
