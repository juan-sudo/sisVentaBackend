package proyecto.app.sistemaGrifo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Cliente;
import proyecto.app.sistemaGrifo.models.DetalleFactura;
import proyecto.app.sistemaGrifo.repository.ClienteDao;
import proyecto.app.sistemaGrifo.repository.DetallefacturaDao;

import java.util.List;

@Service
public class DetallefacturaServiceImpl implements DetallefacturaService {
    @Autowired
    private DetallefacturaDao detallefacturaDao;
    @Override
    public DetalleFactura findById(Long id) {
        return detallefacturaDao.findById(id).orElse(null);
    }

    @Override
    public List<DetalleFactura> findAll() {
        return (List<DetalleFactura>) detallefacturaDao.findAll();
    }

    @Override
    public void deleteById(Long id) {

        detallefacturaDao.deleteById(id);

    }

    @Override
    public DetalleFactura save(DetalleFactura detalleFactura) {
        return detallefacturaDao.save(detalleFactura);
    }

    @Override
    public Page<DetalleFactura> findAll(Pageable pageable) {
        return detallefacturaDao.findAll(pageable);
    }
}
