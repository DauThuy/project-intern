package com.example.demo.service;

import com.example.demo.model.mapper.AccountMapper;
import com.example.demo.model.request.CreateUserReq;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProvideJwt jwtProvider;

    @Override
    public InfoDto login(AccountDto dto) {
//        System.out.println("code password: " + encoder.encode("1234567"));
        if (!EmailValidate.validateEmail(dto.getEmail())) {
            throw new InValidEmailException();
        }
        Account user = accountRepository.findByEmailAddress(dto.getEmail());
//        if (user == null || !encoder.matches(dto.getPassword(), user.getAccountPassword())) {
//            throw new UnauthorizedException();
//        }
        if (user == null || !(dto.getPassword().equals(user.getAccountPassword()))) {
            throw new UnauthorizedException();
        }

        String token = jwtProvider.generateTokenForEmployee(user);
        InfoDto info = new InfoDto(
                user.getAccountId(),
                user.getAccountName(),
                user.getEmailAddress(),
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
        Account user = accountRepository.findByEmailAddress(req.getEmailAddress());
        if (user != null) {
            throw new DuplicateKeyException("Email is already");
        }
        user = AccountMapper.toUser(req);
        accountRepository.save(user);

        return user;
    }

    @Override
    public Account updateUser(UpdateUserReq req, int id) {
        Account user = accountRepository.findByEmailAddress(req.getEmailAddress());
        if (user.getEmailAddress() == "") {
            throw new DuplicateKeyException("No user found");
        }
        user = AccountMapper.toUser(req, id);
        accountRepository.save(user);

        return user;
    }

}