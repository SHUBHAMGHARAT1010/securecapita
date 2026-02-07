package com.gsenterproce.securecapia.repository;

import com.gsenterproce.securecapia.domain.Role;
import com.gsenterproce.securecapia.domain.User;

import java.util.Collection;

public interface RoleRepository<T extends Role> {

    /*Basic CRUD operation.*/

    T create(T data);

    Collection<T> list(int page, int pageSize);

    T get(long id);
    T update (T data);
    Boolean delete(long id);

    /*More Complex Operations*/

    void addRoleToUser(Long userId,String roleName);

    Role getRoleByUserId(long userId);
    Role getRoleByUserEmail(String email);
    Boolean updateUserRole(Long userId, String roleName);

}
