package com.example.racingevent.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RacingEventDTO {
    private Long id;
    private String eventName;
    private LocalDateTime date;
    private String location;
}
