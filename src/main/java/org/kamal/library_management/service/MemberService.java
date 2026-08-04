package org.kamal.library_management.service;

import org.kamal.library_management.dto.request.MemberRequestDto;
import org.kamal.library_management.dto.response.MemberResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    MemberResponseDto create(MemberRequestDto requestDto);

    MemberResponseDto getById(Long id);

    Page<MemberResponseDto> getAll(Pageable pageable);

    MemberResponseDto update(Long id, MemberRequestDto requestDto);

    void delete(Long id);
}