package com.gsenterproce.securecapia.repository;

import com.gsenterproce.securecapia.domain.User;

import java.util.Collection;

public interface UserRepository<T extends User> {

    /*Basic CRUD operation.*/

    T create(T data);

    Collection<T> list(int page,int pageSize);

    T get(long id);
    T update (T data);
    Boolean delete(long id);

    /*More Complex Operations*/

}
