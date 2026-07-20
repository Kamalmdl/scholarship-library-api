package org.kamal.library_management.service.impl;

import lombok.RequiredArgsConstructor;
import org.kamal.library_management.dto.MemberRequestDto;
import org.kamal.library_management.dto.MemberResponseDto;
import org.kamal.library_management.entity.Member;
import org.kamal.library_management.exceptions.ResourceNotFoundException;
import org.kamal.library_management.repository.MemberRepository;
import org.kamal.library_management.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDto create(MemberRequestDto requestDto) {
        Member member = Member.builder()
                .fullName(requestDto.getFullName())
                .email(requestDto.getEmail())
                .build();

        Member saved = memberRepository.save(member);
        return toResponseDto(saved);
    }

    @Override
    public MemberResponseDto getById(Long id) {
        Member member = findMemberOrThrow(id);
        return toResponseDto(member);
    }

    @Override
    public Page<MemberResponseDto> getAll(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(this::toResponseDto);
    }

    @Override
    public MemberResponseDto update(Long id, MemberRequestDto requestDto) {
        Member member = findMemberOrThrow(id);
        member.setFullName(requestDto.getFullName());
        member.setEmail(requestDto.getEmail());

        Member updated = memberRepository.save(member);
        return toResponseDto(updated);
    }

    @Override
    public void delete(Long id) {
        Member member = findMemberOrThrow(id);
        memberRepository.delete(member);
    }

    private Member findMemberOrThrow(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
    }

    private MemberResponseDto toResponseDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .fullName(member.getFullName())
                .email(member.getEmail())
                .borrowedBooksCount(member.getBorrowedBooks() != null ? member.getBorrowedBooks().size() : 0)
                .build();
    }
}