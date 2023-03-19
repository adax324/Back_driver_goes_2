package com.drivingschool.utility;

import com.drivingschool.entity.User;
import com.drivingschool.payload.response.UserInfoResponse;
import com.drivingschool.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserUtil {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepo userRepo;

    public UserInfoResponse generateUserInfoFromToken(String token) {
        String username = jwtUtil.getUsernameFromJwtToken(token);
        User user = userRepo.findByUsername(username);
        return new UserInfoResponse(user.getId(),
                user.getUsername(),
                user.getUsername(),
                user.getRoles().stream()
                        .map(item -> item.getName().name())
                        .collect(Collectors.toList()));
    }
}
