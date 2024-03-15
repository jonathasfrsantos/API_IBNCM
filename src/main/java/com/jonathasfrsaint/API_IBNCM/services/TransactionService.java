package com.jonathasfrsaint.API_IBNCM.services;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jonathasfrsaint.API_IBNCM.entities.Finality;
import com.jonathasfrsaint.API_IBNCM.entities.Transaction;
import com.jonathasfrsaint.API_IBNCM.entities.DTO.TransactionDTO;
import com.jonathasfrsaint.API_IBNCM.factory.DTOFactory;
import com.jonathasfrsaint.API_IBNCM.repositories.FinalityRepository;
import com.jonathasfrsaint.API_IBNCM.repositories.TransactionRepository;
import com.jonathasfrsaint.API_IBNCM.services.exceptions.DataBaseException;
import com.jonathasfrsaint.API_IBNCM.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private FinalityRepository finalityRepository;

	
	@Transactional
	public TransactionDTO create(TransactionDTO dto) {
		try {
			Finality finality = finalityRepository.findByDescription(dto.getFinality());

			if (finality == null) {
				finality = new Finality(null, dto.getFinality());
				finalityRepository.save(finality);
			}
			
			Transaction transaction = new Transaction(dto.getId(), dto.getTransactionDate(), dto.getRevenue(),
					dto.getExpense(), dto.getHistoric(), finality, dto.getBankCash());

			transactionRepository.save(transaction);
			return DTOFactory.createDTO(transaction);

		} catch (Exception e) {
			throw new RuntimeException("Erro ao criar transação", e);
		}

	}
	
	@Transactional
	public TransactionDTO update(Long id, TransactionDTO dto) {
		try {
			Optional<Transaction> oldTransaction = transactionRepository.findById(id);
			
			if(oldTransaction.isPresent()) {
				Transaction transaction = oldTransaction.get();
				
				Transaction updatedObj = updateData(transaction, dto);
				
				transactionRepository.save(updatedObj);
				return DTOFactory.createDTO(transaction);
			}
			
		} catch (ResourceNotFoundException e) {
				throw new ResourceNotFoundException("ID :" + id + " não encontrado" );
		}
		return dto;
	}
	
	public List<TransactionDTO> findAll(){
		List<Transaction> result = transactionRepository.findAll();
		List<TransactionDTO> dto = result.stream().map((x) -> DTOFactory.createDTO(x)).collect(Collectors.toList());
		
		return dto;
		
	}
	
	
	public TransactionDTO findById(Long id) {
		try {
			Transaction result = transactionRepository.findById(id).get();
			TransactionDTO dto = DTOFactory.createDTO(result);
			return dto;
			
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}
	
	public List<TransactionDTO> findTithesListByDateInterval(LocalDate start_date, LocalDate end_date) {
		List<Transaction> result = transactionRepository.tithesTable(start_date, end_date);

		List<TransactionDTO> dto = result.stream().map((x) -> DTOFactory.createDTO(x)).collect(Collectors.toList());

		return dto;

	}

	public List<TransactionDTO> findExpenseListByDateInterval(LocalDate start_date, LocalDate end_date) {
		List<Transaction> result = transactionRepository.expensesTableGroupByFinality(start_date, end_date);

		List<TransactionDTO> dto = result.stream().map((x) -> DTOFactory.createDTO(x)).collect(Collectors.toList());

		return dto;

	}
	
	public List<TransactionDTO> findOffersListByDateInterval(LocalDate start_date, LocalDate end_date) {
		List<Transaction> result = transactionRepository.OffersTable(start_date, end_date);

		List<TransactionDTO> dto = result.stream().map((x) -> DTOFactory.createDTO(x)).collect(Collectors.toList());

		return dto;

	}
	
	
	
	
	public void delete(Long id) {
		try {
			transactionRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Transaction updateData(Transaction oldTransaction, TransactionDTO dto) {
		oldTransaction.setTransactionDate(dto.getTransactionDate());
		oldTransaction.setRevenue(dto.getRevenue());
		oldTransaction.setExpense(dto.getExpense());
		oldTransaction.setHistoric(dto.getHistoric());
		
		Finality finality = finalityRepository.findByDescription(dto.getFinality());
		
		if (finality == null) {
			finality = new Finality(null, dto.getFinality());
			finalityRepository.save(finality);
		}
		
		oldTransaction.setFinality(finality);
		oldTransaction.setBankCash(dto.getBankCash());
		
		return oldTransaction;
		
	}
	
}
