package com.nhnacademy.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.userservice.dto.UserDto;
import com.nhnacademy.userservice.service.UserService;
import com.nhnacademy.userservice.vo.RequestLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment environment;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                   UserService userService, Environment environment) {
        super(authenticationManager);
        this.userService = userService;
        this.environment = environment;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // 우리가 요청 정보를 보냈을 때 그걸 처리시켜 줄 수 있는 메서드이다.
        try {
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
            // 사용자가 입력했던 이메일과 아이디 값을 Spring Security에서 사용할 수 있는 형태의 값으로 반환하기 위해서
            // UsernamePasswordAuthenticationToken라는 형태로 바꿔줄 필요가 있다.
            // 그리고 최종적으로 이 값을 우리가 Authentication에 전달을 해서 인증처리를 요청을 해야 한다.
            // 사용자가 입력했던 유저네임과 패스워드가 그걸 감고 있는 형태인 authentication 토큰으로 변경을 했으니 그 값을 처리해주기 위한
            // Autentication매니저에다가 인증 작업을 요청해야 된다.
            // new ArrayList는 어떤 인자값이냐라는 권한에 관련된 것임.
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // 여기서는 사용자가 입력했던 id하고 패스워드로 실제 로그인이 성공을 했을 때 정확하게 어떤 값을 반환시켜 줄 것인지, 어떤 처리를
        // 해줄 것인지 예를 들어 토큰을 만든다든가 토큰을 만들 때 토큰의 만료 시간이 언제가 될 건지 사용자가 로그인 했을 때
        // 반환 값으로 어떤 것을 줄건지를 여기다가 보내주는 처리를 해주면 된다.
        String userName = ((User) auth.getPrincipal()).getUsername();
        UserDto userDetails = userService.getUserDetailsByEmail(userName);

        byte[] secretKeyBytes = Base64.getEncoder().encode(environment.getProperty("token.secret").getBytes());

        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);

        Instant now = Instant.now();

        String token = Jwts.builder()
                .subject(userDetails.getUserId())
                .expiration(Date.from(now.plusMillis(Long.parseLong(environment.getProperty("token.expiration_time")))))
                .issuedAt(Date.from(now))
                .signWith(secretKey)
                .compact();

        res.addHeader("token", token);
        res.addHeader("userId", userDetails.getUserId());
    }
}
