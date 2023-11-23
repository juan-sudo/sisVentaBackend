package proyecto.app.sistemaGrifo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.DetalleFactura;
import proyecto.app.sistemaGrifo.models.Factura;
import proyecto.app.sistemaGrifo.models.Usuario;

import java.util.List;


public interface FacturaService {

    public Factura findById(Long id);

    public List<Factura> findAll();

    public void deleteById(Long id);

    public Factura save(Factura factura);

    public Page<Factura> findAll(Pageable pageable);
    public Factura findByNroDocumento(String nro);

}
