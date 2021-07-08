package com.example.demo.service;

import com.example.demo.common.EmailValidate;
import com.example.demo.common.InValidEmailException;
import com.example.demo.common.UnauthorizedException;
import com.example.demo.config.ProvideJwt;
import com.example.demo.dto.AccountDto;
import com.example.demo.dto.InfoDto;
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
//        String t=encoder.encode("456");
//        System.out.println("t:"+t);
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

//    public ResponseEntity<Response> createUser(UserDto dto) {
//        Account user;
////        Account existingUser = accountRepository.findByEmailAddress(dto.getEmailAddress());
//
//
//        user = new Account();
//        user.setAccountPassword(encoder.encode(dto.getAccountPassword()));
//        user.setAccountName(dto.getAccountName());
//        user.setAccountStatus(dto.getAccountStatus());
//        user.setAccountImage(dto.getAccountImage());
//        user.setEmailAddress(dto.getEmailAddress());
//        user.setApprovalDate(dto.getApprovalDate());
//        user.setDateCreated(dto.getDateCreated());
//        user.setDateModified(dto.getDateModified());
////        user.setRoleId(dto.getRoleId());
//
//
//        try {
//            accountRepository.save(user);
//            return ResponseEntity.ok(Response.success("SUccess"));
//        } catch (Exception e) {
////            LOG.error(e.getMessage());
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Response.internalError(e.getMessage()));
//        }
//    }


}