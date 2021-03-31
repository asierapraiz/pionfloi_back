package com.yoymico.starter.controllers;

import com.yoymico.starter.IServices.IAvatarService;
import com.yoymico.starter.IServices.IUsuarioService;
import com.yoymico.starter.models.entity.Avatar;
import com.yoymico.starter.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AvatarController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IAvatarService avatarService;

    //El id es del usuario
    @PostMapping("/avatar/save/{id}")
    public ResponseEntity<?> saveAvatar(@PathVariable Integer id, @RequestBody Avatar avatar){

        Map<String, Object> response = new HashMap<>( );

        Usuario usuario = usuarioService.findById(Long.valueOf(id));

        Usuario usuarioSaved;
        try {
            usuario.setAvatar(avatar);
             usuarioSaved = usuarioService.save(usuario);

        } catch (DataAccessException e) {
            response.put ( "mensaje" , "Error al realizar el insert en la base de datos" );
            response.put ( "error" , e.getMessage ( ).concat ( ": " ).concat ( e.getMostSpecificCause ( ).getMessage ( ) ) );
            return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.INTERNAL_SERVER_ERROR );
        }

        response.put ( "mensaje" , "El usuario ha sido creado con éxito!" );
        response.put ( "usuario" , usuarioSaved );

        return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.CREATED );
    }


    //El id es del usuario
    @PostMapping("/avatar/get/{id}")
    public ResponseEntity<?> getAvatar(@PathVariable Integer id){

        Map<String, Object> response = new HashMap<>( );

        Usuario usuario;
        Avatar avatar;

        try {

            usuario  = usuarioService.findById(Long.valueOf(id));
            //avatar = usuario.getAvatar();

        } catch (DataAccessException e) {
            response.put ( "mensaje" , "Error al realizar el insert en la base de datos" );
            response.put ( "error" , e.getMessage ( ).concat ( ": " ).concat ( e.getMostSpecificCause ( ).getMessage ( ) ) );
            return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.INTERNAL_SERVER_ERROR );
        }

        response.put ( "mensaje" , "Este es tu avatar!" );
        response.put ( "usuario" , usuario );

        return new ResponseEntity<Map<String, Object>> ( response , HttpStatus.CREATED );
    }

}
