package proyecto.app.sistemaGrifo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Categoria;
import proyecto.app.sistemaGrifo.models.Factura;
import proyecto.app.sistemaGrifo.models.Producto;
import proyecto.app.sistemaGrifo.models.Role;
import proyecto.app.sistemaGrifo.repository.CategoriaDao;
import proyecto.app.sistemaGrifo.repository.DetallefacturaDao;
import proyecto.app.sistemaGrifo.repository.FacturaDao;
import proyecto.app.sistemaGrifo.repository.ProductoDao;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private CategoriaDao categoriaDao;



    @Override
    public Producto findById(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    public void deleteById(Long id) {

        productoDao.deleteById(id);

    }

    @Override
    public Producto save(Producto producto) {
        Categoria categoria = obtainCategoriaById(producto.getCategoria().getId());
        producto.setCategoria(categoria);
        return productoDao.save(producto);
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {

        return productoDao.findAll(pageable);
    }

    @Override
    public List<Producto> findByDescripcionContainingIgnoreCase(String descripcion) {
        return productoDao.findByDescripcionContainingIgnoreCase(descripcion);
    }

    private Categoria obtainCategoriaById(Long roleId) {
        Optional<Categoria> categoriaOptional = categoriaDao.findById(roleId);
        return categoriaOptional.orElseThrow(() -> new RuntimeException("No se encontró el Role con ID: " + roleId));
    }


}
