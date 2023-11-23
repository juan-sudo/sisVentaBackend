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
import proyecto.app.sistemaGrifo.models.DetalleFactura;
import proyecto.app.sistemaGrifo.models.Factura;
import proyecto.app.sistemaGrifo.services.FacturaServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/factura")
public class FacuturaControllers {

    private static Logger log= LoggerFactory.getLogger(FacuturaControllers.class);

    @Autowired
    private FacturaServiceImpl facturaService;


    @GetMapping("/buscar/{nro}")
    public ResponseEntity<?> buscar(@PathVariable String nro){
        Map<String,Object> res=new HashMap<>();
        log.info("numero en path: ",nro);
        Factura factura=null;
        try{
            factura=facturaService.findByNroDocumento(nro);
            log.info("id de factura: ",factura.getId());


        }catch (Exception e){
            res.put("error","Error al buscar factura"+e.getMessage());
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("factura",factura);
        return ResponseEntity.ok().body(res);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();


        try{
            facturaService.deleteById(id);


        }catch (Exception e){
            res.put("Mensaje","Ocurrio un error al eliminar una factura"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("Mensaje","La factura ha sido iminado con exito");
        return ResponseEntity.ok().body(res);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Factura facturaRequest){
        Map<String,Object> res=new HashMap<>();

        Factura facturaEncontrado=facturaService.findById(id);
        Factura factura=null;
        try{

            facturaEncontrado.setSubtotal(facturaRequest.getSubtotal());
            facturaEncontrado.setIgv(facturaRequest.getIgv());
            facturaEncontrado.setTotal(facturaRequest.getTotal());
            facturaEncontrado.setCliente(facturaRequest.getCliente());
            facturaEncontrado.setUsuario(facturaRequest.getUsuario());
            facturaEncontrado.setDetalleFactura(facturaRequest.getDetalleFactura());


            factura=facturaService.save(facturaEncontrado);

        }catch (Exception e){
            res.put("Mensaje","Error al actualizar  factura"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("factura",factura);
        res.put("Mensaje","El detalle factura actualizado con exito");
        return  ResponseEntity.ok().body(res);


    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();
        Factura factura=null;
        try{
            factura=facturaService.findById(id);
        }catch (Exception e){
            res.put("Error","Ocurrio un error al obtener al obteer factura "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("factura",factura);
        return ResponseEntity.ok().body(res);

    }

    @PostMapping("/form")
    public ResponseEntity<?> guardar(@RequestBody Factura facturaRequest) {
        Map<String, Object> res = new HashMap<>();

        try {

            Factura facturaGuardada = facturaService.save(facturaRequest);
            res.put("factura", facturaGuardada);
            res.put("Mensaje", "Factura guardada con éxito");

            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            res.put("Error", "Error al guardar factura: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }


//    @PostMapping("/form")
//    public ResponseEntity<?> guardar(@RequestBody Factura facturaRequest){
//        Map<String,Object> res=new HashMap<>();
//        Factura facturaNuevo=new Factura();
//        Factura factura=null;
//        facturaNuevo.setUsuario(facturaRequest.getUsuario());
//
//
//        System.out.println("IGV: " + facturaRequest.getIgv());
//        for(DetalleFactura detalleFactura:facturaRequest.getDetalleFactura()){
//            System.out.println("cantidad: " + detalleFactura.getCantidad());
//        }
//
//
//        try {
//
//            //Factura facturaNuevo=new Factura();
//
//            facturaNuevo.setSubtotal(factura.getSubtotal());
//            facturaNuevo.setIgv(factura.getIgv());
//            facturaNuevo.setTotal(factura.getTotal());
//            // log.info("total: ",factura.getTotal());
//
//            facturaNuevo.setNroDocumento(factura.getTipoFactura().getSerie().concat("-").concat(factura.getTipoFactura().getNumero()));
//
//
//            facturaNuevo.setCliente(factura.getCliente());
//
//            facturaNuevo.setUsuario(factura.getUsuario());
//            facturaNuevo.setDetalleFactura(factura.getDetalleFactura());
//
//            for(DetalleFactura facturDetalle:factura.getDetalleFactura()){
//                DetalleFactura detalleFactura=new DetalleFactura();
//
//                detalleFactura.setFactura(facturaNuevo);
//                detalleFactura.setImporte(facturDetalle.getImporte());
//                detalleFactura.setProducto(facturDetalle.getProducto());
//                detalleFactura.setCantidad(facturDetalle.getCantidad());
//
//                System.out.println("cantidad en omplemenete: " + detalleFactura.getCantidad());
//
//
//
//                facturaNuevo.getDetalleFactura().add(detalleFactura);
//                //System.out.println("cantidad en omplemenete: " + facturaNuevo.getDetalleFactura().size());
//
//
//            }
//
//            facturaNuevo.setTipoFactura(factura.getTipoFactura());
//
//
//            factura=facturaService.save(facturaNuevo);
//
//        }catch (Exception e){
//            res.put("Error","Error al guardar factura"+e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
//        }
//        res.put("factura",factura);
//        res.put("Mensaje","Factura guardado con exito");
//        return ResponseEntity.ok().body(res);
//
//    }



    @GetMapping("/listar/{page}")
    public ResponseEntity<?> listar(@PathVariable Integer page){
        Pageable pageable= PageRequest.of(page,5, Sort.by(Sort.Order.desc("id")));
        Map<String,Object> res=new HashMap<>();
        Page<Factura> facturas=null;

        try{


            facturas=facturaService.findAll(pageable);

        } catch (Exception e){
            res.put("Mensaje","Error en listar facturas"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("facturas",facturas);
        return ResponseEntity.ok().body(res);

    }


}
