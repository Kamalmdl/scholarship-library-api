package org.kamal.library_management.service;


import org.kamal.library_management.dto.Request.LoginRequestDto;
import org.kamal.library_management.dto.Request.RegisterRequestDto;
import org.kamal.library_management.dto.Response.AuthResponseDto;

public interface AuthService {

    AuthResponseDto register(RegisterRequestDto requestDto);

    AuthResponseDto login(LoginRequestDto requestDto);
}