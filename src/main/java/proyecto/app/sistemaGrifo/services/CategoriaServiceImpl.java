package proyecto.app.sistemaGrifo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Categoria;
import proyecto.app.sistemaGrifo.repository.CategoriaDao;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaDao categoriaDao;
    @Override
    public Categoria findById(Long id) {
        return categoriaDao.findById(id).orElse(null);
    }

    @Override
    public List<Categoria> findAll() {
        return (List<Categoria>) categoriaDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoriaDao.deleteById(id);

    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaDao.save(categoria);
    }

    @Override
    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaDao.findAll(pageable);
    }




}
