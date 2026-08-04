package org.kamal.library_management.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {

    private Long id;
    private Long memberId;
    private LocalDateTime orderDate;
    private List<String> bookTitles;
}