package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/login")
    public InfoDto getInfo(@Valid @RequestBody AccountDto dto) {
            return accountService.login(dto);
    }

    @GetMapping(value="/users")
    public ResponseEntity<?> getListUsers() {
        List<Account> users = accountService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable int id) {
        System.out.println(id);
        Account user =  accountService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser() {
        return null;
    }
}
