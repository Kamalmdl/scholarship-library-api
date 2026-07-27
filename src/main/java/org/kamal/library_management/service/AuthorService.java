package org.kamal.library_management.service;


import org.kamal.library_management.dto.Request.AuthorRequestDto;
import org.kamal.library_management.dto.Response.AuthorResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    AuthorResponseDto create(AuthorRequestDto requestDto);

    AuthorResponseDto getById(Long id);

    Page<AuthorResponseDto> getAll(Pageable pageable);

    AuthorResponseDto update(Long id, AuthorRequestDto requestDto);

    void delete(Long id);
}