package proyecto.app.sistemaGrifo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Cliente;

import java.util.List;


public interface ClienteService {

    public Cliente findById(Long id);

    public List<Cliente> findAll();

    public void deleteById(Long id);

    public Cliente save(Cliente cliente);

    public Page<Cliente> findAll(Pageable pageable);

    public Cliente findByDniRuc(String dni);

}
