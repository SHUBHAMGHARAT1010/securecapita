package com.gsenterproce.securecapia.query;

public class UserQuery {

    public static final String INSERT_USER_QUERY = "INSERT INTO USERS (FIRST_NAME,LAST_NAME,EMAIL,PASSWORD) VALUES (:firstName, :lastName, :email, :password)";
    public static final String COUNT_USER_EMAIL_QUERY = "SELECT COUNT(*) FROM USERS WHERE EMAIL = :email";
    public static final String INSERT_ACCOUNTVERIFICATION_URL_QUERY = "INSERT INTO accountverification (USER_ID,URL,DATE) VALUES (:userId, :url,:date)";
    public static final String GET_USER_BYID_QUERY= "SELECT * FROM USERS WHERE ID =:userId";

}
