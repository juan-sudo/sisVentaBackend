package proyecto.app.sistemaGrifo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Role;
import proyecto.app.sistemaGrifo.models.Usuario;
import proyecto.app.sistemaGrifo.repository.RoleDao;
import proyecto.app.sistemaGrifo.repository.UsuarioDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsuarioServiceImpl  implements UsuarioService{

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private RoleDao roleDao;
    @Override
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        usuarioDao.deleteById(id);

    }

    @Override
    public Usuario save(Usuario usuario) {
        String dni = usuario.getDni();
        Role role = obtainRoleById(usuario.getRole().getId());

        // Verificar si el usuario ya existe por ID
        if (usuario.getId() != null) {
            return updateExistingUser(usuario);
        }

        // Verificar si el usuario ya existe por DNI
        if (isDniAlreadyRegistered(dni)) {
            throw new RuntimeException("El usuario ya está registrado en la base de datos");
        } else {
            usuario.setRole(role);
            return usuarioDao.save(usuario);
        }
    }

    private Role obtainRoleById(Long roleId) {
        Optional<Role> roleOptional = roleDao.findById(roleId);
        return roleOptional.orElseThrow(() -> new RuntimeException("No se encontró el Role con ID: " + roleId));
    }

    private boolean isDniAlreadyRegistered(String dni) {
        return usuarioDao.findByDni(dni) != null;
    }

    private Usuario updateExistingUser(Usuario usuario) {
        Optional<Usuario> usuarioExiste = usuarioDao.findById(usuario.getId());
        if (usuarioExiste.isPresent()) {
            return usuarioDao.save(usuario);
        } else {
            throw new RuntimeException("No se encontró el usuario con ID: " + usuario.getId());
        }
    }


//    @Override
//    public Usuario save(Usuario usuario) {
//        String dni=usuario.getDni();
//
//        Optional<Role> roleOptional=roleDao.findById(usuario.getRole().getId());
//        Optional<Usuario> usuarioExisteDni=Optional.ofNullable(usuarioDao.findByDni(dni));
//
//        if(roleOptional.isPresent()){
//            Role role=roleOptional.get();
//            usuario.setRole(role);
//
//        }
//
//
//        //ACTUALIZAR USUARIO
//        if(usuario.getId()!=null){
//            Optional<Usuario> usuarioExiste=usuarioDao.findById(usuario.getId());
//            if(usuarioExiste.isPresent()){
//                return usuarioDao.save(usuario);
//            }
//
//        }
//        if(usuarioExisteDni.isPresent()){
//            throw new RuntimeException("El usuario ya esiste registrado en la base de datos");
//        }
//        else{
//            return usuarioDao.save(usuario);
//        }
//
//
//
//
//
//
//
//
//    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioDao.findAll(pageable);
    }

    @Override
    public Usuario findByUsuario(String usuario) {
        return usuarioDao.findByUsuario(usuario);
    }

    @Override
    public Usuario findByDni(String dni) {
        Optional<Usuario> usuario=Optional.ofNullable(usuarioDao.findByDni(dni));
        if(usuario.isPresent()){
            return usuarioDao.findByDni(dni);

        }else{
            throw new RuntimeException("En Usuario no existe en la base de datos");

        }


    }


}
