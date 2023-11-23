package proyecto.app.sistemaGrifo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import proyecto.app.sistemaGrifo.models.Categoria;
import proyecto.app.sistemaGrifo.models.Usuario;

import java.util.List;

public interface CategoriaService {

    public Categoria findById(Long id);

    public List<Categoria> findAll();

    public void deleteById(Long id);

    public Categoria save(Categoria categoria);

    public Page<Categoria> findAll(Pageable pageable);


}
