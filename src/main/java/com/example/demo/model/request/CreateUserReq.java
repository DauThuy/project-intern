package com.example.demo.model.request;

import lombok.*;
import javax.validation.constraints.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserReq {
//    @NotNull(message = "account id is required")
//    @NotEmpty(message = "account id is required")
//    private Integer accountId;

    @NotNull(message = "username is required")
    @NotEmpty(message = "username is required")
    private String accountName;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Pasword must be at least 6 characters")
    private String accountPassword;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String emailAddress;

//    private String accountImage;

    private Integer accountStatus;
//
//    private Date approvalDate;
//
//    private Date dateCreated;
//
//    private Date dateModified;

    private Integer roleId;
}

