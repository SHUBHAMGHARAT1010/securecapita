package com.gsenterproce.securecapia.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

// user dto
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;
    private boolean enabled;
    private boolean isNotLocked;
    private boolean isUsingMfa;
    private LocalDateTime createdAt;
}
