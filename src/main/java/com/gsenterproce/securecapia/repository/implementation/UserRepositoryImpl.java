package com.gsenterproce.securecapia.repository.implementation;

import com.gsenterproce.securecapia.domain.Role;
import com.gsenterproce.securecapia.domain.User;
import com.gsenterproce.securecapia.exception.APIException;
import com.gsenterproce.securecapia.repository.RoleRepository;
import com.gsenterproce.securecapia.repository.UserRepository;
import com.gsenterproce.securecapia.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.gsenterproce.securecapia.enumeration.RoleType.ROLE_USER;
import static com.gsenterproce.securecapia.enumeration.VerificationType.ACCOUNT;
import static com.gsenterproce.securecapia.query.UserQuery.*;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;

@RequiredArgsConstructor
@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {

    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role> roleRepository;
    private final PasswordEncoder encoder;


    @Override
    public User create(User user) {

        // check the email is unique?
        if (getEmailCount(user.getEmail().trim().toLowerCase()) > 0)
            throw new APIException("Email already in use. Please use a different email and try again.");
        // Save the new user

        try {

            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource parameterSource = getParameterSource(user);

            jdbc.update(INSERT_USER_QUERY, parameterSource, keyHolder);
            user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

            // Add role to the user
            roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());

            // send verification URL
            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
            // Save URL in verification table
            jdbc.update(INSERT_ACCOUNTVERIFICATION_URL_QUERY, of("userId", user.getId(), "url", verificationUrl,"date",now().toString()));

            // Send email to user with the verification URL

//            emailService.sendverificationUrl(user.getFirstName(),user.getEmail(),verificationUrl,ACCOUNT);
            user.setEnabled(false);
            user.setNotLocked(true);

            // Return the newly created user

            return user;
            // If any errors, throw exception with proper message

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Collection<User> list(int page, int pageSize) {
        return List.of();
    }

    @Override
    public User get(long id) {
        return jdbc.queryForObject(GET_USER_BYID_QUERY,of("userId",id), new UserRowMapper());

    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(long id) {
        return null;
    }


    private int getEmailCount(String email) {
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, of("email", email), Integer.class);
    }

    private SqlParameterSource getParameterSource(User user) {

        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()));

    }

    private String getVerificationUrl(String key, String type) {

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify" + type + "/" + key).toString();
    }
}
