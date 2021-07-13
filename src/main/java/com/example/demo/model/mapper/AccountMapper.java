package com.example.demo.model.mapper;

import com.example.demo.entity.Account;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.UserDto;

public class AccountMapper {
    public static UserDto userDto (UserDto userDto) {
        UserDto userMapper = new UserDto();
        userMapper.setAccountName(userDto.getAccountName());
        userMapper.setEmailAddress(userDto.getEmailAddress());
        userMapper.setRoleId(userDto.getRoleId());

        return userMapper;
    }
}
