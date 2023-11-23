package proyecto.app.sistemaGrifo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.app.sistemaGrifo.models.Producto;
import proyecto.app.sistemaGrifo.models.TipoFactura;
import proyecto.app.sistemaGrifo.services.TipoFacturaService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tipoFactura")
public class TipoFacturaControllers {

    @Autowired
    private TipoFacturaService tipoFacturaService;


    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();


        try{
            tipoFacturaService.deleteById(id);


        }catch (Exception e){
            res.put("Mensaje","Ocurrio un error al eliminar tipo de factura"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("Mensaje","Eliminado conexito");
        return ResponseEntity.ok().body(res);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody TipoFactura tipoFacturaoRequest){
        Map<String,Object> res=new HashMap<>();

        TipoFactura tipoFacturaEncotrado=tipoFacturaService.findById(id);
        TipoFactura tipoFactura=null;
        try{

            tipoFacturaEncotrado.setDocumento(tipoFacturaoRequest.getDocumento());
            tipoFacturaEncotrado.setNumero(tipoFacturaoRequest.getNumero());
            tipoFacturaEncotrado.setSerie(tipoFacturaoRequest.getSerie());


            tipoFactura =tipoFacturaService.save(tipoFacturaEncotrado);


        }catch (Exception e){
            res.put("Mensaje","Error al actualizar tipo de documento"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("tipoFactura",tipoFactura);
        res.put("Mensaje","Tipo de factura actualizado con exito");
        return  ResponseEntity.ok().body(res);


    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();
        TipoFactura tipoFactura=null;
        try{
            tipoFactura=tipoFacturaService.findById(id);
        }catch (Exception e){
            res.put("Error","Ocurrio un error al obtener tipo de factura"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("tipoFactura",tipoFactura);
        return ResponseEntity.ok().body(res);

    }



    @PostMapping("/form")
    public ResponseEntity<?> guardar(@RequestBody TipoFactura tipoFacturaRequest){
        Map<String,Object> res=new HashMap<>();
        TipoFactura tipoFacturaEncontrado=new TipoFactura();
        TipoFactura tipoFactura=null;
        try {

            tipoFacturaEncontrado.setDocumento(tipoFacturaRequest.getDocumento());
            tipoFacturaEncontrado.setNumero(tipoFacturaRequest.getNumero());
            tipoFacturaEncontrado.setSerie(tipoFacturaRequest.getSerie());


            tipoFactura =tipoFacturaService.save(tipoFacturaEncontrado);


        }catch (Exception e){
            res.put("Error","Error al guardar tipo de docuento"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        res.put("tipoFactura",tipoFactura);
        res.put("Mensaje","Tipo de docuemnto guardado con exito");
        return ResponseEntity.ok().body(res);

    }



    @GetMapping("/listar/{page}")
    public ResponseEntity<?> listar(@PathVariable Integer page){
        Pageable pageable= PageRequest.of(page,5, Sort.by(Sort.Order.desc("id")));
        Map<String,Object> res=new HashMap<>();
        Page<TipoFactura> tipoFacturas=null;

        try{


            tipoFacturas=tipoFacturaService.findAll(pageable);

        } catch (Exception e){
            res.put("Mensaje","Error en listas tipo de factura"+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);


        }
        res.put("tipoFactura",tipoFacturas);
        return ResponseEntity.ok().body(res);

    }

    @PutMapping("/incrementar/{id}")
    public ResponseEntity<?> incrementarNumero(@PathVariable Long id){
        Map<String,Object> res=new HashMap<>();

        // String serieAc;
        TipoFactura tipoDocumentoBuscado=null;

        try{
            tipoDocumentoBuscado=tipoFacturaService.findById(id);
            // Obtener el valor actual del campo 'numero' como cadena
            String numeroActual = tipoDocumentoBuscado.getNumero();

            // Convertir la cadena a un número entero
            int numeroEntero = Integer.parseInt(numeroActual);

            // Incrementar el número en uno
            numeroEntero++;

            // Formatear el número resultante como una cadena de 6 caracteres con ceros a la izquierda
            String nuevoNumero = String.format("%06d", numeroEntero);

            // Actualizar el campo 'numero' con el nuevo valor
            tipoDocumentoBuscado.setNumero(nuevoNumero);

            //tipoDocumentoBuscado.setNumero(tipoDocumento.getNumero());


            tipoFacturaService.save(tipoDocumentoBuscado);

        }catch (Exception e){
            res.put("Error","error al actualizar tipo de documento"+e.getMessage());
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("tipoDocumento",tipoDocumentoBuscado);
        return ResponseEntity.ok().body(res);
    }


}
