package com.binar.kampusmerdeka.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationResponse {
    private String title;
    private String content;
    private Boolean statusRead;
    private Instant createdAt;
    private Instant modifiedAt;
}

