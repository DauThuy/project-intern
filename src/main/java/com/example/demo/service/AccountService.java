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


@Service
public class AccountService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProvideJwt jwtProvider;

    public InfoDto login(AccountDto dto) {
        if(!EmailValidate.validateEmail(dto.getEmail())) {
            throw new InValidEmailException();
        }
        Account user = accountRepository.findByEmailAddress(dto.getEmail());
        if (user == null ||  !encoder.matches(dto.getPassword(), user.getAccountPassword())) {
            throw new UnauthorizedException();
        }

        String token =jwtProvider.generateTokenForEmployee(user);
        InfoDto info = new InfoDto(
                user.getAccountName(),
                user.getEmailAddress(),
                user.getRole_id().getRoleId(),
                user.getRole_id().getRoleName(),
                token
        );
        return info;
    }
}