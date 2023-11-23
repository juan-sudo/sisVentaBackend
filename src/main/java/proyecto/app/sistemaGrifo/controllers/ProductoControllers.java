package proyecto.app.sistemaGrifo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.app.sistemaGrifo.models.Producto;
import proyecto.app.sistemaGrifo.services.ProductoServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/producto")
public class ProductoControllers {

    private static Logger log=LoggerFactory.getLogger(ProductoControllers.class);

    @Autowired
    private ProductoServiceImpl productoService;


    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?>filtrar(@PathVariable String term){
        Map<String,Object>res=new HashMap<>();
        List<Producto> productos=null;
        List<Producto> productosMay=null;

        try {
            productos=productoService.findByDescripcionContainingIgnoreCase(term);

            productosMay=productos.stream()
                    .map(producto -> {
                        return new Producto(producto.getId(), producto.getDescripcion().toUpperCase(), producto.getPrecio(), producto.getUnidad(),
                                producto.getCategoria());

                    }).collect(Collectors.toList());


        }catch (Exception e){
            res.put("error"," no se ecnotro el producto");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("productos",productosMay);
        return ResponseEntity.ok().body(res);

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();


        try{
            productoService.deleteById(id);


        }catch (Exception e){
            res.put("Mensaje","Ocurrio un error al eliminar un producto"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("mensaje","El producto ha sido eliminado con exito");
        return ResponseEntity.ok().body(res);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Producto productoRequest){
        Map<String,Object> res=new HashMap<>();

        Producto productoEncotrado=productoService.findById(id);
        Producto producto=null;
        try{

            productoEncotrado.setDescripcion(productoRequest.getDescripcion());
            productoEncotrado.setUnidad(productoRequest.getUnidad());
            productoEncotrado.setPrecio(productoRequest.getPrecio());

            producto =productoService.save(productoEncotrado);


        }catch (Exception e){
            res.put("Mensaje","Error al actualizar producto"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("producto",producto);
        res.put("Mensaje","Producto actualizado con exito");
        return  ResponseEntity.ok().body(res);


    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();
        Producto producto=null;
        try{
            producto=productoService.findById(id);
        }catch (Exception e){
            res.put("Error","Ocurrio un error al obtener"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("producto",producto);
        return ResponseEntity.ok().body(res);

    }



    @PostMapping("/form")
    public ResponseEntity<?> guardar(@RequestBody Producto productoRequest){
        Map<String,Object> res=new HashMap<>();
       // Producto productoNuevo=new Producto();

        Producto producto=null;
        try {

//            productoNuevo.setDescripcion(productoRequest.getDescripcion());
//            productoNuevo.setPrecio(productoRequest.getPrecio());
//            productoNuevo.setUnidad(productoRequest.getUnidad());


            producto= productoService.save(productoRequest);


        }catch (Exception e){
            res.put("Error","Error al guardar producto"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("producto",producto);
        res.put("Mensaje","Prducto guardado con exito");
        return ResponseEntity.ok().body(res);

    }



    @GetMapping("/listar/{page}")
    public ResponseEntity<?> listar(@PathVariable Integer page){
        Pageable pageable= PageRequest.of(page,5, Sort.by(Sort.Order.desc("id")));
        Map<String,Object> res=new HashMap<>();
        Page<Producto> productos=null;

        try{


            productos=productoService.findAll(pageable);

        } catch (Exception e){
            res.put("Mensaje","Error en listas producto"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("productos",productos);
        return ResponseEntity.ok().body(res);

    }
}
