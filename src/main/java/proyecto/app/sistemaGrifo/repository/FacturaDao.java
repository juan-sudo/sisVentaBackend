package proyecto.app.sistemaGrifo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import proyecto.app.sistemaGrifo.models.Cliente;
import proyecto.app.sistemaGrifo.models.Factura;

public interface FacturaDao extends CrudRepository<Factura,Long> {
    public Page<Factura> findAll(Pageable pageable);

    public Factura findByNroDocumento(String nro);
}
