package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name="account_name")
    private String accountName;

    @Column(name="account_password")
    private String accountPassword;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="account_image")
    private String accountImage;

    @Column(name="account_status")
    private Integer accountStatus;

    @Column(name="approval_date")
    private Date approvalDate;

    @Column(name="date_created")
    private Date dateCreated;

    @Column(name="date_modified")
    private Date dateModified;

//    @ManyToOne
//    @JoinColumn(name = "roleId",referencedColumnName="role_id")
//    private Role roleId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role_id;

    @OneToMany(mappedBy="accountId",fetch = FetchType.LAZY)
    private List<Campaign> campaigns;

}