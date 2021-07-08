package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
//    @Query(value = "SELECT * FROM accounts a WHERE a.email_address = :email AND a.account_password = :password", nativeQuery = true)
//    Account findAccount(@Param("email") String email,@Param("password") String password);
//
//    @Query(value = "SELECT a.account_name FROM accounts a", nativeQuery = true)
//    String findAllAccount();

    Account findByEmailAddress(String email);
}
