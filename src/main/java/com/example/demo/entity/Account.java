package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
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

    @Column(name="email_address", unique=true)
    private String emailAddress;

    @Column(name="account_image", columnDefinition = "varchar(255) default 'ava.png'")
    private String accountImage;

    @Column(name="account_status", columnDefinition = "integer default 0")
    private Integer accountStatus;

    @Column(name="approval_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvalDate;

    @Column(name="date_created")
//    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="date_modified")
//    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date dateModified;

    @Column(name="role_id")
    private Integer roleId;
}