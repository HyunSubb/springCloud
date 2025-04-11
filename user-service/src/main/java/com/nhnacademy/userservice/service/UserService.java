package com.nhnacademy.userservice.service;

import com.nhnacademy.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
}
