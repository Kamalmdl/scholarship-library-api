package org.kamal.library_management.service;

import org.kamal.library_management.dto.AuthorRequestDto;
import org.kamal.library_management.dto.AuthorResponseDto;

import java.util.List;

public interface AuthorService {

    AuthorResponseDto create(AuthorRequestDto requestDto);

    AuthorResponseDto getById(Long id);

    List<AuthorResponseDto> getAll();

    AuthorResponseDto update(Long id, AuthorRequestDto requestDto);

    void delete(Long id);
}