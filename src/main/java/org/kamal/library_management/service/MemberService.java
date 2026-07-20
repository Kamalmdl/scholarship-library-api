package org.kamal.library_management.service;

import org.kamal.library_management.dto.MemberRequestDto;
import org.kamal.library_management.dto.MemberResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {

    MemberResponseDto create(MemberRequestDto requestDto);

    MemberResponseDto getById(Long id);

    Page<MemberResponseDto> getAll(Pageable pageable);

    MemberResponseDto update(Long id, MemberRequestDto requestDto);

    void delete(Long id);
}