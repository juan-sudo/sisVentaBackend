package proyecto.app.sistemaGrifo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Producto;
import proyecto.app.sistemaGrifo.models.Usuario;

import java.util.List;


public interface UsuarioService {

    public Usuario findById(Long id);

    public List<Usuario> findAll();

    public void deleteById(Long id);

    public Usuario save(Usuario usuario);

    public Page<Usuario> findAll(Pageable pageable);

    public Usuario findByUsuario(String usuario);

    public Usuario findByDni(String dni);
}
