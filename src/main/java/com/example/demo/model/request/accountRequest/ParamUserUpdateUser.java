package com.example.demo.model.request.accountRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamUserUpdateUser {
    @NotNull(message = "username is required")
    @NotEmpty(message = "username is required")
    private String userName;
}

