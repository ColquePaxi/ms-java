package com.plug.hroauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.plug.hroauth.entities.User;

//Sempre que usar Feign então tem que usar interface
//public class UserFeignClient {

//para que seja um componente gerenciado pelo Spring
@Component
// Com qual microsserviço queremos nos comunicar
@FeignClient(name = "hr-user", path = "/users")
public interface UserFeignClient {
	
	// Usa a mesma assinatura do que está no microsserviço que se quer comunicar
	@GetMapping(value = "/search")
	ResponseEntity<User> findByEmail(@RequestParam String email);

}
