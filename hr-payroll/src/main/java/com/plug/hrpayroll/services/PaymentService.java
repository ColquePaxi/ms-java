package com.plug.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.plug.hrpayroll.entities.Payment;
import com.plug.hrpayroll.entities.Worker;

@Service
public class PaymentService {

	// Annotation para ler as PROPRIEDADES criadas em application.properties
	@Value("${hr-worker.host}")
	private String workerHost;
	
	// Injetando o objeto Bean que foi configurado em hr-payroll/config/AppConfig
	@Autowired
	private RestTemplate restTemplate;
	
	public Payment getPayment(long workerId, int days) {
		// Teste mockado
		//return new Payment("Bob", 200.0, days);
		
		// Tem que criar um Mapa de Parâmetros
		Map<String, String> uriVariables = new HashMap<>();
		// Um jeito rápido de transformar um Long em String
		uriVariables.put("id", ""+workerId);
		
		// Para instanciar o objeto Worker tem que copiar a classe do webservice 
		// hr-worker e tirar as annotations do JPA (deixar a classe pura) 
		String url =  workerHost + "/workers/{id}";
		//Class<Worker> responseType = Worker.class;
		//Worker worker = restTemplate.getForObject(url, responseType, uriVariables);
		Worker worker = restTemplate.getForObject(url, Worker.class, uriVariables);
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
	
}
