package proyecto.app.sistemaGrifo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import proyecto.app.sistemaGrifo.models.Cliente;
import proyecto.app.sistemaGrifo.models.DetalleFactura;
import proyecto.app.sistemaGrifo.models.Usuario;

public interface DetallefacturaDao extends CrudRepository<DetalleFactura,Long> {

    public Page<DetalleFactura> findAll(Pageable pageable);
}
