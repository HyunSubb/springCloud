package com.nhnacademy.userservice.controller;

import com.nhnacademy.userservice.dto.UserDto;
import com.nhnacademy.userservice.service.UserService;
import com.nhnacademy.userservice.vo.Greeting;
import com.nhnacademy.userservice.vo.RequestUser;
import com.nhnacademy.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private Environment env; // yml에서 설정한 것을 가져올 수 있다.

    private UserService userService;

    @Autowired
    private Greeting greeting;

    @Autowired
    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    @GetMapping("/user-service/health_check")
    public String status() {
        return String.format("Its Working in User Service on %s", env.getProperty("server.port"));
    }

    @GetMapping("/user-service/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    @GetMapping("/user-service/welcome2")
    public String welcome2() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

}
