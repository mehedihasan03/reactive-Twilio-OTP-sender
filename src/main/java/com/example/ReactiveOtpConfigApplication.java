package com.example;

import com.example.config.TwilioConfiguration;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ReactiveOtpConfigApplication {

	@Autowired
	private TwilioConfiguration twilio;

	@PostConstruct
	public void initTwilio(){
		Twilio.init(twilio.getAccountSid(), twilio.getAuthToken());
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveOtpConfigApplication.class, args);
	}

}
