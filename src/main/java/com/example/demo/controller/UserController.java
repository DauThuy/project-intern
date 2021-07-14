package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.model.request.UpdateUserReq;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    public InfoDto getInfo(@Valid @RequestBody AccountDto dto) {
            return accountService.login(dto);
    }

    @GetMapping(value="/admin/users")
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

    @PostMapping("/create")
    public ResponseEntity<?> createUser() {
        return null;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserReq req, @PathVariable int id) {
        Account account = accountService.updateUser(req, id);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        accountService.deleteUserById(id);
        System.out.println("Deleted");
        return ResponseEntity.ok("Deleted user");
    }
}
