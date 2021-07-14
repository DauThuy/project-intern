package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByEmailAddress(String email);
    List<Account> findAllBy();
    Account findByAccountId(int id);
//
//    @Modifying
//    @Query(value = "DELETE FROM accounts WHERE account_id=:id",nativeQuery = true)
//    @Transactional
    void deleteAccountByAccountId(@Param("id") int id);

//    @Modifying
//    @Query(value = "DELETE FROM accounts WHERE account_id=:id",nativeQuery = true)
//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE a.account_name, a.email_address FROM accounts a WHERE account_id=:id",nativeQuery = true)
//    @Transactional
    Account updateById(@Param("id") int id);
}
