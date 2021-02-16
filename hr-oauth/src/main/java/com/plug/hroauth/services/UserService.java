package com.plug.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plug.hroauth.entities.User;
import com.plug.hroauth.feignclients.UserFeignClient;

@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public User findByEmail(String email) {
		// Vai se comunicar com o microsserviço hr-user através da interface
		// da UserFeignClient
		// Como espera um ResponseEntity, então usa no final o getBody() 
		User user = userFeignClient.findByEmail(email).getBody();
		
		// O ideal seria fazer uma pagina de Fallback com status-code padrão
		if(user == null) {
			logger.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");
		}
		logger.info("Email found: " + email);
		return user;
	}
	
}