package com.example.demo.service;

import com.example.demo.util.EmailValidate;
import com.example.demo.exception.InValidEmailException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.security.ProvideJwt;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


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

}