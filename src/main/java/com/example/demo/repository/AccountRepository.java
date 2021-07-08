package com.example.demo.repository;

import com.example.demo.dto.InfoDto;
import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
//    @Query(value = "SELECT * FROM accounts a WHERE a.email_address = :email AND a.account_password = :password", nativeQuery = true)
//    Account findAccount(@Param("email") String email,@Param("password") String password);
//
//    @Query(value = "SELECT a.account_name FROM accounts a", nativeQuery = true)
//    String findAllAccount();

    Account findByEmailAddress(String email);
}
