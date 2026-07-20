package org.kamal.library_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private int borrowedBooksCount;
}