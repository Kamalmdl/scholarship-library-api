package org.kamal.library_management.service.impl;

import lombok.RequiredArgsConstructor;
import org.kamal.library_management.dto.request.AuthorRequestDto;
import org.kamal.library_management.dto.response.AuthorResponseDto;
import org.kamal.library_management.entity.Author;
import org.kamal.library_management.exceptions.ResourceNotFoundException;
import org.kamal.library_management.repository.AuthorRepository;
import org.kamal.library_management.service.AuthorService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @CacheEvict(value = "authorsList", allEntries = true)
    public AuthorResponseDto create(AuthorRequestDto requestDto) {
        Author author = Author.builder()
                .fullName(requestDto.getFullName())
                .email(requestDto.getEmail())
                .build();

        Author saved = authorRepository.save(author);
        return toResponseDto(saved);
    }

    @Override
    @Cacheable(value = "authors", key = "#id")
    public AuthorResponseDto getById(Long id) {
        Author author = findAuthorOrThrow(id);
        return toResponseDto(author);
    }

    @Override
    @Cacheable(value = "authorsList", key = "#pageable")
    public Page<AuthorResponseDto> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(this::toResponseDto);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "authors", key = "#id"),
            @CacheEvict(value = "authorsList", allEntries = true)
    })
    public AuthorResponseDto update(Long id, AuthorRequestDto requestDto) {
        Author author = findAuthorOrThrow(id);
        author.setFullName(requestDto.getFullName());
        author.setEmail(requestDto.getEmail());

        Author updated = authorRepository.save(author);
        return toResponseDto(updated);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "authors", key = "#id"),
            @CacheEvict(value = "authorsList", allEntries = true)
    })
    public void delete(Long id) {
        Author author = findAuthorOrThrow(id);
        authorRepository.delete(author);
    }

    private Author findAuthorOrThrow(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    private AuthorResponseDto toResponseDto(Author author) {
        return AuthorResponseDto.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .email(author.getEmail())
                .bookCount(author.getBooks() != null ? author.getBooks().size() : 0)
                .build();
    }
}