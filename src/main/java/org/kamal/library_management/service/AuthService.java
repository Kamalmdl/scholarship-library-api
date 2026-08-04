package org.kamal.library_management.service;


import org.kamal.library_management.dto.request.LoginRequestDto;
import org.kamal.library_management.dto.request.RegisterRequestDto;
import org.kamal.library_management.dto.response.AuthResponseDto;

public interface AuthService {

    AuthResponseDto register(RegisterRequestDto requestDto);

    AuthResponseDto login(LoginRequestDto requestDto);
}