package com.kumbaya.backendapi.model.request;

import lombok.Data;

import java.time.Instant;

@Data
public class UserRequest {
    private Integer id;
    private String firstname;
    private String lastname;
    private Instant dob;
    private String gender;
    private String email;
    private String password;
}
