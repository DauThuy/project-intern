package com.example.demo.service;

import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.entity.Account;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.model.request.UpdateUserByAdminReq;
import com.example.demo.model.request.UpdateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    InfoDto login(AccountDto dto);
    List<Account> getAllUser();
    Account getUserById(int id);
    String deleteUserById(int id);
    Account createUser(CreateUserReq req);
    Account updateUser(UpdateUserReq req, int id);
    Account updateUserByAdmin(UpdateUserByAdminReq req, int id);
}