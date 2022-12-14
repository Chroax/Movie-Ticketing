package com.binar.kampusmerdeka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@AllArgsConstructor
public class NotificationRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private Boolean statusRead = false;
    private Instant createdAt;
    private Instant modifiedAt;
}
