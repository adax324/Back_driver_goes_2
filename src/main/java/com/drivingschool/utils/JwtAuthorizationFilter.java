//package com.drivingschool.utils;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//public class JwtAuthorizationFilter extends OncePerRequestFilter {
//    @Autowired
//    private TokenProvider tokenProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (request.getMethod().equalsIgnoreCase(SecurityConstant.OPTIONS_HTTP_METHOD)) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//            if (authorizationHeader == null || authorizationHeader.startsWith(SecurityConstant.TOKEN_PREFIX)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//        }
//    }
//}
