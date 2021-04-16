package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;


    @Override
    @Transactional
    public Role getRole(String role) {
        return roleDao.getRole(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> list_roles() {
        return roleDao.list_roles();
    }
}
