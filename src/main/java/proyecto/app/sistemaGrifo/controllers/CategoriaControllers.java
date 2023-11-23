package proyecto.app.sistemaGrifo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.app.sistemaGrifo.models.Categoria;
import proyecto.app.sistemaGrifo.services.CategoriaServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categoria")
public class CategoriaControllers {

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        Map<String,Object> res=new HashMap<>();
        List<Categoria> categorias=null;
        try {
            categorias=categoriaService.findAll();

        }catch (Exception e){
            res.put("error","Ocurrio un error l listar");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);

        }
        res.put("categorias",categorias);
        return  ResponseEntity.ok().body(res);
    }
}
