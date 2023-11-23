package proyecto.app.sistemaGrifo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Producto;
import proyecto.app.sistemaGrifo.models.Role;
import proyecto.app.sistemaGrifo.models.Usuario;

import java.util.List;


public interface RoleService {

    public Role findById(Long id);

    public List<Role> findAll();

    public void deleteById(Long id);

    public Role save(Role role);

    public Page<Role> findAll(Pageable pageable);
}
