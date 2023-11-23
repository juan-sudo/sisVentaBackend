package proyecto.app.sistemaGrifo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.app.sistemaGrifo.models.DetalleFactura;
import proyecto.app.sistemaGrifo.models.Role;
import proyecto.app.sistemaGrifo.services.DetallefacturaServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/detalleFactura")

public class DetalleFacturaControllers {

    @Autowired
    private DetallefacturaServiceImpl detallefacturaService;


    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();


        try{
            detallefacturaService.deleteById(id);


        }catch (Exception e){
            res.put("Mensaje","Ocurrio un error al eliminar un detalle factura"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("Mensaje","El detalle factura ha sido iminado con exito");
        return ResponseEntity.ok().body(res);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody DetalleFactura detalleFacruraRequest){
        Map<String,Object> res=new HashMap<>();

        DetalleFactura detalleFacturaEncontrado=detallefacturaService.findById(id);
        DetalleFactura detalleFactura=null;
        try{

            detalleFacturaEncontrado.setCantidad(detalleFacruraRequest.getCantidad());
            detalleFacturaEncontrado.setImporte(detalleFacruraRequest.getImporte());
            detalleFacturaEncontrado.setFactura(detalleFacruraRequest.getFactura());
            detalleFacturaEncontrado.setProducto(detalleFacruraRequest.getProducto());

            detalleFactura =detallefacturaService.save(detalleFacturaEncontrado);


        }catch (Exception e){
            res.put("Mensaje","Error al actualizar Detalle factura"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("detalleFactura",detalleFactura);
        res.put("Mensaje","El detalle factura actualizado con exito");
        return  ResponseEntity.ok().body(res);


    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();
        DetalleFactura detalleFactura=null;
        try{
            detalleFactura=detallefacturaService.findById(id);
        }catch (Exception e){
            res.put("Error","Ocurrio un error al obtener al obteer detalle factura "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("detalleFactura",detalleFactura);
        return ResponseEntity.ok().body(res);

    }



    @PostMapping("/form")
    public ResponseEntity<?> guardar(@RequestBody DetalleFactura detalleFacturaRequest){
        Map<String,Object> res=new HashMap<>();
        DetalleFactura detalleFacturaNuevo=new DetalleFactura();
        DetalleFactura detalleFactura=null;
        try {

            detalleFacturaNuevo.setCantidad(detalleFacturaRequest.getCantidad());
            detalleFacturaNuevo.setImporte(detalleFacturaRequest.getImporte());
            detalleFacturaNuevo.setFactura(detalleFacturaRequest.getFactura());
            detalleFacturaNuevo.setProducto(detalleFacturaRequest.getProducto());

            detalleFactura =detallefacturaService.save(detalleFacturaNuevo);


        }catch (Exception e){
            res.put("Error","Error al guardar detalle factura"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("detalleFactura",detalleFactura);
        res.put("Mensaje","Detalle factura guardado con exito");
        return ResponseEntity.ok().body(res);

    }



    @GetMapping("/listar/{page}")
    public ResponseEntity<?> listar(@PathVariable Integer page){
        Pageable pageable= PageRequest.of(page,5, Sort.by(Sort.Order.desc("id")));
        Map<String,Object> res=new HashMap<>();
        Page<DetalleFactura> detalleFacturas=null;

        try{


            detalleFacturas=detallefacturaService.findAll(pageable);

        } catch (Exception e){
            res.put("Mensaje","Error en listar detalleFacturas"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("detalleFacturas",detalleFacturas);
        return ResponseEntity.ok().body(res);

    }


}
