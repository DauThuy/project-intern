package com.example.demo.controller;

import com.example.demo.common.Response;
import com.example.demo.dto.AccountDto;
import com.example.demo.dto.InfoDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/v1/login")
    public InfoDto getInfo(@Valid @RequestBody AccountDto dto) {
            return accountService.login(dto);
    }

//    @PostMapping(value = "/login/create")
//    public ResponseEntity<Response> createDto(@Valid @RequestBody UserDto dto) {
//        return accountService.createUser(dto);
//    }
}
