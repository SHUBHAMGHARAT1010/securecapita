package com.gsenterproce.securecapia.resource;

import com.gsenterproce.securecapia.domain.HttpResponse;
import com.gsenterproce.securecapia.domain.User;
import com.gsenterproce.securecapia.dto.UserDTO;
import com.gsenterproce.securecapia.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<HttpResponse> saveUser(@RequestBody @Valid User user) {

        UserDTO userDTO = userService.createUser(user);

        return ResponseEntity.created(getUri()).body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userDTO))
                        .message("User created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getUserById(@PathVariable @Min(value = 1, message = "Id must be greater than zero (0).") Long id) {

        UserDTO userDTO = userService.getUserById(id);

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userDTO))
                        .message("User Found successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    private URI getUri() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    }


}
