package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer accountId;

    private String accountName;

    private String accountPassword;

    private String emailAddress;

    private String accountImage;

    private Integer accountStatus;

    private Date approvalDate;

    private Date dateCreated;

    private Date dateModified;

    private Integer roleId;
}
