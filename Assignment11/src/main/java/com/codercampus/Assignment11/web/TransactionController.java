package com.codercampus.Assignment11.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.service.transactionService;

@Controller
public class TransactionController {
	
	@Autowired
	private transactionService transactionServ;
	
	@GetMapping("/transactions")
	public String getTransactions(ModelMap model) {
		List<Transaction> transactions = transactionServ.findAll();
		Transaction transaction = new Transaction();
		model.put("Transaction", transaction);
		model.put("transactions", transactions);
		return "transactions";
	}
	@GetMapping("/transactions/{transactionId}")
	public String getPerson(@PathVariable Long transactionId, ModelMap model) {
		
		Transaction transaction = transactionServ.findById(transactionId);
		model.put("transaction",transaction);
		return "transaction";
	}
	@PostMapping("/transactions")
	
		public String postRootWebpage(Transaction transaction)
		{
			Transaction savedTransaction = transactionServ.save(transaction);
			return "redirect:/transactions";
		}
	@PostMapping("/transactions/{transactionid}")
	public String postTransaction(Transaction transaction)
	{
		Transaction savedTransaction = transactionServ.save(transaction);
		return "redirect:/transactions/" + savedTransaction.getId();
	}
	@PostMapping("/transactions/{transactionid}/delete")
	public String deleteTransaction(@PathVariable Long transactionId) {
		transactionServ.delete(transactionId);
		return "redirect:/transactions";
	}
}  
