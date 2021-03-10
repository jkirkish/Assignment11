package com.codercampus.Assignment11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.repository.TransactionRepository;

@Service
public class transactionService {

	private Long transactionId = 1L;
	@Autowired
	private TransactionRepository transRepo;
	
	
	public Transaction save(Transaction transaction) {
		if(transaction.getId() == null)
		transaction.setId(transactionId++);
		return transRepo.save(transaction);
		
	}

	public Transaction findById(Long transactionId) {
		return transRepo.findById(transactionId);
		
	}

	public List<Transaction> findAll() {
		
		return transRepo.findAll();
	}

	public void delete(Long transactionId) {
		transRepo.delete(transactionId);
		
	}

}
