package org.kamal.library_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private int bookCount;
}