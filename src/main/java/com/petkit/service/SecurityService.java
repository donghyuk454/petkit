package com.petkit.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
	String createToken(String subject);
	boolean validateToken(String token);
    String getSubject(String token);
}
