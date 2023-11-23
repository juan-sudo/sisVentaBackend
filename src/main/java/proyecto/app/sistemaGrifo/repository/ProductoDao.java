package proyecto.app.sistemaGrifo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import proyecto.app.sistemaGrifo.models.Cliente;
import proyecto.app.sistemaGrifo.models.Producto;

import java.util.List;

public interface ProductoDao  extends CrudRepository<Producto,Long> {

    public Page<Producto> findAll(Pageable pageable);

    List<Producto> findByDescripcionContainingIgnoreCase(String descripcion);

}
