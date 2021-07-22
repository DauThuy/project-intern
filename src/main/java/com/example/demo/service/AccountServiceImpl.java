package com.example.demo.service;

import com.example.demo.exception.InValidPasswordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.mapper.AccountMapper;
import com.example.demo.model.request.ParamChangePassword;
import com.example.demo.model.request.ParamCreateUser;
import com.example.demo.model.request.ParamAdminUpdateUser;
import com.example.demo.model.request.ParamUserUpdateUser;
import com.example.demo.util.EmailValidate;
import com.example.demo.exception.InValidEmailException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.security.ProvideJwt;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProvideJwt jwtProvider;

    Date now = new Date();

    @Override
    public InfoDto login(AccountDto dto) {
        if(!EmailValidate.validateEmail(dto.getEmail())) {
            throw new InValidEmailException();
        }
        Account user = accountRepository.findByEmailAddress(dto.getEmail());
        if (user == null || !encoder.matches(dto.getPassword(), user.getAccountPassword())) {
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
    public List<UserDto> getAllUser() {
        List<UserDto> userDtos = new ArrayList<>();
        List<Account> accounts = accountRepository.findAllBy();

        for (Account account: accounts) {
            if (!account.getIsDelete()) {
                userDtos.add(AccountMapper.toUserDto(account));
            }
        }
        Collections.sort(userDtos, new UserUtils());

        return userDtos;
    }

    @Override
    public UserDto getUserById(int id) {
        Account account = accountRepository.findByAccountId(id);
        if (!accountRepository.existsById(id) || account.getIsDelete()) {
            throw new NotFoundException("Not found user");
        }
        return AccountMapper.toUserDto(account);
    }

    @Override
    public String deleteUserById(int id) {
        Account account = accountRepository.findByAccountId(id);
        account.setIsDelete(true);
        accountRepository.save(account);

        return "account removed" + id + ": " + account.getAccountName();
    }

    @Override
    public UserDto createUser(ParamCreateUser req) {
        List<Account> accounts = accountRepository.findAllBy();
        for (Account account: accounts) {
            if (account.getEmailAddress().equals(req.getEmailAddress())) {
                throw new DuplicateKeyException("Email already exists");
            }
        }
        Account account = AccountMapper.toCreateAccount(req);
        account.setAccountId(accounts.size() + 1);
        accountRepository.save(account);

        return AccountMapper.toUserDto(account);
    }

    @Override
    public UserDto updateUserByUser(ParamUserUpdateUser req, int id) {
        Account account = accountRepository.findByAccountId(id);
        if (!accountRepository.existsById(id) || account.getIsDelete()) {
            throw new NotFoundException("Not found user");
        }
        account.setAccountName(req.getUserName());
        account.setDateModified(now);
        accountRepository.save(account);

        return AccountMapper.toUserDto(account);
    }

    @Override
    public UserDto updateUserByAdmin(ParamAdminUpdateUser req, int id) {
        Account account = accountRepository.findByAccountId(id);
        if (!accountRepository.existsById(id) || account.getIsDelete()) {
            throw new NotFoundException("Not found user");
        }
        account.setAccountName(req.getUserName());
        account.setRoleId(req.getRoleId());
        account.setDateModified(now);
        accountRepository.save(account);

        return AccountMapper.toUserDto(account);
    }

    @Override
    public UserDto changePassword(ParamChangePassword req, int id) {
        Account account = accountRepository.findByAccountId(id);
        if (!accountRepository.existsById(id) || account.getIsDelete()) {
            throw new NotFoundException("Not found user");
        }
        if (!encoder.matches(req.getOldPassword(), account.getAccountPassword()) ||
                !req.getNewPassWord().equals(req.getConfirmNewPassWord())) {
            throw new InValidPasswordException();
        }
        String hash = BCrypt.hashpw(req.getNewPassWord(), BCrypt.gensalt(12));
        account.setAccountPassword(hash);
        account.setDateModified(now);
        accountRepository.save(account);

        return AccountMapper.toUserDto(account);
    }
}