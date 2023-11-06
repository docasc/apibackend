package com.bacom.rt.controller;

import com.bacom.rt.model.dto.UsuarioDto;
import com.bacom.rt.model.entity.Usuario;
import com.bacom.rt.model.payload.MensajeResponse;
import com.bacom.rt.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("usuarios")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showAll(){
        List<Usuario> usuariosList = usuarioService.listAll();
        if (usuariosList == null || usuariosList.isEmpty()){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay usuarios registrados")
                            .object(null)
                            .build(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Usuario Encontrado")
                            .object(usuariosList)
                            .build(), HttpStatus.OK);
        }

    }

    @PostMapping("usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto){
        Usuario usuarioSave = null;
        try {
            usuarioSave = usuarioService.save(usuarioDto);
            usuarioDto = UsuarioDto.builder().idUser(usuarioSave.getIdUser())
                    .name(usuarioSave.getName())
                    .lastName((usuarioSave.getLastName()))
                    .cellphone(usuarioSave.getCellphone())
                    .password(usuarioSave.getCellphone())
                    .fec_user_reg(usuarioSave.getFec_usu_reg().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .fec_user_mod(usuarioSave.getFec_usu_mod().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build();

            return new ResponseEntity<>(MensajeResponse
                    .builder()
                    .mensaje("Se guardó correctamente!")
                    .object(usuarioDto).build(),HttpStatus.CREATED);

        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("usuario/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto,@PathVariable Integer id){
        Usuario usuarioUpdate = null;
        Usuario findUser = usuarioService.findById(id);
        try {
            if (usuarioService.existsById(id)){
                usuarioDto.setFec_user_reg(findUser.getFec_usu_reg().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                usuarioUpdate = usuarioService.update(usuarioDto);


                usuarioDto.setIdUser(id);
                usuarioDto = UsuarioDto.builder().idUser(findUser.getIdUser())
                        .name(findUser.getName())
                        .password(findUser.getPassword())
                        .lastName((findUser.getLastName()))
                        .cellphone(findUser.getCellphone())
                        .fec_user_reg(findUser.getFec_usu_reg().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .fec_user_mod(findUser.getFec_usu_mod().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build();

                return new ResponseEntity<>(MensajeResponse
                        .builder()
                        .mensaje("Se actualizó correctamente!")
                        .object(usuarioDto).build(),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El usuario no existe")
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

    @DeleteMapping("usuario/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        try{
            Usuario usuarioDelete = usuarioService.findById(id);
            usuarioService.delete(usuarioDelete);
            return new ResponseEntity<>(usuarioDelete, HttpStatus.NO_CONTENT);
        }catch(DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("usuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Usuario no encontrado")
                            .object(null)
                            .build(), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Usuario Encontrado")
                            .object(UsuarioDto.builder()
                                    .idUser(usuario.getIdUser())
                                    .name(usuario.getName())
                                    .lastName((usuario.getLastName()))
                                    .cellphone(usuario.getCellphone())
                                    .password(usuario.getCellphone())
                                    .fec_user_reg(usuario.getFec_usu_reg().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .fec_user_mod(usuario.getFec_usu_mod().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .build())
                            .build(), HttpStatus.OK);
        }

    }

}
