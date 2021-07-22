package com.example.demo.controller;

import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.ParamChangePassword;
import com.example.demo.model.request.ParamCreateUser;
import com.example.demo.model.request.ParamAdminUpdateUser;
import com.example.demo.model.request.ParamUserUpdateUser;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")

public class UserController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/login")
    public InfoDto getInfo(@Valid @RequestBody AccountDto dto) {
            return accountService.login(dto);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value="/users")
    public ResponseEntity<?> getListUsers() {
        List<UserDto> users = accountService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value="users/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable int id) {
        System.out.println(id);
        UserDto user =  accountService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody ParamCreateUser req) {
        UserDto account = accountService.createUser(req);
        return ResponseEntity.ok(account);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUserByUser(@Valid @RequestBody ParamUserUpdateUser req, @PathVariable int id) {
        UserDto account = accountService.updateUserByUser(req, id);
        return ResponseEntity.ok(account);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserByAdmin(@Valid @RequestBody ParamAdminUpdateUser req, @PathVariable int id) {
        UserDto account = accountService.updateUserByAdmin(req, id);
        return ResponseEntity.ok(account);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("users/change-password/{id}")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ParamChangePassword req, @PathVariable int id) {
        accountService.changePassword(req, id);
        System.out.println("Password has changed");
        return ResponseEntity.ok("Changed password");
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        accountService.deleteUserById(id);
        System.out.println("Deleted");
        return ResponseEntity.ok("Deleted user");
    }
}
