package proyecto.app.sistemaGrifo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.app.sistemaGrifo.models.Role;
import proyecto.app.sistemaGrifo.repository.RoleDao;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) roleDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        roleDao.deleteById(id);

    }

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleDao.findAll(pageable);
    }
}
