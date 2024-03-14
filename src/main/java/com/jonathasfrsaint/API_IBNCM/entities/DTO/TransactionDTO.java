package com.jonathasfrsaint.API_IBNCM.entities.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jonathasfrsaint.API_IBNCM.entities.Finality;

public class TransactionDTO {
	
	private Long id;
	private LocalDate transactionDate;
	private BigDecimal revenue;
	private BigDecimal expense;
	private String historic;
	private String finality;
	private String bankCash;
		
	public TransactionDTO() {
		
	}

	public TransactionDTO(Long id, LocalDate transactionDate, BigDecimal revenue, BigDecimal expense, String historic,
			Finality finality, String bankCash) {
		this.id = id;
		this.transactionDate = transactionDate;
		this.revenue = revenue;
		this.expense = expense;
		this.historic = historic;
		this.finality = finality.getDescription();
		this.bankCash = bankCash;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}


	public BigDecimal getRevenue() {
		return revenue;
	}


	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}


	public BigDecimal getExpense() {
		return expense;
	}


	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}


	public String getHistoric() {
		return historic;
	}


	public void setHistoric(String historic) {
		this.historic = historic;
	}


	public String getFinality() {
		return finality;
	}


	public void setFinality(String finality) {
		this.finality = finality;
	}


	public String getBankCash() {
		return bankCash;
	}


	public void setBankCash(String bankCash) {
		this.bankCash = bankCash;
	}
	
	
	
	

}
