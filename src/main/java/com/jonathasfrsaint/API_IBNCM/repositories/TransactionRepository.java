package com.jonathasfrsaint.API_IBNCM.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jonathasfrsaint.API_IBNCM.entities.Transaction;
import com.jonathasfrsaint.API_IBNCM.entities.DTO.TransactionDTO;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{
	
	// total revenues
	@Query(value = "SELECT SUM(revenue) as totalRevenues FROM tb_transaction WHERE transaction_date BETWEEN :start_date AND :end_date", nativeQuery = true)
	Double totalRevenuesPerPeriod(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date);
	
	
	//total expenses
	@Query(value = "SELECT SUM(expense) as totalExpenses FROM tb_transaction WHERE transaction_date BETWEEN :start_date AND :end_date", nativeQuery = true)
	Double totalExpensesPerPeriod(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date);
	
	//<< Relatório de dízimos>>
	
	//1- lista de dízimos
	
	@Query(value = "SELECT * FROM tb_transaction WHERE finality_id = 1 AND transaction_date BETWEEN :start_date AND :end_date", nativeQuery = true)
	List<Transaction> tithesTable (@Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);
	
	//2 - soma dos dízimos
	
	@Query(value = "SELECT SUM(revenue) FROM tb_transaction WHERE finality_id = 1 AND transaction_date BETWEEN :start_date AND :end_date", nativeQuery = true)
	Double totalOfTithes(@Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);
	
													// << Relatório de saídas >>
	
	//1. lista de saídas agrupadas por data e finalidade
	
	@Query(value = "SELECT f.description AS descrição, SUM(t.expense) AS valor_total " +
			"FROM tb_transaction t " +
			"JOIN tb_finality f ON t.finality_id = f.id_finality " +
			"WHERE t.transaction_date BETWEEN :start_date AND :end_date AND t.expense != 0 " +
			"GROUP BY f.description " +
			"ORDER BY f.description " ,nativeQuery = true)
	List<Transaction> expensesTableGroupByFinality(@Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);
	
	//2. total das saídas agrupadas por finalidade
	
	@Query(value = "SELECT SUM(expense) FROM tb_transaction WHERE expense != 0 AND transaction_date BETWEEN :start_date AND :end_date", nativeQuery = true)
	Double totalOfExpenses(@Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);

	
														// << Relatório de ofertantes >>
	
	// 1. lista de ofertantes, agrupado por data e finalidade
	

	@Query(value ="SELECT SUM(revenue) as totalRevenues FROM tb_transaction WHERE transaction_date BETWEEN :start_date AND :end_date AND finality_id = 2", nativeQuery = true)
	Double totalOffers(@Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);
	

	@Query(value = "SELECT f.description AS Ofertantes, t.transaction_date, SUM(t.revenue) AS valor " +
				"FROM tb_transaction t " +
				"JOIN tb_finality f " +
				"ON t.finality_id = f.id_finality " +
				"WHERE t.transaction_date BETWEEN :start_date AND :end_date AND finality_id = 2 " +
				"GROUP BY t.transaction_date, f.description " +
				"ORDER BY t.transaction_date ", nativeQuery = true)
	List<Transaction> OffersTable(@Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);
	
	

}
