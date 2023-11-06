package com.bacom.rt.model.dao;

import com.bacom.rt.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario,Integer> {

}
