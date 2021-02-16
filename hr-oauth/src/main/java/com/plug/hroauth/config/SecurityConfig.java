package com.plug.hroauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
// Precisa extender de uma classe para fazer a autenticação usando 
// o FRAMEWORK SpringSecurity
// Aqui vai fazer a autenticação, ou seja, 
// 1) Pegar as credenciais do usuário, 2) Buscar e comparar no database
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired // Não esqueça!!!!
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}

	// Não esqueça. E está vinculado ao AuthorizationServerConfig.
	// Só tivemos que chamar porque precisamos colocar o @Bean porque
	// estamos injetando em outro componente e para que esse outro 
	// component tenha acesso ao mesmo Bean
	@Bean  
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	
}
