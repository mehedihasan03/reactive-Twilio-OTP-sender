package com.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfiguration {

    @Autowired
    private TwilioOtpHandler otpHandler;

    @Bean
    public RouterFunction<ServerResponse> smsHandler() {
        return RouterFunctions.route()
                .POST("/router/send-OTP", otpHandler::sendOtp)
                .POST("/router/validate-OTP", otpHandler::vaidateOtp)
                .build();
    }
}
