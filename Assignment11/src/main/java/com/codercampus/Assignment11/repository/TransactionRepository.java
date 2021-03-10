package com.codercampus.Assignment11.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.codercampus.Assignment11.domain.Transaction;

@Repository
public class TransactionRepository {
	
	private List<Transaction> transactions = new ArrayList<>(100);
	
	public TransactionRepository () {
		super();
		populateData();
	}
	
	public List<Transaction> findAll() {
		
		List<Transaction>sortedDateTransactions = transactions.stream()
		                                         .sorted(Comparator.comparing(Transaction::getDate))
		                            		     .collect(Collectors.toList());
		return sortedDateTransactions;
		         
	}

	@SuppressWarnings("unchecked")
	private void populateData() {
		try (FileInputStream fileInputStream = new FileInputStream("transactions.txt");
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
			this.transactions = (List<Transaction>) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}

	public Transaction save(Transaction transaction) {
		transactions.add(transaction);
		return transaction;
	}

	public Transaction findById(Long transactionId) {
		// TODO Auto-generated method stub
		Integer index = Integer.parseInt(transactionId.toString());
		index--;
		return transactions.get(index);
	}

	public void delete(Long transactionId) {
		transactions.remove(transactionId);
		
	}

	
}