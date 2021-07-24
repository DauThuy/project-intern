package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.ParamChangePassword;
import com.example.demo.model.request.ParamCreateUser;
import com.example.demo.model.request.ParamAdminUpdateUser;
import com.example.demo.model.request.ParamUserUpdateUser;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface AccountService {
    InfoDto login(AccountDto dto);
    void revokeToken(UserDto userDto);
    UserDto getInfoUserFromToken(String token);
//    ConsumerTokenServices consumerTokenServices();
    List<UserDto> getAllUser();
    UserDto getUserById(int id);
    String deleteUserById(int id);
    UserDto createUser(ParamCreateUser req);
    UserDto updateUserByUser(ParamUserUpdateUser req, int id);
    UserDto updateUserByAdmin(ParamAdminUpdateUser req, int id);
    UserDto changePassword(ParamChangePassword req, int id);
}