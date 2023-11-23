package proyecto.app.sistemaGrifo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.TipoFactura;
import proyecto.app.sistemaGrifo.repository.TipoFacturaDao;

import java.util.List;

@Service
public class TipoFacturaImpl implements TipoFacturaService {

    @Autowired
    private TipoFacturaDao tipoFacturaDao;

    @Override
    public TipoFactura findById(Long id) {
        return tipoFacturaDao.findById(id).orElse(null);
    }

    @Override
    public List<TipoFactura> findAll() {
        return (List<TipoFactura>) tipoFacturaDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        tipoFacturaDao.deleteById(id);

    }

    @Override
    public TipoFactura save(TipoFactura tipoFactura) {


        return tipoFacturaDao.save(tipoFactura);
    }

    @Override
    public Page<TipoFactura> findAll(Pageable pageable) {
        return tipoFacturaDao.findAll(pageable);
    }
}
