package org.example.suiteHaven.dtos.photos;

import org.springframework.web.multipart.MultipartFile;

public record PhotoRequestDto(
        String fileName,
        MultipartFile[] file,
        Long holidayFlat
) {
}
