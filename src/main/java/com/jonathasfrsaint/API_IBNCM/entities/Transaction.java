package com.jonathasfrsaint.API_IBNCM.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_transaction")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private LocalDate transactionDate;
	private BigDecimal revenue;
	private BigDecimal expense;
	private String historic;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "finality_id")
	private Finality finality;
	
	private String bankCash;

	public Transaction() {

	}

	public Transaction(Long id, LocalDate transactionDate, BigDecimal revenue, BigDecimal expense, String historic,
			Finality finality, String bankCash) {
		this.id = id;
		this.transactionDate = transactionDate;
		this.revenue = revenue;
		this.expense = expense;
		this.historic = historic;
		this.finality = finality;
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

	public Finality getFinality() {
		return finality;
	}

	public void setFinality(Finality finality) {
		this.finality = finality;
	}

	public String getBankCash() {
		return bankCash;
	}

	public void setBankCash(String bankCash) {
		this.bankCash = bankCash;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(id, other.id);
	}

}
