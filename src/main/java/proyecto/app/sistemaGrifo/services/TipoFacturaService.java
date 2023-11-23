package proyecto.app.sistemaGrifo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import proyecto.app.sistemaGrifo.models.Role;
import proyecto.app.sistemaGrifo.models.TipoFactura;

import java.util.List;

public interface TipoFacturaService {
    public TipoFactura findById(Long id);

    public List<TipoFactura> findAll();

    public void deleteById(Long id);

    public TipoFactura save(TipoFactura tipoFactura);

    public Page<TipoFactura> findAll(Pageable pageable);
}
