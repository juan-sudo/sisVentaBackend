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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/role")
public class RoleControllers {
    @Autowired
    private RoleServiceImpl roleService;

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();


        try{
            roleService.deleteById(id);


        }catch (Exception e){
            res.put("Mensaje","Ocurrio un error al eliminar un role"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("Mensaje","El rol se ha sido eliminado con exito");
        return ResponseEntity.ok().body(res);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Role roleRequest){
        Map<String,Object> res=new HashMap<>();

        Role roleEncontrado=roleService.findById(id);
        Role role=null;
        try{

            roleEncontrado.setNombreRole(roleRequest.getNombreRole());

            role =roleService.save(roleEncontrado);


        }catch (Exception e){
            res.put("Mensaje","Error al actualizar role"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("role",role);
        res.put("Mensaje","El role actualizado con exito");
        return  ResponseEntity.ok().body(res);


    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();
        Role role=null;
        try{
            role=roleService.findById(id);
        }catch (Exception e){
            res.put("Error","Ocurrio un error al obtener al obteer role "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("role",role);
        return ResponseEntity.ok().body(res);

    }



    @PostMapping("/form")
    public ResponseEntity<?> guardar(@RequestBody Role roleRequest){
        Map<String,Object> res=new HashMap<>();
        Role roleNuevo=new Role();
        Role role=null;
        try {

            roleNuevo.setNombreRole(roleRequest.getNombreRole());

            role =roleService.save(roleNuevo);


        }catch (Exception e){
            res.put("Error","Error al guardar role"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("role",role);
        res.put("Mensaje","Role guardado con exito");
        return ResponseEntity.ok().body(res);

    }



    @GetMapping("/listar/{page}")
    public ResponseEntity<?> listar(@PathVariable Integer page){
        Pageable pageable= PageRequest.of(page,5, Sort.by(Sort.Order.desc("id")));
        Map<String,Object> res=new HashMap<>();
        Page<Role> roles=null;

        try{


            roles=roleService.findAll(pageable);

        } catch (Exception e){
            res.put("Mensaje","Error en listas roles"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("roles",roles);
        return ResponseEntity.ok().body(res);

    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarSinpage(){

        Map<String,Object> res=new HashMap<>();
        List<Role> roles=null;

        try{


            roles=roleService.findAll();

        } catch (Exception e){
            res.put("Mensaje","Error en listas roles"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("roles",roles);
        return ResponseEntity.ok().body(res);

    }

}
