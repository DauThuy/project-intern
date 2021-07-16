package com.example.demo.service;

import com.example.demo.model.request.CreateUserReq;
import com.example.demo.model.request.UpdateUserByAdminReq;
import com.example.demo.model.request.UpdateUserReq;
import com.example.demo.util.EmailValidate;
import com.example.demo.exception.InValidEmailException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.security.ProvideJwt;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProvideJwt jwtProvider;

    @Override
    public InfoDto login(AccountDto dto) {
        if (!EmailValidate.validateEmail(dto.getEmail())) {
            throw new InValidEmailException();
        }
        Account user = accountRepository.findByEmailAddress(dto.getEmail());
        if (user == null || !(dto.getPassword().equals(user.getAccountPassword()))) {
            throw new UnauthorizedException();
        }

        String token = jwtProvider.generateTokenForEmployee(user);
        InfoDto info = new InfoDto(
                user.getAccountId(),
                user.getAccountName(),
                user.getEmailAddress(),
                user.getRoleId(),
                token
        );
        return info;
    }

    @Override
    public List<Account> getAllUser() {
        return accountRepository.findAllBy();
    }

    @Override
    public Account getUserById(int id) {
        return accountRepository.findByAccountId(id);
    }

    @Override
    public String deleteUserById(int id) {
        accountRepository.deleteAccountByAccountId(id);
        return "account removed" + id;
    }

    @Override
    public Account createUser(CreateUserReq req) {
        Date date = new Date();
        Account user = accountRepository.findByEmailAddress(req.getEmailAddress());
        if (user != null) {
            throw new DuplicateKeyException("Email is already");
        }
        Account newUser = new Account(req.getAccountId(),req.getAccountName(),req.getAccountPassword(),
                                    req.getEmailAddress(),"ava.png",
                                    req.getAccountStatus(),date,
                                    date,date, req.getRoleId());
        System.out.println("create:"+ req.getAccountId());
        accountRepository.save(newUser);
        return newUser;
    }

    @Override
    public Account updateUser(UpdateUserReq req, int id) {
        Account user=accountRepository.findByAccountId(id);
        Account userUpdate = new Account(user.getAccountId(),req.getAccountName(),user.getAccountPassword(),
                                    req.getEmailAddress(),user.getAccountImage(),user.getAccountStatus(),
                                    user.getApprovalDate(),user.getDateCreated(),user.getDateModified(),user.getRoleId());
        accountRepository.save(userUpdate);
        return userUpdate;
    }

    @Override
    public Account updateUserByAdmin(UpdateUserByAdminReq req, int id) {
        Account user = accountRepository.findByAccountId(id);
        Account userUpdatedByAdmin = new Account(req.getAccountId(),req.getAccountName(),user.getAccountPassword(),
                                    req.getEmailAddress(),user.getAccountImage(),user.getAccountStatus(),user.getApprovalDate(),
                                    user.getDateCreated(),user.getDateModified(),req.getRoleId());
        accountRepository.save(userUpdatedByAdmin);
        return userUpdatedByAdmin;
    }
}