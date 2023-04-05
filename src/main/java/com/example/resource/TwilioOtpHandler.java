package com.example.resource;

import com.example.dto.TwilioRequestDto;
import com.example.service.TwilioOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TwilioOtpHandler {

    @Autowired
    private TwilioOtpService service;

    public Mono<ServerResponse> sendOtp(ServerRequest request) {
        return request.bodyToMono(TwilioRequestDto.class)
                .flatMap(req -> service.sendOtp(req))
                .flatMap(req -> ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(req)));
    }

    public Mono<ServerResponse> vaidateOtp(ServerRequest request) {
        return request.bodyToMono(TwilioRequestDto.class)
                .flatMap(dto -> service.validateOtp(
                        dto.getOneTimePassword(),
                        dto.getUserName()
                ))
                .flatMap(dto -> ServerResponse.status(HttpStatus.OK).bodyValue(dto));
    }
}
