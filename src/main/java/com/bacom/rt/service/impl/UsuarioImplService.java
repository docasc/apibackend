package com.bacom.rt.service.impl;

import com.bacom.rt.model.dto.UsuarioDto;
import com.bacom.rt.model.entity.Usuario;
import com.bacom.rt.model.dao.UsuarioDao;
import com.bacom.rt.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UsuarioImplService implements IUsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public List<Usuario> listAll() {

        return (List) usuarioDao.findAll();
    }

    @Transactional
    @Override
    public Usuario save(UsuarioDto usuarioDto) {
        Usuario usuario = Usuario.builder()
                .idUser(usuarioDto.getIdUser())
                .name(usuarioDto.getName())
                .lastName((usuarioDto.getLastName()))
                .cellphone(usuarioDto.getCellphone())
                .password(usuarioDto.getCellphone())
                .fec_usu_reg(LocalDateTime.now())
                .fec_usu_mod(LocalDateTime.now())
                .build();
        return usuarioDao.save(usuario);
    }

    @Transactional
    @Override
    public Usuario update(UsuarioDto usuarioDto) {
        Usuario usuario = Usuario.builder()
                .idUser(usuarioDto.getIdUser())
                .name(usuarioDto.getName())
                .lastName((usuarioDto.getLastName()))
                .cellphone(usuarioDto.getCellphone())
                .password(usuarioDto.getCellphone())
                .fec_usu_reg(LocalDateTime.parse(usuarioDto.getFec_user_reg(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .fec_usu_mod(LocalDateTime.now())
                .build();
        return usuarioDao.save(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario findById(Integer id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public boolean existsById(Integer id) {
        return usuarioDao.existsById(id);
    }
}
