package com.gsenterproce.securecapia.repository.implementation;

import com.gsenterproce.securecapia.domain.Role;
import com.gsenterproce.securecapia.exception.APIException;
import com.gsenterproce.securecapia.repository.RoleRepository;
import com.gsenterproce.securecapia.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.gsenterproce.securecapia.enumeration.RoleType.ROLE_USER;
import static com.gsenterproce.securecapia.query.RoleQuery.INSERT_ROLE_TO_USER_QUERY;
import static com.gsenterproce.securecapia.query.RoleQuery.SELECT_ROLE_BY_NAME_QUERY;
import static java.util.Objects.requireNonNull;

@Repository
@Slf4j
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository<Role> {


    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Role create(Role data) {
        return null;
    }

    @Override
    public Collection<Role> list(int page, int pageSize) {
        return List.of();
    }

    @Override
    public Role get(long id) {
        return null;
    }

    @Override
    public Role update(Role data) {
        return null;
    }

    @Override
    public Boolean delete(long id) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {

        log.info("Adding role {} to user id:{}",roleName,userId);

        try {

            Role role= jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("roleName" ,roleName), new RoleRowMapper());
            jdbc.update(INSERT_ROLE_TO_USER_QUERY,Map.of("userId",userId,"roleId", requireNonNull(role).getId()));


        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            throw new APIException("An error Occurred. Please try again.");
        }

    }

    @Override
    public Role getRoleByUserId(long userId) {
        return null;
    }

    @Override
    public Role getRoleByUserEmail(String email) {
        return null;
    }

    @Override
    public Boolean updateUserRole(Long userId, String roleName) {
        return null;
    }
}
