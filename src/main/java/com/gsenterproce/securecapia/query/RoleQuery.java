package com.gsenterproce.securecapia.query;

public class RoleQuery {
    public static final String SELECT_ROLE_BY_NAME_QUERY = "SELECT * FROM ROLES WHERE NAME= :roleName";
    public static final String INSERT_ROLE_TO_USER_QUERY = "INSERT INTO userroles (USER_ID,ROLE_ID) VALUES (:userId, :roleId)";
}
