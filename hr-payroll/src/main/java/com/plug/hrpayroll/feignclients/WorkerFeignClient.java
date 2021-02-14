package com.plug.hrpayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.plug.hrpayroll.entities.Worker;

@Component // porque vamos injetar em outras classes
@FeignClient(name="hr-worker", url="localhost:8001", path="/workers")
public interface WorkerFeignClient {

	// Assinatura
	// Tem que ser exatamente igual ao que foi declarado em WorkerResource
	@GetMapping(value = "/{id}")
	ResponseEntity<Worker> findById(@PathVariable Long id);
	
}
