package org.example.superbnb.dtos.photos;

import org.springframework.web.multipart.MultipartFile;

public record PhotoRequestDto(
        String fileName,
        MultipartFile[] file,
        Long holidayFlat
) {
}
