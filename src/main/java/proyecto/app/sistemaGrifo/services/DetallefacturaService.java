package proyecto.app.sistemaGrifo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Cliente;
import proyecto.app.sistemaGrifo.models.DetalleFactura;
import proyecto.app.sistemaGrifo.models.Usuario;

import java.util.List;

public interface DetallefacturaService {
    public DetalleFactura findById(Long id);

    public List<DetalleFactura> findAll();

    public void deleteById(Long id);

    public DetalleFactura save(DetalleFactura detalleFactura);

    public Page<DetalleFactura> findAll(Pageable pageable);
}
