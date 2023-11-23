package proyecto.app.sistemaGrifo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.app.sistemaGrifo.models.Role;
import proyecto.app.sistemaGrifo.models.Usuario;
import proyecto.app.sistemaGrifo.services.RoleServiceImpl;
import proyecto.app.sistemaGrifo.services.UsuarioServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuario")
public class UsuarioControllers {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private RoleServiceImpl roleService;


    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();


        try{
            usuarioService.deleteById(id);


        }catch (Exception e){
            res.put("mensaje","Ocurrio un error al eliminar un usuario"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("mensaje","El usuario ha sido eliminado con exito");
        return ResponseEntity.ok().body(res);

    }

    @GetMapping("/buscar/{dni}")

    public ResponseEntity<?> buscar(@PathVariable String dni){
        Map<String,Object> res=new HashMap<>();
        Usuario usuario=null;
        try {
            usuario=usuarioService.findByDni(dni);


        }catch ( Exception e){
            res.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("usuario",usuario);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        Map<String,Object>res=new HashMap<>();
        List<Usuario> usuarios=null;

        try{
            usuarios=usuarioService.findAll();


        }catch (Exception e){
            res.put("error","ocuerrio error al listar");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("usuarios",usuarios);
        return ResponseEntity.ok().body(res);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Usuario usuarioRequest){
        Map<String,Object> res=new HashMap<>();

        Usuario usuarioEncontrado=usuarioService.findById(id);
        Usuario usuario=null;
        try{

            usuarioEncontrado.setNombres(usuarioRequest.getNombres());
            usuarioEncontrado.setApellidoPaterno(usuarioRequest.getApellidoPaterno());
            usuarioEncontrado.setApellidoMaterno(usuarioRequest.getApellidoMaterno());
            usuarioEncontrado.setUsuario(usuarioRequest.getUsuario());
            usuarioEncontrado.setContrasena(usuarioRequest.getContrasena());
            usuarioEncontrado.setRole(usuarioRequest.getRole());



            usuario =usuarioService.save(usuarioEncontrado);


        }catch (Exception e){
            res.put("Mensaje","Error al actualizar usuario"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("usuario",usuario);
        res.put("Mensaje","El Usuario actualizado con exito");
        return  ResponseEntity.ok().body(res);


    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();
        Usuario usuario=null;
        try{
            usuario=usuarioService.findById(id);
        }catch (Exception e){
            res.put("Error","Ocurrio un error al obtener al obteer usuario "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("usuario",usuario);
        return ResponseEntity.ok().body(res);

    }



    @PostMapping("/form")
    public ResponseEntity<?> guardar(@RequestBody Usuario usuarioRequest){
        Map<String,Object> res=new HashMap<>();
       Usuario usuario=null;
        try {
            //GUARDAR ROLE ANTES PARA NO DEVUELVA NULL


            usuario=usuarioService.save(usuarioRequest);

        }catch (Exception e){

            res.put("Error",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("mensaje","guarado con exito");
        res.put("usuario",usuario);

        return ResponseEntity.ok().body(res);

    }



    @GetMapping("/listar/{page}")
    public ResponseEntity<?> listar(@PathVariable Integer page){
        Pageable pageable= PageRequest.of(page,5, Sort.by(Sort.Order.desc("id")));
        Map<String,Object> res=new HashMap<>();
        Page<Usuario> usuarios=null;

        try{


            usuarios=usuarioService.findAll(pageable);

        } catch (Exception e){
            res.put("Mensaje","Error en listas usuarios"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("usuarios",usuarios);
        return ResponseEntity.ok().body(res);

    }

}
