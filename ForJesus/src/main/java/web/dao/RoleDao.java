package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRole(String role);
    List<Role> list_roles();
}
