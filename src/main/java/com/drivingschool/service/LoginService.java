package com.drivingschool.service;

import com.drivingschool.payload.response.UserInfoResponse;
import org.springframework.http.ResponseCookie;

public interface LoginService {
    ResponseCookie authenticateWithCredentials(String username, String password);
    ResponseCookie authenticateWithToken(String token);

}
