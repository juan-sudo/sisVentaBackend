package proyecto.app.sistemaGrifo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import proyecto.app.sistemaGrifo.models.Cliente;
import proyecto.app.sistemaGrifo.models.Producto;
import proyecto.app.sistemaGrifo.services.ClienteServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cliente")
public class ClienteControllers {
    @Autowired
    private ClienteServiceImpl clienteService;


    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();


        try{
            clienteService.deleteById(id);


        }catch (Exception e){
            res.put("Mensaje","Ocurrio un error al eliminar un cliente"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("Mensaje","El cliente eliminado con exito");
        return ResponseEntity.ok().body(res);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cliente clienteRequest){
        Map<String,Object> res=new HashMap<>();

        Cliente clienteEncotrado=clienteService.findById(id);
        Cliente cliente=null;
        try{

            clienteEncotrado.setDniRuc(clienteRequest.getDniRuc());
            clienteEncotrado.setNombreCliente(clienteRequest.getNombreCliente());
            clienteEncotrado.setDireccion(clienteRequest.getDireccion());

            cliente =clienteService.save(clienteEncotrado);


        }catch (Exception e){
            res.put("Mensaje","Error al actualizar cliente"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("cliente",cliente);
        res.put("Mensaje","Cliente actualizado con exito");
        return  ResponseEntity.ok().body(res);


    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();
        Cliente cliente=null;
        try{
            cliente=clienteService.findById(id);
        }catch (Exception e){
            res.put("Error","Ocurrio un error al obtener cliente"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("cliente",cliente);
        return ResponseEntity.ok().body(res);

    }
    
    @GetMapping("/buscarDni/{dni}")
    public ResponseEntity<?> buscar(@PathVariable String dni){
        Map<String,Object> res=new HashMap<>();
        Cliente cliente=null;

        try {
            cliente=clienteService.findByDniRuc(dni);


        }catch (Exception e){
            res.put("error","Ocurrio un error al buscar cliente");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("cliente",cliente);
        return ResponseEntity.ok().body(res);

    }
    




    @PostMapping("/form")
    public ResponseEntity<?> guardar(@RequestBody Cliente clienteRequest){
        Map<String,Object> res=new HashMap<>();

        Cliente cliente=null;
        try {

            cliente =clienteService.save(clienteRequest);


        }catch (Exception e){
            res.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("cliente",cliente);
        res.put("Mensaje","Cliente guardado con exito");
        return ResponseEntity.ok().body(res);

    }



    @GetMapping("/listar/{page}")
    public ResponseEntity<?> listar(@PathVariable Integer page){
        Pageable pageable= PageRequest.of(page,5, Sort.by(Sort.Order.desc("id")));
        Map<String,Object> res=new HashMap<>();
        Page<Cliente> clientes=null;

        try{


            clientes=clienteService.findAll(pageable);

        } catch (Exception e){
            res.put("Mensaje","Error al listar clientes"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("clientes",clientes);
        return ResponseEntity.ok().body(res);

    }
    @GetMapping("/listar")
    public ResponseEntity<?> listartodo(){
        Map<String, Object> res=new HashMap<>();
        List<Cliente> clientes=null;

        try{
            clientes=clienteService.findAll();

        }catch (Exception e){
            res.put("error","Ocurrio un error al listar");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("clientes",clientes);
        return ResponseEntity.ok().body(res);

    }

}
