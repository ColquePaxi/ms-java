package com.plug.hrpayroll.services;

import org.springframework.stereotype.Service;

import com.plug.hrpayroll.entities.Payment;

@Service
public class PaymentService {

	public Payment getPayment(long workerId, int days) {
		// Teste mockado
		return new Payment("Bob", 200.0, days);
	}
	
}
