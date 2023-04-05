package com.example.dto;

import lombok.Data;

@Data
public class TwilioRequestDto {

    private String phoneNumber;
    private String userName;
    private String oneTimePassword;
}
