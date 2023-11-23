package proyecto.app.sistemaGrifo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import proyecto.app.sistemaGrifo.models.Cliente;
import proyecto.app.sistemaGrifo.models.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario,Long> {

    public Page<Usuario> findAll(Pageable pageable);

    //public Page<Usuario> findAll(Pageable pageable);

    public Usuario findByUsuario(String usuario);

    public Usuario findByDni(String dni);
}
