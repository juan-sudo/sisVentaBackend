package proyecto.app.sistemaGrifo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import proyecto.app.sistemaGrifo.models.Categoria;
import proyecto.app.sistemaGrifo.models.Cliente;

public interface CategoriaDao extends CrudRepository<Categoria,Long> {

    public Page<Categoria> findAll(Pageable pageable);


}
