package proyecto.app.sistemaGrifo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import proyecto.app.sistemaGrifo.models.Cliente;

public interface ClienteDao extends CrudRepository<Cliente,Long> {

    public Page<Cliente> findAll(Pageable pageable);

    public Cliente findByDniRuc(String dni);
}
