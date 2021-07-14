package com.example.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserReq {
    @NotNull(message = "username is required")
    @NotEmpty(message = "username is required")
    @JsonProperty("user_name")
    private String accountName;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String emailAddress;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Pasword must be at least 6 characters")
    private String password;
}

