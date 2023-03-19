package com.drivingschool.service;

import com.drivingschool.payload.response.UserInfoResponse;
import com.drivingschool.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseCookie authenticateWithCredentials(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateJwtCookie((UserDetailsImpl) authentication.getPrincipal());
    }

    @Override
    public ResponseCookie authenticateWithToken(String token) {
        if (jwtUtil.validateJwtToken(token))
            return jwtUtil.generateCookieFromToken(token);
        else
            return jwtUtil.getCleanJwtCookie();
    }
}
