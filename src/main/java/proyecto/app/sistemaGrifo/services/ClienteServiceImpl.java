package proyecto.app.sistemaGrifo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Cliente;
import proyecto.app.sistemaGrifo.models.Usuario;
import proyecto.app.sistemaGrifo.repository.ClienteDao;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;
    @Override
    public Cliente findById(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    public void deleteById(Long id) {

        clienteDao.deleteById(id);

    }

    @Override
    public Cliente save(Cliente cliente) {

        String usuarioRequestName = cliente.getDniRuc();

        Optional<Cliente> usuarioExistenteDni = Optional.ofNullable(clienteDao.findByDniRuc(usuarioRequestName));

        if (cliente.getId() != null) {
            Optional<Cliente> usuarioIdExistente = clienteDao.findById(cliente.getId());

            if (usuarioIdExistente.isPresent()) {
                return clienteDao.save(cliente); // El usuario con el ID ya existe, se actualiza
            }
        }

        if (usuarioExistenteDni.isPresent()) {
            // El usuario ya existe en la base de datos
            throw new RuntimeException("Ya existe el usuario en la base de datos");
        } else {
            // El usuario no existe, puedes guardarlo
            // Realiza la validación del DNI aquí antes de guardar
            return clienteDao.save(cliente);
        }


    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }

    @Override
    public Cliente findByDniRuc(String dni) {
        return clienteDao.findByDniRuc(dni);
    }
}
