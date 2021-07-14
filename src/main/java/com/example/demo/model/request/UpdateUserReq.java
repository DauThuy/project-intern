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
    private String accountName;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String emailAddress;


}

