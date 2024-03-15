package com.jonathasfrsaint.API_IBNCM.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jonathasfrsaint.API_IBNCM.entities.DTO.TransactionDTO;
import com.jonathasfrsaint.API_IBNCM.repositories.TransactionRepository;
import com.jonathasfrsaint.API_IBNCM.services.TransactionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TransactionDTO> create(@RequestBody @Validated TransactionDTO transactionDTO) {
		return ResponseEntity.ok().body(transactionService.create(transactionDTO));
	}
	
	@GetMapping
	public ResponseEntity<List<TransactionDTO>> findAll(){
		return ResponseEntity.ok().body(transactionService.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TransactionDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(transactionService.findById(id));
		
	}
	
	@GetMapping("/tithes-table-by-date")
	public ResponseEntity<List<TransactionDTO>> getTithes(@RequestParam("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate) {
		return ResponseEntity.ok().body(transactionService.findTithesListByDateInterval(startDate, endDate));
	}
	
	@GetMapping("/total-tithes-by-date")
	public Double getTotalTithes(@RequestParam("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate) {
		return transactionRepository.totalOfTithes(startDate, endDate);
	}
	
	
	// não está funcionando
	@GetMapping("/expenses-table-by-date")
	public ResponseEntity<List<TransactionDTO>> getExpenses(@RequestParam("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate) {
		return ResponseEntity.ok().body(transactionService.findExpenseListByDateInterval(startDate, endDate));
	}
	
	@GetMapping("/total-expenses-by-date")
	public Double getTotalExpenses(@RequestParam("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate) {
		return transactionRepository.totalOfExpenses(startDate, endDate);
	}
	
	@GetMapping("/total-offers-by-date")
	public Double getTotalOffers(@RequestParam("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate) {
		return transactionRepository.totalOffers(startDate, endDate);
	}
	
	@GetMapping("/offers-table-by-date")
	public ResponseEntity<List<TransactionDTO>> getOffers(@RequestParam("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate) {
		return ResponseEntity.ok().body(transactionService.findOffersListByDateInterval(startDate, endDate));
	}


	@PutMapping(value = "/{id}")
	public ResponseEntity<TransactionDTO> update(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO) {
		transactionDTO = transactionService.update(id, transactionDTO);
		return ResponseEntity.ok().body(transactionDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		transactionService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	

}
