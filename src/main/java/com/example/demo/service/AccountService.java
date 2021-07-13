package com.example.demo.service;

import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface AccountService {
    public InfoDto login(AccountDto dto);
    public List<Account> getAllUser();
    public Account getUserById(int id);
}