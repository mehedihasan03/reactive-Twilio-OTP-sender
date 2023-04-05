package com.example.dto;

import com.example.utils.OtpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwilioResponseDto {
    private OtpStatus status;
    private String message;
}
