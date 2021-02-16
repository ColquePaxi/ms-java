package com.plug.hruser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plug.hruser.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// Implementar uma busca customizada
	// Põe uma assinatura de interface = User
	// A própria assinatura vai ser interpretada como um SQL
	// Uma QueryMethods do JPA
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
	// IMPORTANTE: ele associa e faz a busca pelo NOME QUE DETERMINAMOS:
	// Ex: Email que recebe conteúdo email. Poderia ser findByLastname
	// e então buscaria o Lastname no database
	
	User findByEmail(String email);
	
	
}
