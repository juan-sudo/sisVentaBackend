package proyecto.app.sistemaGrifo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Factura;
import proyecto.app.sistemaGrifo.models.Producto;

import java.util.List;


public interface ProductoService {

    public Producto findById(Long id);

    public List<Producto> findAll();

    public void deleteById(Long id);

    public Producto save(Producto producto);

    public Page<Producto> findAll(Pageable pageable);

    List<Producto> findByDescripcionContainingIgnoreCase(String descripcion);


}
