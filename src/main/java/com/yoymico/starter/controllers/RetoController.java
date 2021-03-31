package com.yoymico.starter.controllers;



import com.yoymico.starter.models.dao.IUsuarioDao;
import com.yoymico.starter.models.dao.IRetoRepo;
import com.yoymico.starter.models.entity.Reto;
import com.yoymico.starter.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reto")
public class RetoController {

    @Autowired
    IRetoRepo rr;

    @Autowired
    IUsuarioDao userRepo;

    @GetMapping("/index")
    public List<Reto> index() {
        return rr.findAll();
    }

    @GetMapping("/git")
    public ResponseEntity<?> git() {

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Cabio para heroku!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> create(Principal principal, @RequestBody Reto reto) {
        Map<String, Object> response = new HashMap<>();

//        Reto reto = new Reto();
//        reto.setNombre("Sin definir");
//        reto.setTareas(tareas);
        Usuario user = userRepo.findByUsername(principal.getName());
        reto.setUser_id(user.getId());
        reto.getNombre();


        try {
            reto = rr.save(reto);

        } catch(DataAccessException e) {
            response.put("mensaje", "Error al guardar el reto");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Reto retoGuardado = rr.getOne(reto.getId());
        return new ResponseEntity<Reto>(retoGuardado, HttpStatus.OK);
    }

    @GetMapping("/reto/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Reto reto = null;
        Map<String, Object> response = new HashMap<>();

        try {
            reto = rr.findById(id).get();
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(reto == null) {
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Reto>(reto, HttpStatus.OK);
    }


    @PutMapping("/reto/{id}")
    public ResponseEntity<?> update(@RequestBody Reto reto, BindingResult result, @PathVariable Long id) {

        reto = rr.save(reto);

        Map<String, Object> response = new HashMap<>();

        response.put("mensaje", "El cliente ha sido actualizado con éxito!");
        response.put("reto", reto);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/reto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Reto reto = rr.findById(id).get();
            rr.delete(reto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el cliente de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        }

        response.put("mensaje", "El reto eliminado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


}


