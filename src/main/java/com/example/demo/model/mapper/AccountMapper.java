package com.example.demo.model.mapper;
import com.example.demo.entity.Account;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.CreateUserReq;
//import com.example.demo.model.request.UpdateUserReq;
import com.example.demo.model.request.UpdateUserReq;
import org.springframework.security.crypto.bcrypt.BCrypt;
public class AccountMapper {
    public static UserDto toUserDto (Account account) {
        UserDto userDtoMapper = new UserDto();
        userDtoMapper.setAccountName(account.getAccountName());
        userDtoMapper.setEmailAddress(account.getEmailAddress());
        userDtoMapper.setRoleId(account.getAccountStatus());
        return userDtoMapper;
    }
    public static Account toUser(CreateUserReq req) {
        Account user = new Account();
        user.setAccountName(req.getAccountName());
        user.setEmailAddress(req.getEmailAddress());
        // Hash password using BCrypt
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setAccountPassword(hash);
//        user.setRole("USER");
        return user;
    }
    public static Account toUser(UpdateUserReq req, int id) {
        Account user = new Account();
        user.setAccountName(req.getAccountName());
        user.setEmailAddress(req.getEmailAddress());
//        user.setAccountStatus(req.getAccountStatus());
        // Hash password using BCrypt
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setAccountPassword(hash);
        return user;
    }
}