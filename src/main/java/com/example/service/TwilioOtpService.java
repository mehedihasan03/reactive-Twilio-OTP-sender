package com.example.service;

import com.example.config.TwilioConfiguration;
import com.example.dto.TwilioRequestDto;
import com.example.dto.TwilioResponseDto;
import com.example.utils.OtpStatus;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class TwilioOtpService {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    Map<String, String> otpMap = new HashMap<>();

    public Mono<TwilioResponseDto> sendOtp(TwilioRequestDto requestDto){

        TwilioResponseDto responseDto = null;
        try {
            PhoneNumber to = new PhoneNumber(requestDto.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrailNumber());
            String otp = generateOtp();
            String otpMessage ="Dear User, Your OTP is ##" + otp + "##. Use this to complete your process. Thank You.";

            Message message = Message.creator(to, from, otpMessage).create();
            otpMap.put(requestDto.getUserName(), otp);
            responseDto =new TwilioResponseDto(OtpStatus.DELIVERED, otpMessage);
        }catch (Exception ex){
            responseDto =new TwilioResponseDto(OtpStatus.FAILED, ex.getMessage());
        }

        return Mono.just(responseDto);
    }

    public Mono<String> validateOtp(String userInputOtp, String userName){
        if (userInputOtp.equals(otpMap.get(userName))){
            otpMap.remove(userName, userInputOtp);
            return Mono.just("Your OTP is valid, Please proceed");
        } else{
            return Mono.error(new IllegalArgumentException("Invalid OTP, Please Provide a valid OTP"));
        }
    }

    private String generateOtp(){
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}
