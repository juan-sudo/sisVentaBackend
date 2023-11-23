package proyecto.app.sistemaGrifo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import proyecto.app.sistemaGrifo.models.Role;
import proyecto.app.sistemaGrifo.models.TipoFactura;

public interface TipoFacturaDao extends CrudRepository<TipoFactura,Long> {
    public Page<TipoFactura> findAll(Pageable pageable);
}
