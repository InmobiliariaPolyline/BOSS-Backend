package com.boos.backend.exception;

import java.time.LocalDateTime;

public record CustomErrorTemplate(
        LocalDateTime datetime,
        String message,
        String details
) {

}
