package proyecto.app.sistemaGrifo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import proyecto.app.sistemaGrifo.models.Cliente;
import proyecto.app.sistemaGrifo.models.Role;

import java.util.List;

public interface RoleDao  extends CrudRepository<Role,Long> {



    public Page<Role> findAll(Pageable pageable);
}
