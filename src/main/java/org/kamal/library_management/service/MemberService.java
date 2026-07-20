package org.kamal.library_management.service;

import org.kamal.library_management.dto.MemberRequestDto;
import org.kamal.library_management.dto.MemberResponseDto;

import java.util.List;

public interface MemberService {

    MemberResponseDto create(MemberRequestDto requestDto);

    MemberResponseDto getById(Long id);

    List<MemberResponseDto> getAll();

    MemberResponseDto update(Long id, MemberRequestDto requestDto);

    void delete(Long id);
}