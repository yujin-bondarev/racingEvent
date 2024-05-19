package com.example.racingevent.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SponsorDTO {
    private Long id;
    private String spName;
    private String budget;
    private LocalDateTime dateOfContract;
}
